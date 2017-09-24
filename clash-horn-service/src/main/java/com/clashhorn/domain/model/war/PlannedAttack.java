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

    public PlannedAttack(int attacker, int order) {
        this.attacker = attacker;
        this.order = order;
    }

    public int getAttacker() {
        return attacker;
    }

    public int getOrder() {
        return order;
    }
}
