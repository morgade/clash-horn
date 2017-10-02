/*
 * Clash Horn - MIT License
 */
package com.clashhorn.infrastructure.spring.convert;

import com.clashhorn.application.dto.ClanRefDTO;
import com.clashhorn.application.clashapi.Clan;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 *
 * @author morgade
 */
@Component
public class ClanToClanBasicDTO implements Converter<Clan, ClanRefDTO> {

    @Override
    public ClanRefDTO convert(Clan source) {
        ClanRefDTO target = new ClanRefDTO();
        target.setTag(source.getTag());
        target.setName(source.getName());
        return target;
    }
    
}
