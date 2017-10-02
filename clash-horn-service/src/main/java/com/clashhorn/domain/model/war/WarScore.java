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
public class WarScore {
    private final int stars;
    private final float destruction;

    public WarScore(int stars, float destruction) {
        this.stars = stars;
        this.destruction = destruction;
    }

    public int getStars() {
        return stars;
    }

    public float getDestruction() {
        return destruction;
    }
}
