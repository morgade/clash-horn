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

    public WarPlanAttack(int attacker, int defender, int stars, int order, float destructionPercentage) {
        this.attacker = attacker;
        this.defender = defender;
        this.stars = stars;
        this.order = order;
        this.destructionPercentage = destructionPercentage;
    }

    public int getAttacker() {
        return attacker;
    }

    public int getDefender() {
        return defender;
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
