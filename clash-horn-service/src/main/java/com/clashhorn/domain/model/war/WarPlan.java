/*
 * Clash Horn - MIT License
 */
package com.clashhorn.domain.model.war;

import com.clashhorn.domain.model.clan.ClanRef;
import com.clashhorn.domain.shared.AggregateRoot;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import static java.util.stream.Collectors.toList;
import org.springframework.data.annotation.Id;
import org.springframework.util.Assert;


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
    private WarScore clanScore;
    private WarScore enemyScore;
    private Date startTime;
    private Date preparationStartTime;
    private Date endTime;
    private List<WarPlayer> members;
    private List<WarPlayer> enemies;
    private List<List<WarPlayer>> attackQueues;
    private List<WarPlanAttack> performedAttacks;
    private List<WarPlanAttack> sufferedAttacks;

    /**
     * Constructor used by spring data
     */
    WarPlan() {
    }
    
    /**
     * Constructor used by WarPlanBuilder
     * @param id
     * @param clanAccountId
     * @param clan
     * @param enemy
     * @param startTime
     * @param preparationStartTime
     * @param endTime
     * @param members
     * @param enemies
     * @param attackQueues
     * @param performedAttacks
     * @param sufferedAttacks 
     * @param clanScore 
     * @param enemyScore 
     */
    protected WarPlan(final String id, final String clanAccountId, final ClanRef clan, 
            final ClanRef enemy, final Date startTime, final Date preparationStartTime, 
            final Date endTime, final List<WarPlayer> members, final List<WarPlayer> enemies, 
            final List<List<WarPlayer>> attackQueues, final List<WarPlanAttack> performedAttacks, 
            final List<WarPlanAttack> sufferedAttacks, WarScore clanScore, WarScore enemyScore) {
        Assert.notNull(clanAccountId, "clanAccountId is mandatory");
        Assert.notNull(clan, "clan is mandatory");
        Assert.notNull(enemy, "enemy is mandatory");
        Assert.notNull(startTime, "startTime is mandatory");
        Assert.notNull(preparationStartTime, "preparationStartTime is mandatory");
        Assert.notNull(endTime, "endTime is mandatory");
        Assert.notNull(members, "members is mandatory");
        Assert.notNull(performedAttacks, "performedAttacks is mandatory");
        Assert.notNull(sufferedAttacks, "sufferedAttacks is mandatory");
        Assert.notNull(clanScore, "clanScore is mandatory");
        Assert.notNull(enemyScore, "enemyScore is mandatory");
        
        Assert.notNull(enemies.size()==members.size(), "enemies and member count must match");
        
        this.id = id.toUpperCase();
        this.clanAccountId = clanAccountId;
        this.clan = clan;
        this.enemy = enemy;
        this.startTime = startTime;
        this.preparationStartTime = preparationStartTime;
        this.endTime = endTime;
        this.members = members;
        this.enemies = enemies;
        this.enemyScore = enemyScore;
        this.clanScore = clanScore;
        this.performedAttacks = new ArrayList(performedAttacks);
        this.sufferedAttacks = new ArrayList(sufferedAttacks);
        this.attackQueues = attackQueues == null ? new ArrayList() : new ArrayList(attackQueues);
    }

    public void updateWithDataFrom(WarPlan currentWarPlanUpdatedData) {
        // TODO: Implement
    }
    
    public int getMapSize() {
        return members.size();
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
        return Collections.unmodifiableList(members);
    }

    public List<WarPlayer> getEnemies() {
        return Collections.unmodifiableList(enemies);
    }

    public List<WarPlayer> getAttackQueue(int position) {
        if (position>attackQueues.size()-1) {
            return Collections.EMPTY_LIST;
        }
        return attackQueues.get(position);
    }

    public List<WarPlanAttack> getPerformedAttacks(int mapPosition) {
        return performedAttacks.stream().filter(a->a.getDefender()==mapPosition).collect(toList());
    }

    public List<WarPlanAttack> getSufferedAttacks(int mapPosition) {
        return sufferedAttacks.stream().filter(a->a.getDefender()==mapPosition).collect(toList());
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getPreparationStartTime() {
        return preparationStartTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public WarScore getClanScore() {
        return clanScore;
    }

    public WarScore getEnemyScore() {
        return enemyScore;
    }

}
