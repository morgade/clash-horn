/*
 * Clash Horn - MIT License
 */
package com.clashhorn.domain.model.war;

import com.clashhorn.domain.shared.ValueObject;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 *
 * @author morgade
 */
@ValueObject
public enum WarResult {
    PREPARATION,
    IN_PROGRESS,
    DRAW,
    VICTORY,
    DEFEAT;
    
    @JsonValue
    public int toValue() {
        return ordinal();
    }

}
