/*
 * Clash Horn - MIT License
 */
package com.clashhorn.infrastructure.jsonrpc;

import com.fasterxml.jackson.databind.JsonNode;
import com.googlecode.jsonrpc4j.DefaultErrorResolver;
import com.googlecode.jsonrpc4j.ErrorResolver;
import com.googlecode.jsonrpc4j.ErrorResolver.JsonError;
import java.lang.reflect.Method;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

/**
 *
 * @author morgade
 */
@Component
public class ClashHornErrorResolver implements ErrorResolver {
    public static final int CODE_API_PREFIX = -10000;
    
    @Override
    public JsonError resolveError(Throwable error, Method method, List<JsonNode> list) {
        if (error instanceof HttpClientErrorException) {
            HttpClientErrorException e = (HttpClientErrorException) error;
            return new JsonError(CODE_API_PREFIX - e.getRawStatusCode(), "Could not contact Clash of Clans API service", list);
        } else {
            return DefaultErrorResolver.INSTANCE.resolveError(error, method, list);
        }
    }
    
}
