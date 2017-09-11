package com.clashhorn.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

/**
 *
 * @author x4rb
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment {
    private final UUID id;
    private final String email;
    private final String name;
    private final String text;

    @JsonCreator
    public Comment(@JsonProperty("id")    String id, 
                   @JsonProperty("email") String email,
                   @JsonProperty("name")  String name, 
                   @JsonProperty("text")  String text) {
        this.id = id == null ? UUID.randomUUID() : UUID.fromString(id);
        this.email = email;
        this.name = name;
        this.text = text;
    }
    
    public Comment(String email, String name, String text) {
        this(null, email, name, text);
    }

    public String getId() {
        return id!=null?id.toString():null;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    
}
