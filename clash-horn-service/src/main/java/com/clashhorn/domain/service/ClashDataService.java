/*
 * Clash Horn - MIT License
 */
package com.clashhorn.domain.service;

import com.clashhorn.domain.model.war.WarPlan;
import java.util.UUID;

/**
 *
 * @author morgade
 */
public interface ClashDataService {
    public WarPlan createWarPlan(String clanTag);
    public WarPlan getWarPlan(UUID id);
}
