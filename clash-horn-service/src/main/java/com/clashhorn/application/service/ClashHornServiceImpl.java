/*
 * Clash Horn - MIT License
 */
package com.clashhorn.application.service;

import com.clashhorn.application.dto.ClanAccountDTO;
import com.clashhorn.application.dto.ClanFullDTO;
import com.clashhorn.application.dto.WarPlanFullDTO;
import com.clashhorn.domain.model.account.ClanAccount;
import com.clashhorn.domain.model.account.ClanAccountRepository;
import com.clashhorn.domain.model.war.WarPlan;
import com.clashhorn.domain.model.war.WarPlanRepository;
import com.clashhorn.application.clashapi.Clan;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import com.clashhorn.domain.service.ClanAccountService;
import com.clashhorn.domain.service.WarPlanService;
import org.springframework.data.domain.Example;

/**
 *
 * @author morgade
 */
@Service
@AutoJsonRpcServiceImpl
public class ClashHornServiceImpl implements ClashHornService {
    
    @Autowired
    @Qualifier("dtoConversionService")
    private ConversionService converter;
    
    @Autowired
    private ClashOfClansService clashAPIService;
    
    @Autowired
    private ClanAccountService clashHornService;
    
    @Autowired
    private WarPlanRepository warPlanRepository;
    
    @Autowired
    private WarPlanService warPlanService;
    
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
        WarPlan warPlan;
        if (warPlanId!=null) {
            warPlan = warPlanRepository.findOne(Example.of(new WarPlan(warPlanId, clanAccountId)));
        } else {
            warPlan = warPlanService.getOrCreateCurrentWarPlan(clanAccountId);
        }
        return converter.convert(warPlan, WarPlanFullDTO.class);
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
