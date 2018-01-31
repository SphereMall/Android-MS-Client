package com.spheremall.core.resources.shop;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.shop.DeliveryPaymentRelation;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

public class DeliveryPaymentsResourceImpl extends BaseResource<DeliveryPaymentRelation, DeliveryPaymentsResource> implements DeliveryPaymentsResource {

    public DeliveryPaymentsResourceImpl(SMClient smClient) {
        super(smClient);
    }

    public DeliveryPaymentsResourceImpl(SMClient client, String version) {
        super(client, version);
    }

    @Override
    public String getURI() {
        return "deliverypaymentrelations";
    }

    @Override
    public ObjectMaker<DeliveryPaymentRelation> initializeMaker() {
        return new ObjectMaker<>(DeliveryPaymentRelation.class);
    }

    @Override
    protected DeliveryPaymentsResource currentContext() {
        return this;
    }
}
