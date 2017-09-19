/*
 * Clash Horn - MIT License
 */
package com.clashhorn.application.service;

import com.clashhorn.application.dto.ClanAccountDTO;
import com.clashhorn.application.dto.ClanFullDTO;
import com.clashhorn.application.dto.WarPlanFullDTO;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import java.util.Map;

/**
 *
 * @author morgade
 */
@JsonRpcService("/api/clash-horn")
public interface ClashHornService {
    /**
     * Fetch clan data by tag
     * @param tag
     * @return 
     */
    ClanFullDTO fethClanData(@JsonRpcParam("tag") String tag);
    
    /**
     * Register a new clanAccount
     * @param clanAccountId Optional user defined accountId
     * @param tag Associated clan tag
     * @return 
     */
    ClanAccountDTO registerClanAccount(@JsonRpcParam("tag") String tag, @JsonRpcParam("clanAccountId") String clanAccountId);
    
    /**
     * Fetch clan data by clanAccountId
     * @param clanAccountId
     * @return 
     */
    ClanAccountDTO fetchClanAccount(@JsonRpcParam("clanAccountId") String clanAccountId);
    
    /**
     * Fetch a war plan from an accountId with <pre>warPlanId</pre>. If provided <pre>warPlanId</pre> 
     * is null, this method will fetch the current war as stated by CoC API (creating a new war 
     * plan for the current war if it not exists).
     * @param clanAccountId
     * @param warPlanId  
     *                  
     * @return 
     */
    WarPlanFullDTO fetchWarPlan(@JsonRpcParam("clanAccountId") String clanAccountId, @JsonRpcParam("warPlanId") String warPlanId);
    
    
    /**
     * Fetch service status data
     * @return 
     */
    Map status();
}