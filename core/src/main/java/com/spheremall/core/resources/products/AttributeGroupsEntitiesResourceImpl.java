package com.spheremall.core.resources.products;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.products.AttributeGroupsEntities;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

public class AttributeGroupsEntitiesResourceImpl extends BaseResource<AttributeGroupsEntities, AttributeGroupsEntitiesResource> implements AttributeGroupsEntitiesResource {

    public AttributeGroupsEntitiesResourceImpl(SMClient smClient) {
        super(smClient);
    }

    @Override
    public String getURI() {
        return "attributegroupsentities";
    }

    @Override
    public ObjectMaker<AttributeGroupsEntities> initializeMaker() {
        return new ObjectMaker<>(AttributeGroupsEntities.class);
    }

    @Override
    protected AttributeGroupsEntitiesResource currentContext() {
        return this;
    }
}
