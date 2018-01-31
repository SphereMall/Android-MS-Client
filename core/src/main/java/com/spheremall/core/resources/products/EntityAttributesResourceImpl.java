package com.spheremall.core.resources.products;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.products.EntityAttribute;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

public class EntityAttributesResourceImpl extends BaseResource<EntityAttribute, EntityAttributesResource> implements EntityAttributesResource {

    public EntityAttributesResourceImpl(SMClient smClient) {
        super(smClient);
    }

    @Override
    public String getURI() {
        return "entityattributes";
    }

    @Override
    public ObjectMaker<EntityAttribute> initializeMaker() {
        return new ObjectMaker<>(EntityAttribute.class);
    }

    @Override
    protected EntityAttributesResource currentContext() {
        return this;
    }
}
