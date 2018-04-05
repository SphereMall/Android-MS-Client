package com.spheremall.core.mappers;

import com.spheremall.core.entities.pojo.FacetsObject;
import com.spheremall.core.entities.products.Attribute;
import com.spheremall.core.entities.products.AttributeValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacetAttributesMapper implements Mapper<FacetsObject, Attribute> {

    private HashMap<String, Attribute> attributeHashMap = new HashMap<>();

    @Override
    public List<Attribute> doObject(FacetsObject obj) {

        for (FacetsObject.Attribute attr : obj.data.attributes) {
            Attribute attribute = attributeHashMap.get(String.valueOf(attr.attributeId));
            if (attribute == null) {
                attribute = createAttribute(attr);
                for (FacetsObject.Attribute value : obj.data.attributes) {
                    if (attr.attributeId == value.attributeId) {
                        attribute.values.add(createAttributeValue(value));
                    }
                }
                attributeHashMap.put(attribute.getId().toString(), attribute);
            }
        }

        List<Attribute> attributes = new ArrayList<>();
        for (Object object : attributeHashMap.entrySet()) {
            Map.Entry pair = (Map.Entry) object;
            Attribute attribute = (Attribute) pair.getValue();
            attributes.add(attribute);
        }

        return attributes;
    }

    private Attribute createAttribute(FacetsObject.Attribute item) {
        Attribute attribute = new Attribute();
        attribute.setId(item.attributeId);
        attribute.attributeGroupId = item.attributeGroupId;
        attribute.title = item.title;
        attribute.code = item.code;
        attribute.cssClass = item.cssClass;
        attribute.orderNumber = item.orderNumber;
        attribute.showInSpecList = item.showInSpecList;
        attribute.useInFilter = item.useInFilter;
        attribute.description = item.description;
        attribute.values = new ArrayList<>();
        return attribute;
    }

    private AttributeValue createAttributeValue(FacetsObject.Attribute item) {
        AttributeValue attributeValue = new AttributeValue();
        attributeValue.setId(item.id);
        attributeValue.value = item.value;
        attributeValue.title = item.valueTitle;
        attributeValue.amount = item.amount;
        attributeValue.showInSpecList = item.showInSpecList;
        return attributeValue;
    }
}
