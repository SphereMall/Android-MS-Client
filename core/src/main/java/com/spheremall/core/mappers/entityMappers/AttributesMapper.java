package com.spheremall.core.mappers.entityMappers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.spheremall.core.entities.products.Attribute;

public class AttributesMapper extends ObjectMapper<Attribute> {

    @Override
    public Attribute doObject(JsonObject obj, JsonObject relations, JsonArray includes) {
        Attribute attribute = new Attribute();
        attribute.setId(JsonObjectUtils.getInt(obj, "id"));
        attribute.showInSpecList = JsonObjectUtils.getInt(obj, "showInSpecList");
        attribute.code = JsonObjectUtils.getString(obj, "code");
        attribute.title = JsonObjectUtils.getString(obj, "title");
        attribute.description = JsonObjectUtils.getString(obj, "description");
        attribute.attributeGroupId = JsonObjectUtils.getInt(obj, "attributeGroupId");
        attribute.orderNumber = JsonObjectUtils.getInt(obj, "orderNumber");
        attribute.useInFilter = JsonObjectUtils.getInt(obj, "useInFilter");
        return attribute;
    }
}
