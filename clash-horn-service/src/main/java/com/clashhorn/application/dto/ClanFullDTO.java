/*
 * Clash Horn - MIT License
 */
package com.clashhorn.application.dto;

/**
 *
 * @author morgade
 */
public class ClanFullDTO {
    private String tag;
    private String name;
    private String type;
    private String description;
    private LocationDTO location;
    private BadgeUrlsDTO badgeUrls;
    private Integer clanLevel;
    private Integer clanPoints;
    private Integer clanVersusPoints;
    private Integer requiredTrophies;
    private String warFrequency;
    private Integer warWinStreak;
    private Integer warWins;
    private Integer warTies;
    private Integer warLosses;
    private Boolean isWarLogPublic;
    private Integer members;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    public BadgeUrlsDTO getBadgeUrls() {
        return badgeUrls;
    }

    public void setBadgeUrls(BadgeUrlsDTO badgeUrls) {
        this.badgeUrls = badgeUrls;
    }

    public Integer getClanLevel() {
        return clanLevel;
    }

    public void setClanLevel(Integer clanLevel) {
        this.clanLevel = clanLevel;
    }

    public Integer getClanPoints() {
        return clanPoints;
    }

    public void setClanPoints(Integer clanPoints) {
        this.clanPoints = clanPoints;
    }

    public Integer getClanVersusPoints() {
        return clanVersusPoints;
    }

    public void setClanVersusPoints(Integer clanVersusPoints) {
        this.clanVersusPoints = clanVersusPoints;
    }

    public Integer getRequiredTrophies() {
        return requiredTrophies;
    }

    public void setRequiredTrophies(Integer requiredTrophies) {
        this.requiredTrophies = requiredTrophies;
    }

    public String getWarFrequency() {
        return warFrequency;
    }

    public void setWarFrequency(String warFrequency) {
        this.warFrequency = warFrequency;
    }

    public Integer getWarWinStreak() {
        return warWinStreak;
    }

    public void setWarWinStreak(Integer warWinStreak) {
        this.warWinStreak = warWinStreak;
    }

    public Integer getWarWins() {
        return warWins;
    }

    public void setWarWins(Integer warWins) {
        this.warWins = warWins;
    }

    public Integer getWarTies() {
        return warTies;
    }

    public void setWarTies(Integer warTies) {
        this.warTies = warTies;
    }

    public Integer getWarLosses() {
        return warLosses;
    }

    public void setWarLosses(Integer warLosses) {
        this.warLosses = warLosses;
    }

    public Boolean getIsWarLogPublic() {
        return isWarLogPublic;
    }

    public void setIsWarLogPublic(Boolean isWarLogPublic) {
        this.isWarLogPublic = isWarLogPublic;
    }

    public Integer getMembers() {
        return members;
    }

    public void setMembers(Integer members) {
        this.members = members;
    }
    
}
