/*
 * Clash Horn - MIT License
 */
package com.clashhorn.domain.service;

import com.clashhorn.domain.model.account.ClanAccount;

/**
 *
 * @author morgade
 */
public interface ClanAccountService {
    /**
     * Register a new ClanAccount with a unique id
     * @param clanAccountId
     * @param clanTag
     * @return 
     */
    ClanAccount registerNewClanAccount(String clanAccountId, String clanTag);
}
