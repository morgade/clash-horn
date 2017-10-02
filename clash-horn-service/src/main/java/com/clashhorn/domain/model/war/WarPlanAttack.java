/*
 * Clash Horn - MIT License
 */
package com.clashhorn.domain.model.war;

import com.clashhorn.domain.shared.ValueObject;

/**
 *
 * @author morgade
 */
@ValueObject
public class WarPlanAttack {
    private int attacker;
    private int defender;
    private int stars;
    private int order;
    private float destructionPercentage;

    WarPlanAttack() {
    }

    public WarPlanAttack(WarPosition attacker, WarPosition defender, int stars, int order, float destructionPercentage) {
        this.attacker = attacker.asValue();
        this.defender = defender.asValue();
        this.stars = stars;
        this.order = order;
        this.destructionPercentage = destructionPercentage;
    }

    public WarPosition getAttacker() {
        return WarPosition.fromValue(attacker);
    }

    public WarPosition getDefender() {
        return WarPosition.fromValue(defender);
    }

    public int getStars() {
        return stars;
    }

    public int getOrder() {
        return order;
    }

    public float getDestructionPercentage() {
        return destructionPercentage;
    }

    
    
    
}
