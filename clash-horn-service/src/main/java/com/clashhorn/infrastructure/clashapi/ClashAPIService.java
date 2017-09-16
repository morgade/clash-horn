/*
 * Clash Horn - MIT License
 */
package com.clashhorn.infrastructure.clashapi;

import com.clashhorn.infrastructure.clashapi.data.Clan;
import com.clashhorn.infrastructure.clashapi.data.War;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author morgade
 * @author guilaaf
 */
@Service
public class ClashAPIService  {
    public static final String TAG_PREFIX = "#";
    public static final String RESOURCE_CURRENT_WAR = "clans/{clanTag}/currentwar";
    public static final String RESOURCE_CLANS = "clans/{clanTag}";
    public static final String HEADER_AUTH_NAME = "Authorization";
    public static final String HEADER_AUTH_VALUE_PREFIX = "Bearer ";
    
    @Value("${clash-api.baseUrl}")
    private String baseUrl;
    @Value("${clash-api.accessToken}")
    private String accessToken;
    @Autowired
    private RestTemplate restTemplate;
    
    /**
     * Fetch currentWar data from Clash of Clans API
     * @param clanTag
     * @return 
     */
    public War currentWar(String clanTag) {
        String fixedTag = fixTagPrefix(clanTag);
        
        // /clans/{tag}/currentWar
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .pathSegment(RESOURCE_CURRENT_WAR)
                .buildAndExpand(fixedTag).toUri();
        
        // authorization header
        HttpHeaders headers = new HttpHeaders();
        headers.set(HEADER_AUTH_NAME, HEADER_AUTH_VALUE_PREFIX + accessToken);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(headers);
        
        ResponseEntity<War> response = restTemplate.exchange(uri, HttpMethod.GET, entity, War.class);
        return proccessResponse(response);
    }
    
    /**
     * Fetch clan data from Clash of Clans API
     * @param clanTag
     * @return 
     */
    public Clan clans(String clanTag) {
        // /clans/{tag}
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .pathSegment(RESOURCE_CLANS)
                .buildAndExpand(clanTag).toUri();
        
        ResponseEntity<Clan> response = restTemplate.getForEntity(uri, Clan.class);
        return proccessResponse(response);
    }
    
    /**
     * General processing of Clash of Clans API response
     * @param <T>
     * @param entity
     * @return 
     */
    private <T> T proccessResponse(ResponseEntity<T> entity) {
        if (entity.getStatusCode()==HttpStatus.OK) {
            return entity.getBody();
        } else {
            return null;
        }
    }

    private String fixTagPrefix(String clanTag) {
        if (clanTag.startsWith(TAG_PREFIX)) {
            return clanTag;
        } else {
            return TAG_PREFIX + clanTag;
        }
    }
}
