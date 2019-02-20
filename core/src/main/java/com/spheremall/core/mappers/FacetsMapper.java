package com.spheremall.core.mappers;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.Response;
import com.spheremall.core.entities.products.Attribute;
import com.spheremall.core.entities.products.AttributeValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacetsMapper implements Mapper<Response<List<Entity>>, Entity> {

    @Override
    public List<Entity> doObject(Response<List<Entity>> obj) {
        Map<Attribute, List<AttributeValue>> map = new HashMap<>();
        List<Entity> facets = new ArrayList<>();
        for (Entity entity : obj.data()) {

            if (entity instanceof AttributeValue) {
                AttributeValue attributeValue = null;
                try {
                    attributeValue = ((AttributeValue) entity).clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                if (attributeValue == null) continue;

                Attribute attribute = null;
                try {
                    attribute = attributeValue.attribute.get(0).clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }

                if (attribute == null) continue;
                attributeValue.attribute = null;

                if (!map.containsKey(attribute)) {
                    List<AttributeValue> values = new ArrayList<>();
                    values.add(attributeValue);
                    map.put(attribute, values);
                } else {

                    map.get(attribute).add(attributeValue);
                }
            } else {
                facets.add(entity);
            }
        }

        for (Map.Entry<Attribute, List<AttributeValue>> entry : map.entrySet()) {
            Attribute attribute = entry.getKey();
            attribute.attributeValues = new ArrayList<>();
            attribute.attributeValues.addAll(entry.getValue());
            facets.add(attribute);
        }
        return facets;
    }
}
