package com.spheremall.core.resources.products;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.products.AttributeGroup;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

public class AttributeGroupsResourceImpl extends BaseResource<AttributeGroup, AttributeGroupsResource> implements AttributeGroupsResource {

    public AttributeGroupsResourceImpl(SMClient smClient) {
        super(smClient);
    }

    @Override
    public String getURI() {
        return "attributegroups";
    }

    @Override
    public ObjectMaker<AttributeGroup> initializeMaker() {
        return new ObjectMaker<>(AttributeGroup.class);
    }

    @Override
    protected AttributeGroupsResource currentContext() {
        return this;
    }
}
