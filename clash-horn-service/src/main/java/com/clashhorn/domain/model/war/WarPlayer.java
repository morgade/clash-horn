/*
 * Clash Horn - MIT License
 */
package com.clashhorn.domain.model.war;

import com.clashhorn.domain.shared.ValueObject;

/**
 *
 * @author morgade
 */
@ValueObject
public class WarPlayer {
    private String tag;
    private String name;
    private int position;
    private Integer townhallLevel;

    WarPlayer() {
    }

    public WarPlayer(String tag, String name, int position, Integer townhallLevel) {
        this.tag = tag;
        this.name = name;
        this.position = position;
        this.townhallLevel = townhallLevel;
    }

    public String getTag() {
        return tag;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public Integer getTownhallLevel() {
        return townhallLevel;
    }

    
}
