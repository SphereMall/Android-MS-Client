package com.spheremall.core.resources.shop;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.shop.Vat;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

public class VatsResourceImpl extends BaseResource<Vat, VatsResource> implements VatsResource {

    public VatsResourceImpl(SMClient smClient) {
        super(smClient);
    }

    public VatsResourceImpl(SMClient client, String version) {
        super(client, version);
    }

    @Override
    public String getURI() {
        return "vat";
    }

    @Override
    public ObjectMaker<Vat> initializeMaker() {
        return new ObjectMaker<>(Vat.class);
    }

    @Override
    protected VatsResource currentContext() {
        return this;
    }
}
