/*
 * Clash Horn - MIT License
 */
package com.clashhorn.application;

import com.clashhorn.application.dto.ClanFullDTO;
import com.clashhorn.infrastructure.clashapi.ClashAPIService;
import com.clashhorn.infrastructure.clashapi.data.Clan;
import com.clashhorn.interfaces.jsonrpc.ClashHornEndpoint;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

/**
 *
 * @author morgade
 */
@Service
@AutoJsonRpcServiceImpl
public class ClashHornAPI implements ClashHornEndpoint {
    @Autowired
    @Qualifier("dtoConversionService")
    private ConversionService converter;
    @Autowired
    private ClashAPIService clashAPIService;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ClanFullDTO fethClanData(@JsonRpcParam("tag") String tag) {
        Clan clan = clashAPIService.clans(tag);
        return converter.convert(clan, ClanFullDTO.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Map status() {
        return new HashMap() {{
            put("freeHeap", Runtime.getRuntime().freeMemory());
        }};
    }
    
}
