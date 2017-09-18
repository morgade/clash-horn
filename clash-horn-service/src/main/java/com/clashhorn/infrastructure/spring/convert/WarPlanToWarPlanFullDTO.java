/*
 * Clash Horn - MIT License
 */
package com.clashhorn.infrastructure.spring.convert;

import com.clashhorn.application.dto.ClanRefDTO;
import com.clashhorn.application.dto.WarPlanFullDTO;
import com.clashhorn.domain.model.war.WarPlan;
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
        BeanUtils.copyProperties(source, target, "clan", "enemy");
        target.setClan(getConverter().convert(source.getClan(), ClanRefDTO.class) );
        target.setEnemy(getConverter().convert(source.getEnemy(), ClanRefDTO.class));
        return target;
    }
    
}
