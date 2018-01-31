package com.spheremall.core.resources.shop;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.shop.DeliveryProvider;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

public class DeliveryProvidersResourceImpl extends BaseResource<DeliveryProvider, DeliveryProvidersResource> implements DeliveryProvidersResource {

    public DeliveryProvidersResourceImpl(SMClient smClient) {
        super(smClient);
    }

    public DeliveryProvidersResourceImpl(SMClient client, String version) {
        super(client, version);
    }

    @Override
    public String getURI() {
        return "deliveryproviders";
    }

    @Override
    public ObjectMaker<DeliveryProvider> initializeMaker() {
        return new ObjectMaker<>(DeliveryProvider.class);
    }

    @Override
    protected DeliveryProvidersResource currentContext() {
        return this;
    }
}
