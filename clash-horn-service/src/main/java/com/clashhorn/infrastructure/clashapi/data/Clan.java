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
public class Clan {
    private final String tag;
    private final String name;
    private final String type;
    private final String description;
    private final Location location;
    private final BadgeUrls badgeUrls;
    private final Integer clanLevel;
    private final Integer clanPoints;
    private final Integer clanVersusPoints;
    private final Integer requiredTrophies;
    private final String warFrequency;
    private final Integer warWinStreak;
    private final Integer warWins;
    private final Integer warTies;
    private final Integer warLosses;
    private final Boolean isWarLogPublic;
    private final Integer members;
    private final ClanMember[] memberList;

    @JsonCreator
    public Clan(@JsonProperty("tag") String tag, 
                    @JsonProperty("name") String name, 
                    @JsonProperty("type") String type, 
                    @JsonProperty("description") String description, 
                    @JsonProperty("location") Location location, 
                    @JsonProperty("badgeUrls") BadgeUrls badgeUrls, 
                    @JsonProperty("clanLevel") Integer clanLevel, 
                    @JsonProperty("clanPoints") Integer clanPoints, 
                    @JsonProperty("clanVersusPoints") Integer clanVersusPoints, 
                    @JsonProperty("requiredTrophies") Integer requiredTrophies, 
                    @JsonProperty("warFrequency") String warFrequency, 
                    @JsonProperty("warWinStreak") Integer warWinStreak, 
                    @JsonProperty("warWins") Integer warWins, 
                    @JsonProperty("warTies") Integer warTies, 
                    @JsonProperty("warLosses") Integer warLosses, 
                    @JsonProperty("isWarLogPublic") Boolean isWarLogPublic, 
                    @JsonProperty("members") Integer members,
                    @JsonProperty("memberList") ClanMember[] memberList) {
        this.tag = tag;
        this.name = name;
        this.type = type;
        this.description = description;
        this.location = location;
        this.badgeUrls = badgeUrls;
        this.clanLevel = clanLevel;
        this.clanPoints = clanPoints;
        this.clanVersusPoints = clanVersusPoints;
        this.requiredTrophies = requiredTrophies;
        this.warFrequency = warFrequency;
        this.warWinStreak = warWinStreak;
        this.warWins = warWins;
        this.warTies = warTies;
        this.warLosses = warLosses;
        this.isWarLogPublic = isWarLogPublic;
        this.members = members;
        this.memberList = memberList;
    }

    public String getTag() {
        return tag;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public Location getLocation() {
        return location;
    }

    public BadgeUrls getBadgeUrls() {
        return badgeUrls;
    }

    public Integer getClanLevel() {
        return clanLevel;
    }

    public Integer getClanPoints() {
        return clanPoints;
    }

    public Integer getClanVersusPoints() {
        return clanVersusPoints;
    }

    public Integer getRequiredTrophies() {
        return requiredTrophies;
    }

    public String getWarFrequency() {
        return warFrequency;
    }

    public Integer getWarWinStreak() {
        return warWinStreak;
    }

    public Integer getWarWins() {
        return warWins;
    }

    public Integer getWarTies() {
        return warTies;
    }

    public Integer getWarLosses() {
        return warLosses;
    }

    public Boolean getIsWarLogPublic() {
        return isWarLogPublic;
    }

    public Integer getMembers() {
        return members;
    }

    public ClanMember[] getMemberList() {
        return memberList;
    }
     
}
