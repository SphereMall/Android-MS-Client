package com.spheremall.core.resources.products;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.products.UnitOfMeasure;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

public class UnitOfMeasureResourceImpl extends BaseResource<UnitOfMeasure, UnitOfMeasureResource> implements UnitOfMeasureResource {

    public UnitOfMeasureResourceImpl(SMClient smClient) {
        super(smClient);
    }

    public UnitOfMeasureResourceImpl(SMClient client, String version) {
        super(client, version);
    }

    @Override
    public String getURI() {
        return "unitofmeasure";
    }

    @Override
    public ObjectMaker<UnitOfMeasure> initializeMaker() {
        return new ObjectMaker<>(UnitOfMeasure.class);
    }

    @Override
    protected UnitOfMeasureResource currentContext() {
        return this;
    }
}
