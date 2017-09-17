/*
 * Clash Horn - MIT License
 */
package com.clashhorn.interfaces.jsonrpc;

import com.clashhorn.application.dto.ClanFullDTO;
import com.googlecode.jsonrpc4j.JsonRpcError;
import com.googlecode.jsonrpc4j.JsonRpcErrors;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import java.util.Map;
import org.springframework.web.client.HttpClientErrorException;

/**
 *
 * @author morgade
 */
@JsonRpcService("/api/clash-horn")
public interface ClashHornEndpoint {
    public static final int COMMUNICATION_ERROR = -1001;
    public static final String COMMUNICATION_ERROR_MESSAGE = "Could not communicate with official Clash Of Clans data service";
    
    /**
     * Fetch clan data by tag
     * @param tag
     * @return 
     */
    @JsonRpcErrors({ @JsonRpcError(exception=HttpClientErrorException.class, code=COMMUNICATION_ERROR, message=COMMUNICATION_ERROR_MESSAGE) })
    ClanFullDTO fethClanData(@JsonRpcParam("tag") String tag);
    
    Map status();
}
