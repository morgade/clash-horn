/*
 * Clash Horn - MIT License
 */
package com.clashhorn.domain.model.clan;

/**
 * Clan model entity
 * @author morgade
 */
public class Clan {
    private String tag;
    private String name;

    Clan() {
    }

    public Clan(String tag, String name) {
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
