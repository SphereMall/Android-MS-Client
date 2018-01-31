package com.spheremall.core.resources.products;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.products.ProductAttributeValue;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

public class ProductAttributeValuesResourceImpl extends BaseResource<ProductAttributeValue, ProductAttributeValuesResource> implements ProductAttributeValuesResource {

    public ProductAttributeValuesResourceImpl(SMClient smClient) {
        super(smClient);
    }

    @Override
    public String getURI() {
        return "productattributevalues";
    }

    @Override
    public ObjectMaker<ProductAttributeValue> initializeMaker() {
        return new ObjectMaker<>(ProductAttributeValue.class);
    }

    @Override
    protected ProductAttributeValuesResource currentContext() {
        return this;
    }
}
