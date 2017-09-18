/*
 * Clash Horn - MIT License
 */
package com.clashhorn.application.clashapi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

/**
 *
 * @author morgade
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class War {
    
    private final String state;
    private final Integer teamSize;
    private final Date preparationStartTime;
    private final Date startTime;
    private final Date endTime;
    private final WarClan clan;
    private final WarClan opponent;

    @JsonCreator
    public War(@JsonProperty("state") String state, 
                      @JsonProperty("teamSize") Integer teamSize, 
                      @JsonProperty("preparationStartTime") 
                      @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd'T'HHmmss.SSSXXX") Date preparationStartTime, 
                      @JsonProperty("startTime")
                      @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd'T'HHmmss.SSSXXX") Date startTime, 
                      @JsonProperty("endTime")
                      @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd'T'HHmmss.SSSXXX") Date endTime, 
                      @JsonProperty("clan") WarClan clan, 
                      @JsonProperty("opponent") WarClan opponent) {
        this.state = state;
        this.teamSize = teamSize;
        this.preparationStartTime = preparationStartTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.clan = clan;
        this.opponent = opponent;
    }
    
    public String getState() {
        return state;
    }

    public Integer getTeamSize() {
        return teamSize;
    }

    public Date getPreparationStartTime() {
        return preparationStartTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public WarClan getClan() {
        return clan;
    }

    public WarClan getOpponent() {
        return opponent;
    }
    
    
}
