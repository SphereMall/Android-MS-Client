package com.spheremall.core.resources.products;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.price.PriceConfiguration;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

public class PriceConfigurationResourceImpl extends BaseResource<PriceConfiguration, PriceConfigurationResource> implements PriceConfigurationResource {

    public PriceConfigurationResourceImpl(SMClient smClient) {
        super(smClient);
    }

    public PriceConfigurationResourceImpl(SMClient client, String version) {
        super(client, version);
    }

    @Override
    public String getURI() {
        return "priceconfigurations";
    }

    @Override
    public ObjectMaker<PriceConfiguration> initializeMaker() {
        return new ObjectMaker<>(PriceConfiguration.class);
    }

    @Override
    protected PriceConfigurationResource currentContext() {
        return this;
    }
}
