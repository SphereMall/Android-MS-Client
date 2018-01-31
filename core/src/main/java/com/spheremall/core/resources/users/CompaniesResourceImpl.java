package com.spheremall.core.resources.users;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.users.Company;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

public class CompaniesResourceImpl extends BaseResource<Company, CompaniesResource> implements CompaniesResource {

    public CompaniesResourceImpl(SMClient smClient) {
        super(smClient);
    }

    public CompaniesResourceImpl(SMClient client, String version) {
        super(client, version);
    }

    @Override
    public String getURI() {
        return "companies";
    }

    @Override
    public ObjectMaker<Company> initializeMaker() {
        return new ObjectMaker<>(Company.class);
    }

    @Override
    protected CompaniesResource currentContext() {
        return this;
    }
}
