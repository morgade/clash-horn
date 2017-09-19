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
import com.clashhorn.application.clashapi.War;
import com.clashhorn.domain.model.clan.ClanRef;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import com.clashhorn.domain.service.ClanAccountService;
import com.clashhorn.domain.service.WarPlanService;
import java.util.UUID;
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
    private ClashOfClansService clashOfClansService;
    
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
        Clan clan = clashOfClansService.clans(tag);
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
            ClanAccount clanAccount = clanAccountRepository.findOne(clanAccountId);
            // Fetch current war on CoC API to verify matches of warplans
            // TODO: Optimize and reduce calls to clashOfClansService (Example: search repository first if there's a walPlan where endTime is in the future ?)
            War war = clashOfClansService.currentWar(clanAccount.getClan().getTag());
            // Query a matching warplan on the repository
            WarPlan currentWarPlan = warPlanRepository.findByAccountAndPreparationTime(clanAccountId, war.getPreparationStartTime());
            
            if (currentWarPlan!=null) {
                // Found a WarPlan matching with current war. This is it
                // TODO: Merge and update possible current war changes
                warPlan = currentWarPlan;
            } else {
                // No matches of current war. Create a new war plan here
                warPlan = createWarPlan(war, clanAccountId);
                warPlan = warPlanRepository.save(warPlan);
            }
        }
        return converter.convert(warPlan, WarPlanFullDTO.class);
    }
    
    /**
     * Creates a WarPlan based on CoC API war data
     * @param war
     * @return 
     */
    private WarPlan createWarPlan(War war, String clanAccountId) {
        String id = UUID.randomUUID().toString();
        WarPlan warPlan = new WarPlan(id, clanAccountId)
                .withClan(new ClanRef(war.getClan().getTag(), war.getClan().getName()))
                .withEnemy(new ClanRef(war.getOpponent().getTag(), war.getOpponent().getName()))
                .withPreparationStartTime(war.getPreparationStartTime())
                .withStartTime(war.getStartTime())
                .withEndTime(war.getEndTime());
        return warPlan;
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
