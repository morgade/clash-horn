/*
 * Clash Horn - MIT License
 */
package com.clashhorn.infrastructure.clashapi;

import com.clashhorn.infrastructure.clashapi.data.War;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author morgade
 */
public class ClashService {
    public static final String RESOURCE_CURRENT_WAR = "/clans/{clanTag}/currentwar";
    
    @Value("${clash-api.baseUrl}")
    private String baseUrl;
    @Autowired
    private RestTemplate restTemplate;
    
    /**
     * Fetch currentWar data fom Clash of Clans API
     * @param clanTag
     * @return 
     */
    public War currentWar(String clanTag) {
        // /clans/{tag}/currentWar
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .pathSegment(RESOURCE_CURRENT_WAR)
                .buildAndExpand(clanTag).toUri();
        
        ResponseEntity<War> response = restTemplate.getForEntity(uri, War.class);
        return proccessResponse(response);
    }
    
    /**
     * General proccessing of Clash of Clans API response
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
}
