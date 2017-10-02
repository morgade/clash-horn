/*
 * Clash Horn - MIT License
 */
package com.clashhorn.domain.service.impl;

import com.clashhorn.domain.model.account.ClanAccount;
import com.clashhorn.domain.model.account.ClanAccountRepository;
import com.clashhorn.domain.model.clan.ClanRef;
import com.clashhorn.domain.service.ClanAccountService;
import com.clashhorn.application.clashapi.Clan;
import static java.lang.String.format;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clashhorn.application.service.ClashOfClansService;

/**
 *
 * @author morgade
 */
@Service
public class ClanAccountServiceImpl implements ClanAccountService {
    @Autowired
    private ClanAccountRepository clanAccountRepository;
    @Autowired
    private ClashOfClansService clashAPIService;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ClanAccount registerNewClanAccount(String clanAccountId, String clanTag) {
        if (clanAccountRepository.exists(clanAccountId)) {
            throw new IllegalStateException(format("Account id '%s' is already registered",clanAccountId));
        }
        
        Clan clan = clashAPIService.clans(clanTag);
        ClanAccount clanAccount = new ClanAccount(clanAccountId, new ClanRef(clan.getTag(), clan.getName(), clan.getBadgeUrls().getSmall()));
        
        return clanAccountRepository.save(clanAccount);
    }

}
