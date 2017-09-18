/*
 * Clash Horn - MIT License
 */
package com.clashhorn.infrastructure.spring.convert;

import com.clashhorn.application.dto.BadgeUrlsDTO;
import com.clashhorn.application.dto.ClanWarDTO;
import com.clashhorn.infrastructure.clashapi.data.WarClan;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 *
 * @author morgade
 */
@Component
public class WarClanToClanWarDTO extends ConverterDepedentConverter implements Converter<WarClan, ClanWarDTO> {
    
    @Override
    public ClanWarDTO convert(WarClan source) {
        ClanWarDTO target = new ClanWarDTO();
        BeanUtils.copyProperties(source, target, "badgeUrls");
        target.setBadgeUrls(getConverter().convert(source.getBadgeUrls(), BadgeUrlsDTO.class));
        return target;
    }
    
}
