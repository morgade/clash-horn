/*
 * Clash Horn - MIT License
 */
package com.clashhorn.application.service;

import com.clashhorn.application.dto.ClanAccountDTO;
import com.clashhorn.application.dto.ClanFullDTO;
import com.clashhorn.application.dto.WarPlanFullDTO;
import com.clashhorn.domain.model.account.ClanAccount;
import com.clashhorn.domain.model.account.ClanAccountRepository;
import com.clashhorn.domain.model.war.WarPlan;
import com.clashhorn.domain.model.war.WarPlanRepository;
import com.clashhorn.application.clashapi.Clan;
import com.clashhorn.application.clashapi.War;
import com.clashhorn.application.clashapi.WarClanMember;
import com.clashhorn.domain.model.clan.ClanRef;
import com.clashhorn.domain.model.war.WarPlanAttack;
import com.clashhorn.domain.model.war.WarPlanBuilder;
import com.clashhorn.domain.model.war.WarPlayer;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import com.clashhorn.domain.service.ClanAccountService;
import com.clashhorn.domain.service.WarPlanService;
import static java.util.Comparator.comparing;
import java.util.UUID;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;

/**
 *
 * @author morgade
 */
@Service
@AutoJsonRpcServiceImpl
public class ClashHornServiceImpl implements ClashHornService {
    
    @Autowired
    @Qualifier("dtoConversionService")
    private ConversionService converter;
    
    @Autowired
    private ClashOfClansService clashOfClansService;
    
    @Autowired
    private ClanAccountService clashHornService;
    
    @Autowired
    private WarPlanRepository warPlanRepository;
    
    @Autowired
    private WarPlanService warPlanService;
    
    @Autowired
    private ClanAccountRepository clanAccountRepository;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ClanFullDTO fethClanData(String tag) {
        Clan clan = clashOfClansService.clans(tag);
        return converter.convert(clan, ClanFullDTO.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ClanAccountDTO fetchClanAccount(String clanAccountId) {
        ClanAccount clanAccount = clanAccountRepository.findOne(clanAccountId);
        return converter.convert(clanAccount, ClanAccountDTO.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ClanAccountDTO registerClanAccount(String tag, String clanAccountId) {
        ClanAccount clanAccount = clashHornService.registerNewClanAccount(clanAccountId, tag);
        return converter.convert(clanAccount, ClanAccountDTO.class);
    }
    
        
    /**
     * {@inheritDoc}
     */
    @Override
    public WarPlanFullDTO fetchWarPlan(String clanAccountId, String warPlanId) {
        WarPlan warPlan;
        if (warPlanId!=null) {
            // warPlanId provided. Just get a warPlan from the repository assuring it belongs to provided clanAccountId
            warPlan = warPlanRepository.findByIdAndClanAccountId(warPlanId, clanAccountId);
        } else {
            // warPlanId not provided. Assumes a request for the current war
            ClanAccount clanAccount = clanAccountRepository.findOne(clanAccountId);
            // Fetch current war on CoC API to verify matches and updates for a possible repository warplan
            // TODO: Maybe optimize and reduce calls to clashOfClansService ? Example: search repository first foar a walPlan where endTime is in the future ?
            War war = clashOfClansService.currentWar(clanAccount.getClan().getTag());
            warPlan = warPlanService.createOrUpdateCurrentWarPlan(createWarPlanFromCoCWar(war, clanAccountId));
        }
        return converter.convert(warPlan, WarPlanFullDTO.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Map status() {
        return new HashMap() {{
            put("freeHeap", Runtime.getRuntime().freeMemory());
        }};
    }
    
    /**
     * Creates a WarPlan based on CoC API war data
     * @param war
     * @return 
     */
    protected WarPlan createWarPlanFromCoCWar(War war, String clanAccountId) {
        String id = UUID.randomUUID().toString();
        return
            WarPlanBuilder.builder(id)
                .clanAccountId(clanAccountId)
                .clan(new ClanRef(war.getClan().getTag(), war.getClan().getName()))
                .enemy(new ClanRef(war.getOpponent().getTag(), war.getOpponent().getName()))
                .preparationStartTime(war.getPreparationStartTime())
                .startTime(war.getStartTime())
                .endTime(war.getEndTime())
                
                .members( Stream.of(war.getClan().getMembers())
                            .sorted(comparing(WarClanMember::getMapPosition))
                            .map(m -> new WarPlayer(m.getTag(), m.getName(), m.getMapPosition(), m.getTownhallLevel()))
                            .collect(toList()))
                
                .enemies( Stream.of(war.getOpponent().getMembers())
                            .sorted(comparing(WarClanMember::getMapPosition))
                            .map(m -> new WarPlayer(m.getTag(), m.getName(), m.getMapPosition(), m.getTownhallLevel()))
                            .collect(toList()))
                
                .performedAttacks( 
                        Stream.of(war.getClan().getMembers())
                                .flatMap(
                                        m -> Stream.of(m.getAttacks())                                                    
                                                .map( a -> new WarPlanAttack(m.getMapPosition(), 
                                                                war.getOpponent(a.getDefenderTag()).getMapPosition(), 
                                                                a.getStars(), a.getOrder(), 
                                                                a.getDestructionPercentage()))
                                ).collect(toList())
                )
                
                .sufferedAttacks( 
                        Stream.of(war.getOpponent().getMembers())
                                .flatMap(
                                        m -> Stream.of(m.getAttacks())                                                 
                                                .map( a -> new WarPlanAttack(m.getMapPosition(), 
                                                                war.getMember(a.getDefenderTag()).getMapPosition(), 
                                                                a.getStars(), a.getOrder(), 
                                                                a.getDestructionPercentage()))
                                ).collect(toList())
                )
                
                .build();
    }

    
}