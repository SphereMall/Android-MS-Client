package com.spheremall.core.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spheremall.core.api.response.ErrorResponse;
import com.spheremall.core.jsonapi.IntegerIdHandler;
import com.spheremall.core.jsonapi.annotations.Id;
import com.spheremall.core.jsonapi.annotations.Meta;
import com.spheremall.core.jsonapi.annotations.Type;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@Type("base")
public class Entity implements Cloneable{

    private HashMap<String, String> properties = new HashMap<>();

    @Id(IntegerIdHandler.class)
    private Integer id;

    public String status;

    public List<ErrorResponse> errors;

    public String service;
    public String ver;

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

    public Boolean isSuccess() {
        return status.equals("OK");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entity)) return false;
        Entity entity = (Entity) o;
        return Objects.equals(properties, entity.properties) &&
                Objects.equals(id, entity.id) &&
                Objects.equals(status, entity.status) &&
                Objects.equals(errors, entity.errors) &&
                Objects.equals(service, entity.service) &&
                Objects.equals(ver, entity.ver) &&
                Objects.equals(meta, entity.meta);
    }

    @Override
    public int hashCode() {

        return Objects.hash(properties, id, status, errors, service, ver, meta);
    }
}
