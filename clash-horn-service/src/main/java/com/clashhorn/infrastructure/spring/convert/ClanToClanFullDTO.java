/*
 * Clash Horn - MIT License
 */
package com.clashhorn.infrastructure.spring.convert;

import com.clashhorn.application.dto.BadgeUrlsDTO;
import com.clashhorn.application.dto.ClanFullDTO;
import com.clashhorn.application.dto.LocationDTO;
import com.clashhorn.application.clashapi.Clan;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 *
 * @author morgade
 */
@Component
public class ClanToClanFullDTO extends ConverterDepedentConverter implements Converter<Clan, ClanFullDTO> {
    
    @Override
    public ClanFullDTO convert(Clan source) {
        ClanFullDTO target = new ClanFullDTO();
        BeanUtils.copyProperties(source, target, "location", "badgeUrls");
        target.setLocation(getConverter().convert(source.getLocation(), LocationDTO.class));
        target.setBadgeUrls(getConverter().convert(source.getBadgeUrls(), BadgeUrlsDTO.class));
        return target;
    }
    
}
