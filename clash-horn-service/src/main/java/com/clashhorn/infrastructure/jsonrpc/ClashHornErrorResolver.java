/*
 * Clash Horn - MIT License
 */
package com.clashhorn.infrastructure.jsonrpc;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.jsonrpc4j.DefaultErrorResolver;
import com.googlecode.jsonrpc4j.ErrorResolver;
import com.googlecode.jsonrpc4j.ErrorResolver.JsonError;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

/**
 *
 * @author morgade
 */
@Component
public class ClashHornErrorResolver implements ErrorResolver {
    public static final int CODE_API_PREFIX = -606000;

    private final ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    public JsonError resolveError(Throwable error, Method method, List<JsonNode> list) {
        if (error instanceof HttpClientErrorException) {
            HttpClientErrorException e = (HttpClientErrorException) error;
            if (e.getStatusCode()==HttpStatus.FORBIDDEN || 
                    e.getStatusCode()==HttpStatus.TOO_MANY_REQUESTS || 
                    e.getStatusCode()==HttpStatus.INTERNAL_SERVER_ERROR || 
                    e.getStatusCode()==HttpStatus.SERVICE_UNAVAILABLE ||
                    e.getStatusCode()==HttpStatus.NOT_FOUND ) {
                
                try {
                    ErrorMessage errorMessage = objectMapper.readValue(e.getResponseBodyAsString(), ErrorMessage.class);
                    errorMessage.setStatus(e.getRawStatusCode());
                    return new JsonError(CODE_API_PREFIX - e.getRawStatusCode(), errorMessage.getReason(), errorMessage);
                } catch (IOException  | RuntimeException exc) {
                    LoggerFactory.getLogger(getClass()).error("Could not handle Clash of Clans Service error status", e);
                }
            }
            return new JsonError(CODE_API_PREFIX - e.getRawStatusCode(), "Could not contact Clash of Clans API service", list);
        } else {
            return DefaultErrorResolver.INSTANCE.resolveError(error, method, list);
        }
    }
    
}
