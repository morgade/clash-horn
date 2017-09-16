/*
 * Clash Horn - MIT License
 */
package com.clashhorn.infrastructure.clashapi;

import com.clashhorn.infrastructure.clashapi.data.Clan;
import com.clashhorn.infrastructure.clashapi.data.War;

/**
 *
 * @author morgade
 * @author guilaaf
 */
public interface ClashAPIService  {
    public static final String TAG_PREFIX = "#";
    public static final String RESOURCE_CURRENT_WAR = "clans/{clanTag}/currentwar";
    public static final String RESOURCE_CLANS = "clans/{clanTag}";
    public static final String HEADER_AUTH_NAME = "Authorization";
    public static final String HEADER_AUTH_VALUE_PREFIX = "Bearer ";
    
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
