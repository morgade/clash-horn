/*
 * Clash Horn - MIT License
 */
package com.clashhorn.domain.model.war;

import com.clashhorn.domain.model.account.ClanAccount;
import com.clashhorn.domain.model.clan.Clan;
import com.clashhorn.domain.shared.AggregateRoot;
import com.clashhorn.infrastructure.clashapi.data.WarAttack;
import java.util.List;
import java.util.UUID;

/**
 * War model entity
 * @author morgade
 */
@AggregateRoot
public class WarPlan {
    private ClanAccount account;
    private UUID id;
    private Clan clan;
    private Clan enemy;
    private List<WarPlayer> members;
    private List<WarPlayer> enemies;
    private List<List<WarPlayer>> attackQueues;
    private List<List<WarAttack>> performedAttacks;
    private List<List<WarAttack>> sufferedAttacks;
    

    WarPlan() {

    }

    public WarPlan(Clan clan, Clan enemy) {
        this.id = UUID.randomUUID();
        this.clan = clan;
        this.enemy = enemy;
    }

    public UUID getId() {
        return id;
    }

    

}