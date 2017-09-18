package com.clashhorn.domain.model.war;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * War entity repository interface
 * @author morgade
 */
public interface WarPlanRepository extends MongoRepository<WarPlan, String> {
}
