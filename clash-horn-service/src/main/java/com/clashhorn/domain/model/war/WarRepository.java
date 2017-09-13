package com.clashhorn.domain.model.war;

import java.util.UUID;

/**
 * War entity repository interface
 * @author morgade
 */
public interface WarRepository {
    void add(WarPlan war);
    WarPlan get(UUID id);
}
