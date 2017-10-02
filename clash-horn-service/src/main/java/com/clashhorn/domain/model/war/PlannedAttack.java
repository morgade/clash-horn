/*
 * Clash Horn - MIT License
 */
package com.clashhorn.domain.model.war;

import com.clashhorn.domain.shared.ValueObject;

/**
 *
 * @author guilaaf
 */
@ValueObject
public class PlannedAttack {
    private int attacker;
    // order of the last attack performed
    private int order;

    public PlannedAttack() {
    }

    public PlannedAttack(WarPosition attacker, int order) {
        this.attacker = attacker.asValue();
        this.order = order;
    }

    public WarPosition getAttacker() {
        return WarPosition.fromValue(attacker);
    }

    public int getOrder() {
        return order;
    }
}
