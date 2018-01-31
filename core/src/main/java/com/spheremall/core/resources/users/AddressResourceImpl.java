package com.spheremall.core.resources.users;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.users.Address;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

public class AddressResourceImpl extends BaseResource<Address, AddressResource> implements AddressResource {

    public AddressResourceImpl(SMClient smClient) {
        super(smClient);
    }

    public AddressResourceImpl(SMClient client, String version) {
        super(client, version);
    }

    @Override
    public String getURI() {
        return "addresses";
    }

    @Override
    public ObjectMaker<Address> initializeMaker() {
        return new ObjectMaker<>(Address.class);
    }

    @Override
    protected AddressResource currentContext() {
        return this;
    }
}
