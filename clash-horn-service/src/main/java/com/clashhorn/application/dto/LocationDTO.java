/*
 * Clash Horn - MIT License
 */
package com.clashhorn.application.dto;

/**
 *
 * @author morgade
 */
public class LocationDTO {
    private String id;
    private String name;
    private String isCountry;
    private String countryCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsCountry() {
        return isCountry;
    }

    public void setIsCountry(String isCountry) {
        this.isCountry = isCountry;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    
    
}
