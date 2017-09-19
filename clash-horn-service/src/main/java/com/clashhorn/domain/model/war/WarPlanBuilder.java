/*
 * Clash Horn - MIT License
 */
package com.clashhorn.domain.model.war;

import com.clashhorn.domain.model.clan.ClanRef;
import java.util.Date;
import java.util.List;

/**
 * WarPlanBuilder
 * @author morgade
 */
public class WarPlanBuilder {
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
        private List<WarPlanAttack> performedAttacks;
        private List<WarPlanAttack> sufferedAttacks;

        private WarPlanBuilder() {
        }

        public static WarPlanBuilder builder(String id) {
            WarPlanBuilder wpb = new WarPlanBuilder();
            wpb.id = id;
            return wpb;
        }
        
        
        public WarPlanBuilder clanAccountId(final String value) {
            this.clanAccountId = value;
            return this;
        }

        public WarPlanBuilder clan(final ClanRef value) {
            this.clan = value;
            return this;
        }

        public WarPlanBuilder enemy(final ClanRef value) {
            this.enemy = value;
            return this;
        }

        public WarPlanBuilder startTime(final Date value) {
            this.startTime = value;
            return this;
        }

        public WarPlanBuilder preparationStartTime(final Date value) {
            this.preparationStartTime = value;
            return this;
        }

        public WarPlanBuilder endTime(final Date value) {
            this.endTime = value;
            return this;
        }

        public WarPlanBuilder members(final List<WarPlayer> value) {
            this.members = value;
            return this;
        }

        public WarPlanBuilder enemies(final List<WarPlayer> value) {
            this.enemies = value;
            return this;
        }

        public WarPlanBuilder attackQueues(final List<List<WarPlayer>> value) {
            this.attackQueues = value;
            return this;
        }

        public WarPlanBuilder performedAttacks(final List<WarPlanAttack> value) {
            this.performedAttacks = value;
            return this;
        }

        public WarPlanBuilder sufferedAttacks(final List<WarPlanAttack> value) {
            this.sufferedAttacks = value;
            return this;
        }

        public WarPlan build() {
            return new WarPlan(id, clanAccountId, clan, enemy, startTime, preparationStartTime, endTime, members, enemies, attackQueues, performedAttacks, sufferedAttacks);
        }
    }
