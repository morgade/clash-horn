/*
 * Clash Horn - MIT License
 */
package com.clashhorn.infrastructure.spring.convert;

import com.clashhorn.application.dto.ClanAccountDTO;
import com.clashhorn.application.dto.ClanRefDTO;
import com.clashhorn.domain.model.account.ClanAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 *
 * @author morgade
 */
@Component
public class ClanAccountToClanAccountDTO extends ConverterDepedentConverter implements Converter<ClanAccount, ClanAccountDTO> {

    @Override
    public ClanAccountDTO convert(ClanAccount source) {
        ClanAccountDTO target = new ClanAccountDTO();
        BeanUtils.copyProperties(source, target, "clan");
        target.setClan(getConverter().convert(source.getClan(), ClanRefDTO.class));
        return target;
    }
    
}
