/*
 * Clash Horn - MIT License
 */
package com.clashhorn.application.service;

import com.clashhorn.application.clashapi.Clan;
import com.clashhorn.application.clashapi.War;

/**
 *
 * @author morgade
 * @author guilaaf
 */
public interface ClashOfClansService  {
    public static final String TAG_PREFIX = "#";    
    
    /**
     * Fetch currentWar data from Clash of Clans API
     * @param clanTag
     * @return 
     */
    public War currentWar(String clanTag);
    
    /**
     * Fetch clan data from Clash of Clans API
     * @param clanTag
     * @return 
     */
    public Clan clans(String clanTag);
    
    /**
     * Assures that a '#' character is set as a tag prefix
     * @param tag
     * @return 
     */
    default String fixTagPrefix(String tag) {
        if (tag.startsWith(TAG_PREFIX)) {
            return tag;
        } else {
            return TAG_PREFIX + tag;
        }
    }
}
