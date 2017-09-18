/*
 * Clash Horn - MIT License
 */
package com.clashhorn.domain.model.clan;

/**
 * Clan model entity
 * @author morgade
 */
public class ClanRef {
    private String tag;
    private String name;
    
    ClanRef() {
    }

    public ClanRef(String tag, String name) {
        this.tag = tag;
        this.name = name;
    }
    
    public String getTag() {
        return tag;
    }

    public String getName() {
        return name;
    }
    
}
