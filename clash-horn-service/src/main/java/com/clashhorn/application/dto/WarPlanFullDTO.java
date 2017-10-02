/*
 * Clash Horn - MIT License
 */
package com.clashhorn.application.dto;

import com.clashhorn.domain.model.war.PlannedAttack;
import com.clashhorn.domain.model.war.WarPlanAttack;
import com.clashhorn.domain.model.war.WarPlayer;
import com.clashhorn.domain.model.war.WarResult;
import com.clashhorn.domain.model.war.WarScore;
import java.util.Date;
import java.util.List;

/**
 *
 * @author morgade
 */
public class WarPlanFullDTO {
    private String id;
    private WarResult result;
    private ClanRefDTO clan;
    private ClanRefDTO enemy;
    private WarScore clanScore;
    private WarScore enemyScore;
    private Date preparationStartTime;
    private Date startTime;
    private Date endTime;
    private int mapSize;
    private List<Position> positions;
    
    public static class Position {
        private int number;
        private WarPlayer member;
        private WarPlayer enemy;
        private List<WarPlanAttack> performedAttacks;
        private List<WarPlanAttack> sufferedAttacks;
        private List<PlannedAttack> attackQueue;

        public WarPlayer getMember() {
            return member;
        }

        public void setMember(WarPlayer member) {
            this.member = member;
        }

        public WarPlayer getEnemy() {
            return enemy;
        }

        public void setEnemy(WarPlayer enemy) {
            this.enemy = enemy;
        }

        public List<WarPlanAttack> getPerformedAttacks() {
            return performedAttacks;
        }

        public void setPerformedAttacks(List<WarPlanAttack> performedAttacks) {
            this.performedAttacks = performedAttacks;
        }

        public List<WarPlanAttack> getSufferedAttacks() {
            return sufferedAttacks;
        }

        public void setSufferedAttacks(List<WarPlanAttack> sufferedAttacks) {
            this.sufferedAttacks = sufferedAttacks;
        }

        public List<PlannedAttack> getAttackQueue() {
            return attackQueue;
        }

        public void setAttackQueue(List<PlannedAttack> attackQueue) {
            this.attackQueue = attackQueue;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public WarResult getResult() {
        return result;
    }

    public void setResult(WarResult result) {
        this.result = result;
    }

    public Date getPreparationStartTime() {
        return preparationStartTime;
    }

    public void setPreparationStartTime(Date preparationStartTime) {
        this.preparationStartTime = preparationStartTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getMapSize() {
        return mapSize;
    }

    public void setMapSize(int mapSize) {
        this.mapSize = mapSize;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public ClanRefDTO getClan() {
        return clan;
    }

    public void setClan(ClanRefDTO clan) {
        this.clan = clan;
    }

    public ClanRefDTO getEnemy() {
        return enemy;
    }

    public void setEnemy(ClanRefDTO enemy) {
        this.enemy = enemy;
    }

    public WarScore getClanScore() {
        return clanScore;
    }

    public void setClanScore(WarScore clanScore) {
        this.clanScore = clanScore;
    }

    public WarScore getEnemyScore() {
        return enemyScore;
    }

    public void setEnemyScore(WarScore enemyScore) {
        this.enemyScore = enemyScore;
    }

}
