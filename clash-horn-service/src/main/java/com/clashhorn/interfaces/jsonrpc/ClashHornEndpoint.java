/*
 * Clash Horn - MIT License
 */
package com.clashhorn.interfaces.jsonrpc;

import com.clashhorn.application.dto.ClanFullDTO;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import java.util.Map;

/**
 *
 * @author morgade
 */
@JsonRpcService("/api/clash-horn")
public interface ClashHornEndpoint {
    ClanFullDTO fethClanData(@JsonRpcParam("tag") String tag);
    Map status();
}
