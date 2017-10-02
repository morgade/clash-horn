/*
 * Clash Horn - MIT License
 */
package com.clashhorn.infrastructure.spring.convert;

import com.clashhorn.application.dto.BadgeUrlsDTO;
import com.clashhorn.application.dto.ClanRefDTO;
import com.clashhorn.application.dto.LocationDTO;
import com.clashhorn.domain.model.clan.ClanRef;
import com.clashhorn.application.clashapi.BadgeUrls;
import com.clashhorn.application.clashapi.Location;
import static java.util.Arrays.asList;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.stereotype.Component;

/**
 *
 * @author morgade
 */
@Component
public class DirectCopyConverter implements GenericConverter {

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return new HashSet<>(asList(
                new ConvertiblePair(Location.class, LocationDTO.class),
                new ConvertiblePair(BadgeUrls.class, BadgeUrlsDTO.class),
                new ConvertiblePair(ClanRef.class, ClanRefDTO.class)
        ));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        try {
            Object target = targetType.getObjectType().newInstance();
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new IllegalArgumentException(ex);
        }
        
    }
    
}
