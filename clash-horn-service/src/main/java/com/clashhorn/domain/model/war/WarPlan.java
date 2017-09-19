/*
 * Clash Horn - MIT License
 */
package com.clashhorn.domain.model.war;

import com.clashhorn.domain.model.clan.ClanRef;
import com.clashhorn.domain.shared.AggregateRoot;
import com.clashhorn.application.clashapi.WarAttack;
import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;

/**
 * WarPlan model entity
 * @author morgade
 */
@AggregateRoot
public class WarPlan {
    @Id
    private String id;
    private String clanAccountId;
    private ClanRef clan;
    private ClanRef enemy;
    private Date startTime;
    private Date preparationStartTime;
    private Date endTime;
    private List<WarPlayer> members;
    private List<WarPlayer> enemies;
    private List<List<WarPlayer>> attackQueues;
    private List<List<WarAttack>> performedAttacks;
    private List<List<WarAttack>> sufferedAttacks;

    WarPlan() {
    }
    
    public WarPlan(String id, String clanAccountId) {
        this.id = id;
        this.clanAccountId = clanAccountId;
    }

    public WarPlan(String id, String clanAccountId, ClanRef clan, ClanRef enemy) {
        this.id = id;
        this.clanAccountId = clanAccountId;
        this.clan = clan;
        this.enemy = enemy;
    }

    public WarPlan withClan(ClanRef clan) {
        this.clan = clan;
        return this;
    }

    public WarPlan withEnemy(ClanRef enemy) {
        this.enemy = enemy;
        return this;
    }

    public WarPlan withStartTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    public WarPlan withPreparationStartTime(Date preparationStartTime) {
        this.preparationStartTime = preparationStartTime;
        return this;
    }

    public WarPlan withEndTime(Date endTime) {
        this.endTime = endTime;
        return this;
    }

    public String getId() {
        return id;
    }

    public String getClanAccountId() {
        return clanAccountId;
    }

    public ClanRef getClan() {
        return clan;
    }

    public ClanRef getEnemy() {
        return enemy;
    }

    public List<WarPlayer> getMembers() {
        return members;
    }

    public List<WarPlayer> getEnemies() {
        return enemies;
    }

    public List<List<WarPlayer>> getAttackQueues() {
        return attackQueues;
    }

    public List<List<WarAttack>> getPerformedAttacks() {
        return performedAttacks;
    }

    public List<List<WarAttack>> getSufferedAttacks() {
        return sufferedAttacks;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getPreparationStartTime() {
        return preparationStartTime;
    }
}
