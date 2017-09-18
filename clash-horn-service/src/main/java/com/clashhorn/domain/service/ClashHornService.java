/*
 * Clash Horn - MIT License
 */
package com.clashhorn.domain.service;

import com.clashhorn.domain.model.account.ClanAccount;
import com.clashhorn.domain.model.war.WarPlan;
import java.util.UUID;

/**
 *
 * @author morgade
 */
public interface ClashHornService {
    ClanAccount registerNewClanAccount(String clanAccountId, String clanTag);
}
