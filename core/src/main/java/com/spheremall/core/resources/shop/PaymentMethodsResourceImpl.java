package com.spheremall.core.resources.shop;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.shop.PaymentMethod;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

public class PaymentMethodsResourceImpl extends BaseResource<PaymentMethod, PaymentMethodsResource> implements PaymentMethodsResource {

    public PaymentMethodsResourceImpl(SMClient smClient) {
        super(smClient);
    }

    public PaymentMethodsResourceImpl(SMClient client, String version) {
        super(client, version);
    }

    @Override
    public String getURI() {
        return "paymentmethods";
    }

    @Override
    public ObjectMaker<PaymentMethod> initializeMaker() {
        return new ObjectMaker<>(PaymentMethod.class);
    }

    @Override
    protected PaymentMethodsResource currentContext() {
        return this;
    }
}
