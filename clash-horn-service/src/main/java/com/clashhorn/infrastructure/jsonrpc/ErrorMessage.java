/*
 * Clash Horn - MIT License
 */
package com.clashhorn.infrastructure.jsonrpc;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author morgade
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorMessage {
    private final String reason;
    private final String message;
    private int status;

    @JsonCreator
    public ErrorMessage(@JsonProperty("reason") String reason, @JsonProperty("message") String message) {
        this.reason = reason;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public String getMessage() {
        return message;
    }
    
    
}
