/*
 * Clash Horn - MIT License
 */
package com.clashhorn.application.clashapi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author morgade
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WarClanMember {

    private final String tag;
    private final String name;
    private final Integer townhallLevel;
    private final Integer mapPosition;
    private final Integer opponentAttacks;
    private final WarAttack[] attacks;

    @JsonCreator
    public WarClanMember(@JsonProperty("tag") String tag,
            @JsonProperty("name") String name,
            @JsonProperty("townhallLevel") Integer townhallLevel,
            @JsonProperty("mapPosition") Integer mapPosition,
            @JsonProperty("opponentAttacks") Integer opponentAttacks,
            @JsonProperty("attacks") WarAttack[] attacks) {
        this.tag = tag;
        this.name = name;
        this.townhallLevel = townhallLevel;
        this.mapPosition = mapPosition;
        this.opponentAttacks = opponentAttacks;
        this.attacks = attacks!=null ? attacks : new WarAttack[0];
    }

    public String getTag() {
        return tag;
    }

    public String getName() {
        return name;
    }

    public Integer getTownhallLevel() {
        return townhallLevel;
    }

    public Integer getMapPosition() {
        return mapPosition;
    }

    public Integer getOpponentAttacks() {
        return opponentAttacks;
    }

    public WarAttack[] getAttacks() {
        return attacks;
    }

}
