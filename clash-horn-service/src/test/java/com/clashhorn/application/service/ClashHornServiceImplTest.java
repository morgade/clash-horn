/*
 * Clash Horn - MIT License
 */
package com.clashhorn.application.service;

import com.clashhorn.application.clashapi.War;
import com.clashhorn.domain.model.war.WarPlan;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.springframework.util.FileCopyUtils.copyToByteArray;
import static org.springframework.util.ResourceUtils.getFile;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author morgade
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ClashHornServiceImplTest {
    
    @Autowired
    private ClashHornServiceImpl service;
    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    public void createWarPlanFromCoCWarTest() throws Exception {
        String content = new String(copyToByteArray(getFile("classpath:json/war-01.json") ), "UTF8");
        War war = objectMapper.readValue(content, War.class);
                
        String clanAccountId = "XXXXXXX";
        
        WarPlan warPlan = service.createWarPlanFromCoCWar(war, clanAccountId);
        assertThat(warPlan.getId()).isNotEmpty();
        assertThat(warPlan.getClanAccountId()).isEqualTo(clanAccountId);
        assertThat(warPlan.getEnemy().getTag()).isEqualTo(war.getOpponent().getTag());
        assertThat(warPlan.getPerformedAttacks(3).size()).isEqualTo(0);
        assertThat(warPlan.getSufferedAttacks(4).size()).isEqualTo(2);
        assertThat(warPlan.getSufferedAttacks(4).stream() 
                                .filter(a -> a.getAttacker()== 6)
                                .findFirst()
                                .get().getStars()
                    ).isEqualTo(3);
    }
}
