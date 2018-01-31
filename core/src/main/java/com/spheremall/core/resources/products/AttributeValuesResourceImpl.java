package com.spheremall.core.resources.products;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.products.AttributeValue;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

public class AttributeValuesResourceImpl extends BaseResource<AttributeValue, AttributeValuesResource> implements AttributeValuesResource {

    public AttributeValuesResourceImpl(SMClient smClient) {
        super(smClient);
    }

    @Override
    public String getURI() {
        return "attributevalues";
    }

    @Override
    public ObjectMaker<AttributeValue> initializeMaker() {
        return new ObjectMaker<>(AttributeValue.class);
    }

    @Override
    protected AttributeValuesResource currentContext() {
        return this;
    }
}
