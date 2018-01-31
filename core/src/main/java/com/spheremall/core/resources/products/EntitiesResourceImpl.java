package com.spheremall.core.resources.products;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.SMEntity;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

public class EntitiesResourceImpl extends BaseResource<SMEntity, EntitiesResource> implements EntitiesResource {

    public EntitiesResourceImpl(SMClient smClient) {
        super(smClient);
    }

    @Override
    public String getURI() {
        return "entities";
    }

    @Override
    public ObjectMaker<SMEntity> initializeMaker() {
        return new ObjectMaker<>(SMEntity.class);
    }

    @Override
    protected EntitiesResource currentContext() {
        return this;
    }
}
