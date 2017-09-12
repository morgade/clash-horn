/*
 * Clash Horn - MIT License
 */
package com.clashhorn.infrastructure.clashapi.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author morgade
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {
    private final String id;
    private final String name;
    private final String isCountry;
    private final String countryCode;

    public Location(@JsonProperty("id") String id, 
                    @JsonProperty("name") String name, 
                    @JsonProperty("isCountry") String isCountry, 
                    @JsonProperty("countryCode") String countryCode) {
        this.id = id;
        this.name = name;
        this.isCountry = isCountry;
        this.countryCode = countryCode;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIsCountry() {
        return isCountry;
    }

    public String getCountryCode() {
        return countryCode;
    }
    
}
