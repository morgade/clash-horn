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
public class WarAttack {
    private final String attackerTag;
    private final String defenderTag;
    private final Integer stars;
    private final Float destructionPercentage;
    private final Integer order;

    @JsonCreator
    public WarAttack(@JsonProperty("attackerTag") String attackerTag, 
            @JsonProperty("defenderTag") String defenderTag, 
            @JsonProperty("stars") Integer stars, 
            @JsonProperty("destructionPercentage") Float destructionPercentage, 
            @JsonProperty("order") Integer order) {
        this.attackerTag = attackerTag;
        this.defenderTag = defenderTag;
        this.stars = stars;
        this.destructionPercentage = destructionPercentage;
        this.order = order;
    }

    public String getAttackerTag() {
        return attackerTag;
    }

    public String getDefenderTag() {
        return defenderTag;
    }

    public Integer getStars() {
        return stars;
    }

    public Float getDestructionPercentage() {
        return destructionPercentage;
    }

    public Integer getOrder() {
        return order;
    }
    
    
}
