/*
 * Clash Horn - MIT License
 */
package com.clashhorn.application.clashapi;

import static java.lang.String.format;
import java.util.stream.Stream;

/**
 *
 * @author morgade
 */
public enum WarState {
    PREPARATION("preparation"),
    IN_WAR("inWar"),
    WAR_ENDED("warEnded");
    
    private String serviceState;

    private WarState(String serviceState) {
        this.serviceState = serviceState;
    }

    public String getServiceState() {
        return serviceState;
    }
    
    public static WarState fromServiceState(String status) {
        return Stream.of(values())
                    .filter(ws->ws.serviceState.equals(status))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException(format("Unknown service status: %s", status)));
    }
}
