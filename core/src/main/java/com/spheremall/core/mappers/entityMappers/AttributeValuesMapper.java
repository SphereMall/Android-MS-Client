package com.spheremall.core.mappers.entityMappers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.spheremall.core.entities.products.AttributeValue;

public class AttributeValuesMapper extends ObjectMapper<AttributeValue> {

    @Override
    public AttributeValue doObject(JsonObject obj, JsonObject relations, JsonArray includes) {
        AttributeValue attributeValue = new AttributeValue();
        attributeValue.setId(JsonObjectUtils.getInt(obj, "id"));
        attributeValue.value = JsonObjectUtils.getString(obj, "value");
        attributeValue.attributeId = JsonObjectUtils.getInt(obj, "attributeId");
        attributeValue.code = JsonObjectUtils.getString(obj, "code");
        attributeValue.title = JsonObjectUtils.getString(obj, "title");
        attributeValue.image = JsonObjectUtils.getString(obj, "image");
        attributeValue.orderNumber = JsonObjectUtils.getInt(obj, "orderNumber");
        attributeValue.amount = JsonObjectUtils.getInt(obj, "amount");
        attributeValue.showInSpecList = JsonObjectUtils.getInt(obj, "showInSpecList");
        return attributeValue;
    }
}