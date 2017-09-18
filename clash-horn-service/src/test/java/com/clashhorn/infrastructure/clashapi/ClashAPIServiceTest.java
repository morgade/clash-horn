/*
 * Clash Horn - MIT License
 */
package com.clashhorn.infrastructure.clashapi;

import com.clashhorn.application.clashapi.War;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import com.clashhorn.application.service.ClashOfClansService;

/**
 *
 * @author guilaaf
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Import(ClashApiTestConfiguration.class)
public class ClashAPIServiceTest {
    
    @Autowired
    private ClashOfClansService service;
    
    @Test
    public void shouldReturnCurrentWar() {
        War war = service.currentWar("#22PLRY2G");
        assertThat(war.getClan().getTag()).isEqualTo("#22PLRY2G");
    }
    
}
