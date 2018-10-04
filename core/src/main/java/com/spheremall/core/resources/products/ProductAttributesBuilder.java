package com.spheremall.core.resources.products;

import com.spheremall.core.entities.price.PriceConfiguration;
import com.spheremall.core.entities.products.Attribute;
import com.spheremall.core.entities.products.Product;
import com.spheremall.core.entities.products.ProductAttributeValue;

import java.util.ArrayList;
import java.util.List;

public class ProductAttributesBuilder {

    public Product combineProductProperties(Product product) {

        if (product.productPriceConfigurations == null || product.productPriceConfigurations.size() == 0) {
            mapAttributes(product);
            return product;
        }

        List<PriceConfiguration> priceConfigurations = product.productPriceConfigurations.get(0).priceConfigurations;
        if (priceConfigurations == null || priceConfigurations.size() == 0) {
            mapAttributes(product);
            return product;
        }

        List<String> affectAttributes = priceConfigurations.get(0).affectAttributes;

        if (affectAttributes == null || affectAttributes.size() == 0) {
            mapAttributes(product);
            return product;
        }

        product.affectedAttributes = new ArrayList<>();

        for (Attribute attribute : product.attributes) {

            setMapAttributeValues(product, attribute);

            for (String affectedAttr : affectAttributes) {
                if (attribute.getId().toString().equals(affectedAttr)) {
                    product.affectedAttributes.add(attribute);
                }
            }
        }

        return product;
    }

    private void mapAttributes(Product product) {
        for (Attribute attribute : product.attributes) {
            setMapAttributeValues(product, attribute);
        }
    }

    private void setMapAttributeValues(Product product, Attribute attribute) {
        attribute.attributeValues = new ArrayList<>();

        for (ProductAttributeValue productAttributeValue : product.productAttributeValues) {
            if (attribute.getId() == productAttributeValue.attributeId) {
                attribute.attributeValues.addAll(productAttributeValue.attributeValues);
            }
        }
    }
}
