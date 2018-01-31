package com.spheremall.core.resources.shop;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.shop.OrderStatus;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

public class OrderStatusesResourceImpl extends BaseResource<OrderStatus, OrderStatusesResource> implements OrderStatusesResource {

    public OrderStatusesResourceImpl(SMClient smClient) {
        super(smClient);
    }

    public OrderStatusesResourceImpl(SMClient client, String version) {
        super(client, version);
    }

    @Override
    public String getURI() {
        return "orderstatuses";
    }

    @Override
    public ObjectMaker<OrderStatus> initializeMaker() {
        return new ObjectMaker<>(OrderStatus.class);
    }

    @Override
    protected OrderStatusesResource currentContext() {
        return this;
    }
}
