/*
 * Clash Horn - MIT License
 */
package com.clashhorn.application;

import com.clashhorn.application.dto.ClanAccountDTO;
import com.clashhorn.application.dto.ClanBasicDTO;
import com.clashhorn.application.dto.ClanFullDTO;
import com.clashhorn.application.dto.WarPlanFullDTO;
import com.clashhorn.infrastructure.clashapi.ClashAPIService;
import com.clashhorn.infrastructure.clashapi.data.Clan;
import com.clashhorn.interfaces.jsonrpc.ClashHornEndpoint;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

/**
 *
 * @author morgade
 */
@Service
@AutoJsonRpcServiceImpl
public class ClashHornAPI implements ClashHornEndpoint {
    @Autowired
    @Qualifier("dtoConversionService")
    private ConversionService converter;
    @Autowired
    private ClashAPIService clashAPIService;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ClanFullDTO fethClanData(String tag) {
        Clan clan = clashAPIService.clans(tag);
        return converter.convert(clan, ClanFullDTO.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ClanAccountDTO fethClanAccountData(String clanAccountId) {
        // TODO: remove mock implementation
        ClanAccountDTO c = new ClanAccountDTO();
        Clan clan = clashAPIService.clans("#22PLRY2G");
        c.setId(clanAccountId);
        c.setClan(converter.convert(clan, ClanBasicDTO.class));
        try {Thread.sleep(2000);} catch (InterruptedException ex) {}
        return c;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ClanAccountDTO registerClanAccount(String tag, String clanAccountId) {
        // TODO: remove mock implementation
        ClanAccountDTO c = new ClanAccountDTO();
        Clan clan = clashAPIService.clans(tag);
        c.setId(clanAccountId);
        c.setClan(converter.convert(clan, ClanBasicDTO.class));
        try {Thread.sleep(2000);} catch (InterruptedException ex) {}
        return c;
    }
    
        
    /**
     * {@inheritDoc}
     */
    @Override
    public WarPlanFullDTO fetchWarPlan(String clanAccountId, String warPlanId) {
        // TODO: remove mock implementation
        WarPlanFullDTO w = new WarPlanFullDTO();
        w.setId(warPlanId);
        try {Thread.sleep(2000);} catch (InterruptedException ex) {}
        return w;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Map status() {
        return new HashMap() {{
            put("freeHeap", Runtime.getRuntime().freeMemory());
        }};
    }
    
}
