/*
 * Clash Horn - MIT License
 */
package com.clashhorn.domain.model.war;

import com.clashhorn.domain.shared.ValueObject;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.util.Assert;

/**
 * Enforces valid war position values and provides disambiguation
 * for using war position as values or 0-based list indexes
 *
 * @author morgade
 */
@ValueObject
public enum WarPosition {
    P01,
    P02,
    P03,
    P04,
    P05,
    P06,
    P07,
    P08,
    P09,
    P10,
    P11,
    P12,
    P13,
    P14,
    P15,
    P16,
    P17,
    P18,
    P19,
    P20,
    P21,
    P22,
    P23,
    P24,
    P25,
    P26,
    P27,
    P28,
    P29,
    P30,
    P31,
    P32,
    P33,
    P34,
    P35,
    P36,
    P37,
    P38,
    P39,
    P40,
    P41,
    P42,
    P43,
    P44,
    P45,
    P46,
    P47,
    P48,
    P49,
    P50;

    public static WarPosition fromValue(int positionValue) {
        Assert.isTrue(positionValue > 0 && positionValue < 51, String.format("Invalid position value: %d", positionValue));
        return values()[positionValue - 1];
    }

    @JsonValue
    public int asValue() {
        return ordinal()+1;
    }

    public int asIndex() {
        return ordinal();
    }

}
