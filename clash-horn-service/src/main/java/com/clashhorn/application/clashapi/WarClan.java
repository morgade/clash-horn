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
public class WarClan {
    private final String tag;
    private final String name;
    private final BadgeUrls badgeUrls;
    private final Integer clanLevel;
    private final Integer attacks;
    private final Integer stars;
    private final Float destructionPercentage;
    private final Integer expEarned;
    private final WarClanMember[] members;
    
    @JsonCreator
    public WarClan(@JsonProperty("tag") String tag, 
                @JsonProperty("name") String name, 
                @JsonProperty("badgeUrls") BadgeUrls badgeUrls, 
                @JsonProperty("clanLevel") Integer clanLevel, 
                @JsonProperty("attacks") Integer attacks, 
                @JsonProperty("stars") Integer stars, 
                @JsonProperty("expEarned") Integer expEarned,
                @JsonProperty("destructionPercentage") Float destructionPercentage,
                @JsonProperty("members") WarClanMember[] members) {
        this.tag = tag;
        this.name = name;
        this.badgeUrls = badgeUrls;
        this.clanLevel = clanLevel;
        this.attacks = attacks;
        this.stars = stars;
        this.destructionPercentage = destructionPercentage;
        this.expEarned = expEarned;
        this.members = members;
}
    
    public String getTag() {
        return tag;
    }

    public String getName() {
        return name;
    }

    public BadgeUrls getBadgeUrls() {
        return badgeUrls;
    }

    public Integer getClanLevel() {
        return clanLevel;
    }

    public Integer getAttacks() {
        return attacks;
    }

    public Integer getStars() {
        return stars;
    }

    public Integer getExpEarned() {
        return expEarned;
    }

    public Float getDestructionPercentage() {
        return destructionPercentage;
    }
    
    public WarClanMember[] getMembers() {
        return members;
    }
}
