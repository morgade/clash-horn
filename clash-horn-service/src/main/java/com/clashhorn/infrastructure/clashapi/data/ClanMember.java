/*
 * Clash Horn - MIT License
 */
package com.clashhorn.infrastructure.clashapi.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author morgade
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClanMember {
    private final String tag;
    private final String name;
    private final String role;
    private final Integer expLevel;
    private final Integer trophies;
    private final Integer versusTrophies;
    private final Integer clanRank;
    private final Integer previousClanRank;
    private final Integer donations;
    private final Integer donationsReceived;

    @JsonCreator
    public ClanMember(@JsonProperty("tag") String tag, 
                @JsonProperty("name") String name, 
                @JsonProperty("role") String role,
                @JsonProperty("expLevel") Integer expLevel,
                @JsonProperty("trophies") Integer trophies,
                @JsonProperty("versusTrophies") Integer versusTrophies,
                @JsonProperty("clanRank") Integer clanRank,
                @JsonProperty("previousClanRank") Integer previousClanRank,
                @JsonProperty("donations") Integer donations,
                @JsonProperty("donationsReceived") Integer donationsReceived) {
        this.tag = tag;
        this.name = name;
        this.role = role;
        this.expLevel = expLevel;
        this.trophies = trophies;
        this.versusTrophies = versusTrophies;
        this.clanRank = clanRank;
        this.previousClanRank = previousClanRank;
        this.donations = donations;
        this.donationsReceived = donationsReceived;
    }

    
    public String getTag() {
        return tag;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public Integer getExpLevel() {
        return expLevel;
    }

    public Integer getVersusTrophies() {
        return versusTrophies;
    }

    public Integer getClanRank() {
        return clanRank;
    }

    public Integer getPreviousClanRank() {
        return previousClanRank;
    }

    public Integer getDonations() {
        return donations;
    }

    public Integer getDonationsReceived() {
        return donationsReceived;
    }

    public Integer getTrophies() {
        return trophies;
    }

}
