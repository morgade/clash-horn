/*
 * Clash Horn - MIT License
 */
package com.clashhorn.application.dto;

/**
 *
 * @author morgade
 */
public class ClanAccountDTO {
    private String id;
    private ClanBasicDTO clan;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ClanBasicDTO getClan() {
        return clan;
    }

    public void setClan(ClanBasicDTO clan) {
        this.clan = clan;
    }
    
    
}
