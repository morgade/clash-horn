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
public class WarAttack {
    private WarPlayer attacker;
    private Integer stars;
    private Float destructionPercentage;

    WarAttack() {
    }

    public WarAttack(WarPlayer attacker) {
        this.attacker = attacker;
    }

    public WarPlayer getAttacker() {
        return attacker;
    }

    public Integer getStars() {
        return stars;
    }

    public Float getDestructionPercentage() {
        return destructionPercentage;
    }
    
    public boolean isPerformed() {
        return stars != null;
    }
    
}
