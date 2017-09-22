/*
 * Clash Horn - MIT License
 */
package com.clashhorn.infrastructure.spring.convert;

import com.clashhorn.application.dto.ClanRefDTO;
import com.clashhorn.application.dto.WarPlanFullDTO;
import com.clashhorn.domain.model.war.WarPlan;
import com.clashhorn.domain.model.war.WarPlayer;
import static java.util.stream.Collectors.toList;
import java.util.stream.IntStream;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 *
 * @author morgade
 */
@Component
public class WarPlanToWarPlanFullDTO extends ConverterDepedentConverter implements Converter<WarPlan, WarPlanFullDTO> {

    @Override
    public WarPlanFullDTO convert(WarPlan source) {
        WarPlanFullDTO target = new WarPlanFullDTO();
        BeanUtils.copyProperties(source, target, "positions", "clan", "enemy");
        target.setClan(getConverter().convert(source.getClan(), ClanRefDTO.class) );
        target.setEnemy(getConverter().convert(source.getEnemy(), ClanRefDTO.class));
        target.setPositions(
                IntStream.range(0, source.getMapSize())
                        .mapToObj(idx -> {
                            WarPlanFullDTO.Position pos = new WarPlanFullDTO.Position();
                            pos.setNumber(idx+1);
                            pos.setEnemy(source.getEnemies().get(idx));
                            pos.setMember(source.getMembers().get(idx));
                            pos.setPerformedAttacks(source.getPerformedAttacks(idx+1));
                            pos.setSufferedAttacks(source.getSufferedAttacks(idx+1));
                            pos.setAttackQueue(source.getAttackQueue(idx).stream().map(WarPlayer::getMapPosition).collect(toList()));
                            return pos;
                        })
                        .collect(toList())
        );
        return target;
    }
    
}
