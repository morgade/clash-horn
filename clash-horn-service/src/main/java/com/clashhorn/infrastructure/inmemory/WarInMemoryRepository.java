package com.clashhorn.infrastructure.inmemory;

import com.clashhorn.domain.model.war.WarPlan;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.clashhorn.domain.model.war.WarRepository;
import java.util.UUID;

/**
 *
 * @author x4rb
 */
@Repository
public class WarInMemoryRepository implements WarRepository {
    private final Map<UUID, WarPlan> wars = new LinkedHashMap<>();

    @Override
    public void add(WarPlan war) {
        wars.put(war.getId(), war);
    }

    @Override
    public WarPlan get(UUID warId) {
        return wars.get(warId);
    }
    
}
