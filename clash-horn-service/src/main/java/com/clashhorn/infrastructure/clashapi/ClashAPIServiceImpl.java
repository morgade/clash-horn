/*
 * Clash Horn - MIT License
 */
package com.clashhorn.infrastructure.clashapi;

import com.clashhorn.infrastructure.clashapi.data.Clan;
import com.clashhorn.infrastructure.clashapi.data.War;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
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
@Profile("live-clash-api")
public class ClashAPIServiceImpl implements ClashAPIService {
    @Value("${clash-api.baseUrl}")
    private String baseUrl;
    @Value("${clash-api.accessToken}")
    private String accessToken;
    @Autowired
    private RestTemplate restTemplate;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public War currentWar(String clanTag) {
        String fixedTag = fixTagPrefix(clanTag);
        
        // /clans/{tag}/currentWar
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .pathSegment(RESOURCE_CURRENT_WAR)
                .buildAndExpand(fixedTag).toUri();
        
        ResponseEntity<War> response = restTemplate.exchange(uri, HttpMethod.GET, createRequestEntity(), War.class);
        return proccessResponse(response);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Clan clans(String clanTag) {
        // /clans/{tag}
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .pathSegment(RESOURCE_CLANS)
                .buildAndExpand(clanTag).toUri();
        
        ResponseEntity<Clan> response = restTemplate.exchange(uri, HttpMethod.GET, createRequestEntity(), Clan.class);
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
    
    /**
     * Creates the default request entity (should cache we it ?)
     * @return RequestEntity for a RestTemplate consumption
     */
    private HttpEntity createRequestEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HEADER_AUTH_NAME, HEADER_AUTH_VALUE_PREFIX + accessToken);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(headers);
        return entity;
    }
}
