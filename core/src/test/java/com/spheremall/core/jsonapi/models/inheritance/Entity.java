package com.spheremall.core.jsonapi.models.inheritance;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spheremall.core.jsonapi.annotations.Id;
import com.spheremall.core.jsonapi.annotations.Type;

@JsonIgnoreProperties(ignoreUnknown = true)
@Type("base")
public class Entity {
    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
