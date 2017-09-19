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
     * Save into repository a warPlan containing data for a current war.
     * If there's a match for this data already into the repository (same clanAccountId and preparationStartTime),
     * update it with the provided data
     * @param currentWarPlanUpdatedData
     * @return 
     */
    WarPlan createOrUpdateCurrentWarPlan(WarPlan currentWarPlanUpdatedData);
    
}
