/*
 * Clash Horn - MIT License
 */
package com.clashhorn.infrastructure.mock;

import com.clashhorn.application.clashapi.Clan;
import com.clashhorn.application.clashapi.War;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import com.clashhorn.application.service.ClashOfClansService;
import static java.lang.String.format;
import org.springframework.beans.factory.annotation.Value;

/**
 * This class uses a mock data folder based on the system property 'mock.root'
 * Defaults to 'mock/data01'.
 * 
 * @author morgade
 */
@Service
@Profile("!live-clash-api")
public class ClashAPIServiceMockImpl implements ClashOfClansService {
    @Autowired
    private ObjectMapper objectMapper;
    @Value("#{'/' + (systemProperties['mock.root']?:'mock/data01')}")
    private String mockRoot;
    
    private int currenWarIdx = 1;
    
    /**
     * {@inheritDoc}
     * Loads json files in sequenence as in <mockRoot>/war-<01, 02 ...>.json
     */
    @Override
    public War currentWar(String clanTag) {
        try {
            War w = objectMapper.readValue(getClass().getResourceAsStream(format("%s/currentWar-%02d.json", mockRoot, currenWarIdx)), War.class);
            if (getClass().getResource(format("%s/currentWar-%02d.json", mockRoot, currenWarIdx+1))!=null) {
                currenWarIdx++;
            }
            return w;
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Clan clans(String clanTag) {
        try {
            return objectMapper.readValue(getClass().getResourceAsStream(format("%s/clans-01.json")), Clan.class);
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }
    
}