/*
 * Clash Horn - MIT License
 */
package com.clashhorn.infrastructure.spring.convert;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;

/**
 * This base class is used to avoid a cyclic dependency between a Converter and a ConversionService
 * The converter bean instance is fetched only after the context initialization
 * @author morgade
 */
public abstract class ConverterDepedentConverter {
    @Autowired
    private BeanFactory context;
    private ConversionService converter;
    
    
    public ConversionService getConverter() {
        if (converter==null) {
            converter = context.getBean("dtoConversionService", ConversionService.class);
        }
        
        return converter;
    }
}
