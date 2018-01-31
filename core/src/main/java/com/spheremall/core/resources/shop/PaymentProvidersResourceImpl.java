package com.spheremall.core.resources.shop;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.shop.PaymentProvider;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

public class PaymentProvidersResourceImpl extends BaseResource<PaymentProvider, PaymentProvidersResource> implements PaymentProvidersResource {

    public PaymentProvidersResourceImpl(SMClient smClient) {
        super(smClient);
    }

    public PaymentProvidersResourceImpl(SMClient client, String version) {
        super(client, version);
    }

    @Override
    public String getURI() {
        return "paymentproviders";
    }

    @Override
    public ObjectMaker<PaymentProvider> initializeMaker() {
        return new ObjectMaker<>(PaymentProvider.class);
    }

    @Override
    protected PaymentProvidersResource currentContext() {
        return this;
    }
}
