/*
 * Clash Horn - MIT License
 */
package com.clashhorn.infrastructure.clashapi;

import static com.clashhorn.infrastructure.clashapi.data.ClanTest.TAG;
import com.clashhorn.infrastructure.clashapi.data.War;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author guilaaf
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Import(ClashApiTestConfiguration.class)
public class ClashAPIServiceTest {
    
    @Autowired
    private ClashAPIService service;
    
    @Test
    public void shouldReturnCurrentWar() {
        War war = service.currentWar("#22PLRY2G");
        assertThat(war.getClan().getTag()).isEqualTo("#22PLRY2G");
    }
    
}
