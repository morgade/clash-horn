/*
 * Clash Horn - MIT License
 */
package com.clashhorn.infrastructure.clashapi.data;

import com.clashhorn.application.clashapi.Clan;
import com.clashhorn.Configuration;
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
@Import(Configuration.class)
public class ClanTest {
    
    public static final String TAG = "#22PLRY2G";
    public static final String NAME = "TJF";
    public static final int CLAN_LEVEL = 10;
    public static final boolean IS_WAR_LOG_PUBLIC = true;
    public static final String COUNTRY_CODE = "BR";
    public static final String SMALL_URL = "SMALL_URL";
    public static final String WAR_FREQUENCY = "always";
    public static final int MEMBER_COUNT = 43;
    public static final String MEMBER_0_NAME = "MatoPorPrazer";
    public static final String MEMBER_0_ROLE = "admin";
    public static final int MEMBER_0_RANK = 1;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    public void jsonLoadTest() throws Exception {
        JacksonTester<Clan> json = new JacksonTester<>(Clan.class, ResolvableType.forClass(Clan.class), objectMapper);
        
        String content = new String(copyToByteArray(getFile("classpath:mock/data01/clan-01.json") ), "UTF8");
        
        Clan clan = json.parse(content).getObject();
        
        assertThat(clan.getTag()).isEqualTo(TAG);
        assertThat(clan.getName()).isEqualTo(NAME);
        assertThat(clan.getClanLevel()).isEqualTo(CLAN_LEVEL);
        assertThat(clan.getLocation().getCountryCode()).isEqualTo(COUNTRY_CODE);
        assertThat(clan.getBadgeUrls().getSmall()).isEqualTo(SMALL_URL);
        assertThat(clan.getWarFrequency()).isEqualTo(WAR_FREQUENCY);
        assertThat(clan.getIsWarLogPublic()).isEqualTo(IS_WAR_LOG_PUBLIC);
        assertThat(clan.getMembers()).isEqualTo(MEMBER_COUNT);
        assertThat(clan.getMemberList()[0].getName()).isEqualTo(MEMBER_0_NAME);
        assertThat(clan.getMemberList()[0].getRole()).isEqualTo(MEMBER_0_ROLE);
        assertThat(clan.getMemberList()[0].getClanRank()).isEqualTo(MEMBER_0_RANK);
        
    }
}
