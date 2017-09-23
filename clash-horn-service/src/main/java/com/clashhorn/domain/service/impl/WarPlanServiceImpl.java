/*
 * Clash Horn - MIT License
 */
package com.clashhorn.domain.service.impl;

import com.clashhorn.domain.model.war.WarPlan;
import com.clashhorn.domain.model.war.WarPlanRepository;
import com.clashhorn.domain.service.WarPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author morgade
 * @author guilaaf
 */
@Service
public class WarPlanServiceImpl implements WarPlanService {
    
    @Autowired
    private WarPlanRepository warPlanRepository;
    
    /**
     * {@inheritDoc}
     * @param currentWarPlanUpdatedData
     * @return 
     */
    @Override
    public WarPlan createOrUpdateCurrentWarPlan(WarPlan currentWarPlanUpdatedData) {
        // Find a matching warplan on the repository
        WarPlan warPlan = warPlanRepository.findByAccountAndPreparationTime(currentWarPlanUpdatedData.getClanAccountId(), currentWarPlanUpdatedData.getPreparationStartTime());
        
        if (warPlan!=null) {
            // Found a WarPlan matching with current war. This is it
            warPlan.updateWithDataFrom(currentWarPlanUpdatedData);
        } else {
            // No matches of current war. Save as a new war plan here
            warPlan = warPlanRepository.save(currentWarPlanUpdatedData);
        }
        return warPlan;
    }
}
