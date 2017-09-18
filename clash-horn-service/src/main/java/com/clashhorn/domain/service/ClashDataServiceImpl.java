/*
 * Clash Horn - MIT License
 */
package com.clashhorn.domain.service;

import com.clashhorn.domain.model.account.ClanAccount;
import com.clashhorn.domain.model.account.ClanAccountRepository;
import com.clashhorn.domain.model.clan.ClanRef;
import com.clashhorn.infrastructure.clashapi.ClashAPIService;
import com.clashhorn.infrastructure.clashapi.data.Clan;
import static java.lang.String.format;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author morgade
 */
@Service
public class ClashDataServiceImpl implements ClashHornService {
    @Autowired
    private ClanAccountRepository clanAccountRepository;
    @Autowired
    private ClashAPIService clashAPIService;
    
    @Override
    public ClanAccount registerNewClanAccount(String clanAccountId, String clanTag) {
        if (clanAccountRepository.exists(clanAccountId)) {
            throw new IllegalStateException(format("Account id '%s' is already registered",clanAccountId));
        }
        
        Clan clan = clashAPIService.clans(clanTag);
        ClanAccount clanAccount = new ClanAccount(clanAccountId, new ClanRef(clan.getTag(), clan.getName()));
        
        return clanAccountRepository.save(clanAccount);
    }

}
