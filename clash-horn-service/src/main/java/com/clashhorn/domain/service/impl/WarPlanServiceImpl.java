/*
 * Clash Horn - MIT License
 */
package com.clashhorn.domain.service.impl;

import com.clashhorn.domain.model.war.WarPlan;
import com.clashhorn.domain.model.war.WarPlanRepository;
import com.clashhorn.domain.service.WarPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import com.clashhorn.application.service.ClashOfClansService;
import com.clashhorn.domain.model.clan.ClanRef;
import org.springframework.stereotype.Service;

/**
 *
 * @author morgade
 */
@Service
public class WarPlanServiceImpl implements WarPlanService {
    @Autowired
    private WarPlanRepository warPlanRepository;
    @Autowired
    private ClashOfClansService clashAPIService;

    /**
     * {@inheritDoc}
     * @param clanAccountId
     * @return 
     */
    @Override
    public WarPlan getOrCreateCurrentWarPlan(String clanAccountId) {
        return new WarPlan(clanAccountId, clanAccountId, new ClanRef("#JKHGH", "TJF"), new ClanRef("#KHGH", "OUTRO"));
    }
    
}
