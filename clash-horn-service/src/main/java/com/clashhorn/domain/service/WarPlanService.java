/*
 * Clash Horn - MIT License
 */
package com.clashhorn.domain.service;

import com.clashhorn.domain.model.war.WarPlan;

/**
 *
 * @author morgade
 */
public interface WarPlanService {
    /**
     * Fetch the current war as stated by CoC API (creating a new war 
     * plan for the current war if it not exists).
     * @param clanAccountId
     * @return 
     */
    WarPlan getOrCreateCurrentWarPlan(String clanAccountId);
}
