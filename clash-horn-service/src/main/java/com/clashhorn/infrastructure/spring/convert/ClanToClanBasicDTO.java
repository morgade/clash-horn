/*
 * Clash Horn - MIT License
 */
package com.clashhorn.infrastructure.spring.convert;

import com.clashhorn.application.dto.ClanBasicDTO;
import com.clashhorn.infrastructure.clashapi.data.Clan;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 *
 * @author morgade
 */
@Component
public class ClanToClanBasicDTO implements Converter<Clan, ClanBasicDTO> {

    @Override
    public ClanBasicDTO convert(Clan source) {
        ClanBasicDTO target = new ClanBasicDTO();
        target.setTag(source.getTag());
        target.setName(source.getName());
        return target;
    }
    
}
