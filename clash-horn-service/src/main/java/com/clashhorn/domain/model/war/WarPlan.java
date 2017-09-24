/*
 * Clash Horn - MIT License
 */
package com.clashhorn.domain.model.war;

import com.clashhorn.domain.model.clan.ClanRef;
import com.clashhorn.domain.shared.AggregateRoot;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import static java.util.stream.Collectors.toList;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.springframework.data.annotation.Id;
import org.springframework.util.Assert;


/**
 * WarPlan model entity
 * @author morgade
 * @author guilaaf
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
    private Date preparationStartTime;
    private Date startTime;
    private Date endTime;
    private List<WarPlayer> members;
    private List<WarPlayer> enemies;
    private List<List<String>> tags;
    private List<List<PlannedAttack>> attackQueues;
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
     * @param preparationStartTime
     * @param startTime
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
            final List<List<PlannedAttack>> attackQueues, final List<WarPlanAttack> performedAttacks, 
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
        Assert.isTrue(!enemies.stream().anyMatch(l->l==null), "Invalid enemies null content");
        Assert.isTrue(!members.stream().anyMatch(l->l==null), "Invalid members null content");
        
        this.id = id.toUpperCase();
        this.clanAccountId = clanAccountId;
        this.clan = clan;
        this.enemy = enemy;
        this.startTime = startTime;
        this.preparationStartTime = preparationStartTime;
        this.endTime = endTime;
        this.members = members;
        this.enemies = enemies;
        this.clanScore = clanScore;
        this.enemyScore = enemyScore;
        this.performedAttacks = new ArrayList(performedAttacks);
        this.sufferedAttacks = new ArrayList(sufferedAttacks);
        if (attackQueues!=null) {
            Assert.isTrue(attackQueues.size()==members.size(), "Invalid attackQueue size");
            Assert.isTrue(!attackQueues.stream().anyMatch(l->l==null), "Invalid attackQueue null content");
            this.attackQueues = attackQueues;
        } else {
            this.attackQueues = new ArrayList<>();
            this.members.stream().forEach(m -> this.attackQueues.add(new LinkedList<>()));
        }
    }

    public void updateWithDataFrom(WarPlan currentWarPlanUpdatedData) {
        this.clanScore = currentWarPlanUpdatedData.getClanScore();
        this.enemyScore = currentWarPlanUpdatedData.getEnemyScore();
        this.performedAttacks = new ArrayList(currentWarPlanUpdatedData.performedAttacks);
        this.sufferedAttacks = new ArrayList(currentWarPlanUpdatedData.sufferedAttacks);
    }
    
    public void addPlannedAttack(WarPosition enemyPosition, WarPosition memberPosition) {
        Assert.isTrue(!this.getAttackQueue(enemyPosition).stream().anyMatch(a -> a.getAttacker()==memberPosition), "this member is already in the queue");
        
        
        List<WarPlanAttack> memberPerformedAttacks = this.getPerformedAttacks(memberPosition);
        Assert.isTrue(memberPerformedAttacks.size() < 2, "this member already have 2 attacks in this war");
        Assert.isTrue(memberPerformedAttacks.stream().anyMatch(a -> a.getDefender()== enemyPosition), "this member already attacked that enemy");
        
        int lastOrder = this.performedAttacks.size() > 0 ? this.performedAttacks.get(this.performedAttacks.size() - 1).getOrder() : 0;
        this.getAttackQueue(enemyPosition).add(new PlannedAttack(memberPosition, lastOrder));
    }
    
    public int getMapSize() {
        return members.size();
    }
    
    public Stream<WarPosition> positions() {
        return IntStream
                .rangeClosed(1, members.size())
                .mapToObj(WarPosition::fromValue);
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
    
    public WarPlayer getMember(WarPosition pos) {
        return members.get(pos.asIndex());
    }

    public List<WarPlayer> getEnemies() {
        return Collections.unmodifiableList(enemies);
    }
    
    public WarPlayer getEnemy(WarPosition pos) {
        return enemies.get(pos.asIndex());
    }
    
    public List<PlannedAttack> getAttackQueue(WarPosition position) {
        return attackQueues.get(position.asIndex());
    }

    public List<WarPlanAttack> getPerformedAttacks(WarPosition mapPosition) {
        return performedAttacks.stream().filter(a->a.getDefender()==mapPosition).collect(toList());
    }

    public List<WarPlanAttack> getSufferedAttacks(WarPosition mapPosition) {
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
