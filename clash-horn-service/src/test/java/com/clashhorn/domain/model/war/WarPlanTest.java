/*
 * Clash Horn - MIT License
 */
package com.clashhorn.domain.model.war;

import com.clashhorn.application.clashapi.War;
import com.clashhorn.application.service.ClashHornServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author morgade
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class WarPlanTest {
    @Autowired
    private ObjectMapper objectMapper;
    
    private War war1;
    private War war2;
    private WarPlan warPlan1;
    private WarPlan warPlan2;
    
    @Before
    public void before() throws IOException {
        war1 = objectMapper.readValue(getClass().getResource("/mock/data01/currentWar-01.json"), War.class);
        war2 = objectMapper.readValue(getClass().getResource("/mock/data01/currentWar-02.json"), War.class);

        warPlan1 = ClashHornServiceImpl.createWarPlanFromCoCWar(war1, "XXXX");
        warPlan2 = ClashHornServiceImpl.createWarPlanFromCoCWar(war2, "XXXX");
    }
    
    @Test
    public void shouldUpdateWithDataFromCorrectly()  {
        warPlan1.addPlannedAttack(WarPosition.P01, WarPosition.P04);

        assertThat(warPlan1.getClanScore().getStars()).isEqualTo(28);
        assertThat(warPlan1.getClanScore().getDestruction()).isEqualTo(64);
        assertThat(warPlan1.getPerformedAttacksBy(WarPosition.P04).size()).isEqualTo(1);
        
        warPlan1.updateWithDataFrom(warPlan2);
        
        assertThat(warPlan1.getClanScore().getStars()).isEqualTo(30);
        assertThat(warPlan1.getClanScore().getDestruction()).isEqualTo(65);
        assertThat(warPlan1.getPerformedAttacksBy(WarPosition.P04).size()).isEqualTo(2);
        assertThat(warPlan1.getPerformedAttacksBy(WarPosition.P04).get(1).getDefender()).isEqualTo(WarPosition.P03);
        
        // Update should not modify attack queue
        assertThat(warPlan1.getAttackQueue(WarPosition.P01).size()).isEqualTo(1);
        assertThat(warPlan1.getAttackQueue(WarPosition.P01).get(0).getAttacker()).isEqualTo(WarPosition.P04);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void shouldNotAllowAddPlannedAttackWhenMemberHasAlreadyAtackkedTwice()  {
        warPlan1.addPlannedAttack(WarPosition.P05, WarPosition.P08);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void shouldNotAllowAddPlannedAttackWhenMemberHasAlreadyAtackkedPosition()  {
        warPlan1.addPlannedAttack(WarPosition.P04, WarPosition.P04);
    }
    
    @Test
    public void shouldAddPlannedAttack()  {
        warPlan1.addPlannedAttack(WarPosition.P01, WarPosition.P04);
        assertThat(warPlan1.getAttackQueue(WarPosition.P01).get(0).getAttacker()).isEqualTo(WarPosition.P04);
    }
}
