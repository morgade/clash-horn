/*
 * Clash Horn - MIT License
 */
package com.clashhorn.domain.model.clan;

import com.clashhorn.domain.shared.AggregateRoot;

/**
 * Clan model entity
 * @author morgade
 */
@AggregateRoot
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
