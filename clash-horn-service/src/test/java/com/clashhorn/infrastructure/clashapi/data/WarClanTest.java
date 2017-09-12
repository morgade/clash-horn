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
public class WarClanTest {
    public static final String TAG = "#22PLRY2G";
    public static final String NAME = "TJF";
    public static final int LEVEL = 10;
    public static final String SMALL_URL = "SMALL_URL";
    public static final String CLAN_NAME = "TJF";
    public static final int MEMBER_COUNT = 10;
    public static final String MEMBER_0_NAME = "bitor";
    public static final String MEMBER_0_TAG = "#PLC8LUPG";
    public static final int MEMBER_0_TOWNHALL_LEVEL = 9;
    public static final int MEMBER_0_MAP_POSITION = 4;
    public static final int MEMBER_0_OPPONENT_ATACKS = 0;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    public void jsonLoadTest() throws Exception {
        JacksonTester<WarClan> json = new JacksonTester<>(WarClan.class, ResolvableType.forClass(WarClan.class), objectMapper);
        
        String content = new String(copyToByteArray(getFile("classpath:json/war-clan-01.json") ));
        
        WarClan clan = json.parse(content).getObject();
        assertThat(clan.getTag()).isEqualTo(TAG);
        assertThat(clan.getName()).isEqualTo(NAME);
        assertThat(clan.getClanLevel()).isEqualTo(LEVEL);
        assertThat(clan.getBadgeUrls().getSmall()).isEqualTo(SMALL_URL);
        
        assertThat(clan.getMembers().length).isEqualTo(MEMBER_COUNT);
        assertThat(clan.getMembers()[0].getTag()).isEqualTo(MEMBER_0_TAG);
        assertThat(clan.getMembers()[0].getName()).isEqualTo(MEMBER_0_NAME);
        assertThat(clan.getMembers()[0].getMapPosition()).isEqualTo(MEMBER_0_MAP_POSITION);
        assertThat(clan.getMembers()[0].getTownhallLevel()).isEqualTo(MEMBER_0_TOWNHALL_LEVEL);
        assertThat(clan.getMembers()[0].getOpponentAttacks()).isEqualTo(MEMBER_0_OPPONENT_ATACKS);
    }
}
