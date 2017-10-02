/*
 * Clash Horn - MIT License
 */
package com.clashhorn.application.dto;

import java.util.Date;

/**
 *
 * @author morgade
 */
public class ClanAccountDTO {
    private String id;
    private Date creation;
    private ClanRefDTO clan;

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

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }
    
}
