/*
 * Clash Horn - MIT License
 */
package com.clashhorn.application.service;

import com.clashhorn.application.clashapi.War;
import com.clashhorn.domain.model.war.WarPlan;
import com.clashhorn.domain.model.war.WarPosition;
import com.clashhorn.domain.model.war.WarResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author morgade
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ClashHornServiceImplTest {
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    public void createWarPlanFromCoCWarTest() throws Exception {
        War war = objectMapper.readValue(getClass().getResource("/mock/data01/currentWar-01.json"), War.class);
                
        String clanAccountId = "XXXXXXX";
        
        WarPlan warPlan = ClashHornServiceImpl.createWarPlanFromCoCWar(war, clanAccountId);
        assertThat(warPlan.getId()).isNotEmpty();
        assertThat(warPlan.getClanAccountId()).isEqualTo(clanAccountId);
        assertThat(warPlan.getEnemy().getTag()).isEqualTo(war.getOpponent().getTag());
        
        assertThat(warPlan.getPerformedAttacksBy(WarPosition.P06).size()).isEqualTo(1);
        assertThat(warPlan.getResult()).isEqualTo(WarResult.IN_PROGRESS);
        
        assertThat(warPlan.getSufferedAttacksAgainst(WarPosition.P04).size()).isEqualTo(2);
        assertThat(warPlan.getSufferedAttacksAgainst(WarPosition.P04).stream() 
                                .filter(a -> a.getAttacker()== WarPosition.P04)
                                .findFirst()
                                .get().getStars()
                    ).isEqualTo(2);
    }
    
    @Test
    public void createWarPlanFromCoCWarTest2() throws Exception {
        War war = objectMapper.readValue(getClass().getResource("/mock/data02/currentWar-03.json"), War.class);
                
        String clanAccountId = "XXXXXXX";
        
        WarPlan warPlan = ClashHornServiceImpl.createWarPlanFromCoCWar(war, clanAccountId);
        assertThat(warPlan.getResult()).isEqualTo(WarResult.VICTORY);
    }
}
