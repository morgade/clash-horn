/*
 * Clash Horn - MIT License
 */
package com.clashhorn.application.dto;

/**
 *
 * @author morgade
 */
public class WarPlanFullDTO {
    private String id;
    private ClanRefDTO clan;
    private ClanRefDTO enemy;
    private Integer size;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ClanRefDTO getClan() {
        return clan;
    }

    public void setClan(ClanRefDTO clan) {
        this.clan = clan;
    }

    public ClanRefDTO getEnemy() {
        return enemy;
    }

    public void setEnemy(ClanRefDTO enemy) {
        this.enemy = enemy;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
