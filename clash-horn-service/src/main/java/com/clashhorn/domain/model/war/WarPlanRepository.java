package com.clashhorn.domain.model.war;

import java.util.Date;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * War entity repository interface
 * @author morgade
 */
public interface WarPlanRepository extends MongoRepository<WarPlan, String> {
    @Query("{ 'clanAccountId':?0, 'preparationStartTime': ?1 }")
    WarPlan findByAccountAndPreparationTime(String clanAccount, Date preparationStartTime);
}
