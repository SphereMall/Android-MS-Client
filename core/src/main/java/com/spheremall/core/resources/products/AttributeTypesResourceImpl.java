package com.spheremall.core.resources.products;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.products.AttributeType;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

public class AttributeTypesResourceImpl extends BaseResource<AttributeType, AttributeTypesResource> implements AttributeTypesResource {

    public AttributeTypesResourceImpl(SMClient smClient) {
        super(smClient);
    }

    @Override
    public String getURI() {
        return "attributetypes";
    }

    @Override
    public ObjectMaker<AttributeType> initializeMaker() {
        return new ObjectMaker<>(AttributeType.class);
    }

    @Override
    protected AttributeTypesResource currentContext() {
        return this;
    }
}
