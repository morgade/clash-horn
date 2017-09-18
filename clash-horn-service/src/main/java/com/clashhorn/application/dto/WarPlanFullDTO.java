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
    private ClanWarDTO clan;
    private ClanWarDTO opponent;
    private Integer size;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ClanWarDTO getClan() {
        return clan;
    }

    public void setClan(ClanWarDTO clan) {
        this.clan = clan;
    }

    public ClanWarDTO getOpponent() {
        return opponent;
    }

    public void setOpponent(ClanWarDTO opponent) {
        this.opponent = opponent;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
