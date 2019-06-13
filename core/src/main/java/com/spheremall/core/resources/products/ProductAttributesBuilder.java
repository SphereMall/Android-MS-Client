package com.spheremall.core.resources.products;

import com.spheremall.core.entities.documents.EntityAttributeValue;
import com.spheremall.core.entities.price.PriceConfiguration;
import com.spheremall.core.entities.products.Attribute;
import com.spheremall.core.entities.products.AttributeValue;
import com.spheremall.core.entities.products.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductAttributesBuilder {

    public Product combineProductProperties(Product product) {

        if (product.productPriceConfigurations == null || product.productPriceConfigurations.size() == 0) {
            return mapAttributes(product);
        }

        List<PriceConfiguration> priceConfigurations = product.productPriceConfigurations.get(0).priceConfigurations;
        if (priceConfigurations == null || priceConfigurations.size() == 0) {
            return mapAttributes(product);
        }

        List<String> affectAttributes = priceConfigurations.get(0).affectAttributes;

        if (affectAttributes == null || affectAttributes.size() == 0) {
            return mapAttributes(product);
        }

        product.affectedAttributes = new ArrayList<>();

        for (Attribute attribute : product.attributes) {

            Attribute attr = null;
            try {
                attr = mapAttributeValues(product.attributeValues, attribute);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

            if (attr == null)
                continue;

            for (String affectedAttr : affectAttributes) {
                if (attr.getId().toString().equals(affectedAttr)) {
                    product.affectedAttributes.add(attr);
                }
            }
        }

        return product;
    }

    private Product mapAttributes(Product product) {

        try {
            Product item = product.clone();
            item.attributes = new ArrayList<>();
            if (product.attributes != null) {
                for (Attribute attribute : product.attributes) {
                    item.attributes.add(mapAttributeValues(product.attributeValues, attribute));
                }
            } else {
                for (EntityAttributeValue eav : product.entityAttributeValues) {
                    if (eav.attributes != null && eav.attributes.size() > 0) {
                        Attribute attribute = eav.attributes.get(0);
                        attribute.attributeValues = new ArrayList<>();
                        attribute.attributeValues.addAll(eav.attributeValues);
                        item.attributes.add(attribute);
                    }
                }
            }

            return item;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Attribute mapAttributeValues(List<AttributeValue> attributeValues, Attribute attribute) throws CloneNotSupportedException {
        List<AttributeValue> attrValues = new ArrayList<>();

        for (AttributeValue attrValue : attributeValues) {
            if (attribute.getId() == attrValue.attributeId) {
                attrValues.add(attrValue.clone());
            }
        }

        Attribute clonedAttribute = attribute.clone();
        clonedAttribute.attributeValues = new ArrayList<>(attrValues);
        return clonedAttribute;
    }
}
