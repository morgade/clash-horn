/*
 * Clash Horn - MIT License
 */
package com.clashhorn.application.dto;

/**
 *
 * @author morgade
 */
public class ClanWarDTO {
    private String tag;
    private String name;
    private BadgeUrlsDTO badgeUrls;
    private Integer clanLevel;
    private Integer attacks;
    private Integer stars;
    private Float destructionPercentage;

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

    public Integer getAttacks() {
        return attacks;
    }

    public void setAttacks(Integer attacks) {
        this.attacks = attacks;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Float getDestructionPercentage() {
        return destructionPercentage;
    }

    public void setDestructionPercentage(Float destructionPercentage) {
        this.destructionPercentage = destructionPercentage;
    }

}
