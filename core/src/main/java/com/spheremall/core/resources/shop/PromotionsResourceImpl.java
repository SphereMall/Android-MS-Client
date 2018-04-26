package com.spheremall.core.resources.shop;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.shop.Promotion;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

public class PromotionsResourceImpl extends BaseResource<Promotion, PromotionsResource> implements PromotionsResource {

    public PromotionsResourceImpl(SMClient smClient) {
        super(smClient);
    }

    public PromotionsResourceImpl(SMClient client, String version) {
        super(client, version);
    }

    @Override
    public String getURI() {
        return "promotions";
    }

    @Override
    public ObjectMaker<Promotion> initializeMaker() {
        return new ObjectMaker<>(Promotion.class);
    }

    @Override
    protected PromotionsResource currentContext() {
        return this;
    }
}
