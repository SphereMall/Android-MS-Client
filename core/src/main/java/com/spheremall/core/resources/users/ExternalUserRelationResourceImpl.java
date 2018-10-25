package com.spheremall.core.resources.users;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.users.ExternalUserRelation;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

public class ExternalUserRelationResourceImpl extends BaseResource<ExternalUserRelation, ExternalUserRelationResource> implements ExternalUserRelationResource {

    public ExternalUserRelationResourceImpl(SMClient smClient) {
        super(smClient);
    }

    public ExternalUserRelationResourceImpl(SMClient client, String version) {
        super(client, version);
    }

    @Override
    public String getURI() {
        return "externaluserrelation";
    }

    @Override
    public ObjectMaker<ExternalUserRelation> initializeMaker() {
        return new ObjectMaker<>(ExternalUserRelation.class);
    }

    @Override
    protected ExternalUserRelationResource currentContext() {
        return this;
    }
}
