/*
 * Clash Horn - MIT License
 */
package com.clashhorn.infrastructure.clashapi.data;

import com.clashhorn.infrastructure.clashapi.ClashApiTestConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import static org.springframework.util.FileCopyUtils.copyToByteArray;
import static org.springframework.util.ResourceUtils.getFile;

/**
 *
 * @author morgade
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Import(ClashApiTestConfiguration.class)
public class WarTest {
    public static final String STATE = "inWar";
    public static final int TEAM_SIZE = 10;
    public static final String CLAN_TAG = "#22PLRY2G";
    public static final String CLAN_NAME = "TJF";
    public static final int CLAN_LEVEL = 10;
    public static final String OPPONENT_NAME = "доза";
    public static final int OPPONENT_STARS = 28;
    public static final int OPPONENT_ATTACKS = 11;
    public static final float OPPONENT_DESTRUCUTION_PERCENTAGE = 95.8f;
    
    public static final int OPPONENT_MEMBER_1_ATTACK_0_STARS = 3;
    public static final float OPPONENT_MEMBER_1_ATTACK_0_DESTRUCUTION_PERCENTAGE = 100f;
    public static final int OPPONENT_MEMBER_1_ATTACK_0_ORDER = 9;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    public void jsonLoadTest() throws Exception {
        JacksonTester<War> json = new JacksonTester<>(War.class, ResolvableType.forClass(War.class), objectMapper);
        
        String content = new String(copyToByteArray(getFile("classpath:json/war-01.json") ), "UTF8");
        
        War war = json.parse(content).getObject();
        
        assertThat(war.getState()).isEqualTo(STATE);
        assertThat(war.getTeamSize()).isEqualTo(TEAM_SIZE);
        
        WarClan clan = war.getClan();
        assertThat(clan.getTag()).isEqualTo(CLAN_TAG);
        assertThat(clan.getName()).isEqualTo(CLAN_NAME);
        assertThat(clan.getClanLevel()).isEqualTo(CLAN_LEVEL);
        
        WarClan opponent = war.getOpponent();
        assertThat(opponent.getName()).isEqualTo(OPPONENT_NAME);
        assertThat(opponent.getStars()).isEqualTo(OPPONENT_STARS);
        assertThat(opponent.getAttacks()).isEqualTo(OPPONENT_ATTACKS);
        assertThat(opponent.getDestructionPercentage()).isEqualTo(OPPONENT_DESTRUCUTION_PERCENTAGE);
        
        WarClanMember member = opponent.getMembers()[1];
        assertThat(member.getAttacks()[0].getStars()).isEqualTo(OPPONENT_MEMBER_1_ATTACK_0_STARS);
        assertThat(member.getAttacks()[0].getDestructionPercentage()).isEqualTo(OPPONENT_MEMBER_1_ATTACK_0_DESTRUCUTION_PERCENTAGE);
        assertThat(member.getAttacks()[0].getOrder()).isEqualTo(OPPONENT_MEMBER_1_ATTACK_0_ORDER);
        
    }
}
