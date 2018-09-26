package com.spheremall.core.resources.products;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.products.AttributeDisplayType;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

public class AttributeDisplayTypesResourceImpl extends BaseResource<AttributeDisplayType, AttributeDisplayTypesResource> implements AttributeDisplayTypesResource {

    public AttributeDisplayTypesResourceImpl(SMClient smClient) {
        super(smClient);
    }

    public AttributeDisplayTypesResourceImpl(SMClient client, String version) {
        super(client, version);
    }

    @Override
    public String getURI() {
        return "attributedisplaytypes";
    }

    @Override
    public ObjectMaker<AttributeDisplayType> initializeMaker() {
        return new ObjectMaker<>(AttributeDisplayType.class);
    }

    @Override
    protected AttributeDisplayTypesResource currentContext() {
        return this;
    }
}
