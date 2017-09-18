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
import static org.springframework.util.ResourceUtils.getFile;
import com.clashhorn.application.service.ClashOfClansService;

/**
 *
 * @author morgade
 */
@Service
@Profile("!live-clash-api")
public class ClashAPIServiceMockImpl implements ClashOfClansService {
    @Autowired
    private ObjectMapper objectMapper;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public War currentWar(String clanTag) {
        try {
            Thread.sleep(2000);
            return objectMapper.readValue(getFile("classpath:mock/war-01.json"), War.class);
        } catch (IOException | InterruptedException ex) {
            throw new IllegalStateException(ex);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Clan clans(String clanTag) {
        try {
            Thread.sleep(2000);
            return objectMapper.readValue(getFile("classpath:mock/clan-01.json"), Clan.class);
        } catch (IOException | InterruptedException ex) {
            throw new IllegalStateException(ex);
        }
    }
    
}
