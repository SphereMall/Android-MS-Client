package com.spheremall.core.resources.products;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.products.FunctionalName;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

public class FunctionalNamesResourceImpl extends BaseResource<FunctionalName, FunctionalNamesResource> implements FunctionalNamesResource {

    public FunctionalNamesResourceImpl(SMClient smClient) {
        super(smClient);
    }

    @Override
    public String getURI() {
        return "functionalnames";
    }

    @Override
    public ObjectMaker<FunctionalName> initializeMaker() {
        return new ObjectMaker<>(FunctionalName.class);
    }

    @Override
    protected FunctionalNamesResource currentContext() {
        return this;
    }
}
