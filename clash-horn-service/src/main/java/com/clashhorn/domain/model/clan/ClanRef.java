/*
 * Clash Horn - MIT License
 */
package com.clashhorn.domain.model.clan;

import com.clashhorn.domain.shared.AggregateRoot;

/**
 * Clan model entity
 * @author morgade
 * @author guilaaf
 */
@AggregateRoot
public class ClanRef {
    private String tag;
    private String name;
    private String badge;
    
    ClanRef() {
    }

    public ClanRef(String tag, String name, String badge) {
        this.tag = tag;
        this.name = name;
        this.badge = badge;
    }
    
    public String getTag() {
        return tag;
    }

    public String getName() {
        return name;
    }

    public String getBadge() {
        return badge;
    }
}
