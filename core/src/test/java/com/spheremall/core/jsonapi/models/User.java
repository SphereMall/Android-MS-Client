package com.spheremall.core.jsonapi.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.spheremall.core.jsonapi.annotations.Id;
import com.spheremall.core.jsonapi.annotations.Links;
import com.spheremall.core.jsonapi.annotations.Meta;
import com.spheremall.core.jsonapi.annotations.Relationship;
import com.spheremall.core.jsonapi.annotations.Type;

import java.util.List;

@Type("users")
@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class, property = "id")
public class User {

    public static class UserMeta {
        public String token;

        public String getToken() {
            return token;
        }
    }

    @Id
    public String id;
    public String name;

    @Relationship("statuses")
    private List<Status> statuses;

    @Meta
    public UserMeta meta;

    @Links
    public com.spheremall.core.jsonapi.Links links;

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

    public List<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Status> statuses) {
        this.statuses = statuses;
    }

    public UserMeta getMeta() {
        return meta;
    }
}
