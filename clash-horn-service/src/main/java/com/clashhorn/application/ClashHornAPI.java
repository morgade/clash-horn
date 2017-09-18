/*
 * Clash Horn - MIT License
 */
package com.clashhorn.application;

import com.clashhorn.application.dto.ClanAccountDTO;
import com.clashhorn.application.dto.ClanFullDTO;
import com.clashhorn.application.dto.ClanWarDTO;
import com.clashhorn.application.dto.WarPlanFullDTO;
import com.clashhorn.domain.model.account.ClanAccount;
import com.clashhorn.domain.model.account.ClanAccountRepository;
import com.clashhorn.domain.service.ClashHornService;
import com.clashhorn.infrastructure.clashapi.ClashAPIService;
import com.clashhorn.infrastructure.clashapi.data.Clan;
import com.clashhorn.infrastructure.clashapi.data.War;
import com.clashhorn.interfaces.jsonrpc.ClashHornEndpoint;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import java.util.HashMap;
import java.util.Map;
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
    
    @Autowired
    private ClashHornService clashHornService;
    
    @Autowired
    private ClanAccountRepository clanAccountRepository;
    
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
    public ClanAccountDTO fetchClanAccount(String clanAccountId) {
        ClanAccount clanAccount = clanAccountRepository.findOne(clanAccountId);
        return converter.convert(clanAccount, ClanAccountDTO.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ClanAccountDTO registerClanAccount(String tag, String clanAccountId) {
        ClanAccount clanAccount = clashHornService.registerNewClanAccount(clanAccountId, tag);
        return converter.convert(clanAccount, ClanAccountDTO.class);
    }
    
        
    /**
     * {@inheritDoc}
     */
    @Override
    public WarPlanFullDTO fetchWarPlan(String clanAccountId, String warPlanId) {
        War war = clashAPIService.currentWar("#22PLRY2G");
        WarPlanFullDTO w = new WarPlanFullDTO();
        w.setId(warPlanId);
        w.setClan(converter.convert(war.getClan(), ClanWarDTO.class));
        w.setOpponent(converter.convert(war.getOpponent(), ClanWarDTO.class));
        w.setSize(war.getClan().getMembers().length);
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
