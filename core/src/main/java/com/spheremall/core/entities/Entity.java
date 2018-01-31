package com.spheremall.core.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spheremall.core.jsonapi.IntegerIdHandler;
import com.spheremall.core.jsonapi.annotations.Id;
import com.spheremall.core.jsonapi.annotations.Meta;
import com.spheremall.core.jsonapi.annotations.Type;

import java.util.HashMap;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@Type("base")
public class Entity {

    private HashMap<String, String> properties = new HashMap<>();

    @Id(IntegerIdHandler.class)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Meta
    public EntityMeta meta;

    public String getType() {
        return this.getClass().getSimpleName().toLowerCase();
    }

    public HashMap<String, String> getProperties() {
        return properties;
    }

    public void setProperty(String name, String value) {
        properties.put(name, value);
    }

    public void removeProperty(String name) {
        properties.remove(name);
    }

    public void setProperties(HashMap<String, String> newProperties) {
        Set<String> fields = newProperties.keySet();
        for (Object field : fields) {
            properties.put((String) field, newProperties.get(field));
        }
    }

    public String getValueByProperty(String propertyName) {
        return properties.get(propertyName);
    }
}
