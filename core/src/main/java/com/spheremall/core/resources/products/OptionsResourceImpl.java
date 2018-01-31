package com.spheremall.core.resources.products;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.products.Option;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

public class OptionsResourceImpl extends BaseResource<Option, OptionsResource> implements OptionsResource {

    public OptionsResourceImpl(SMClient smClient) {
        super(smClient);
    }

    @Override
    public String getURI() {
        return "options";
    }

    @Override
    public ObjectMaker<Option> initializeMaker() {
        return new ObjectMaker<>(Option.class);
    }

    @Override
    protected OptionsResource currentContext() {
        return this;
    }
}
