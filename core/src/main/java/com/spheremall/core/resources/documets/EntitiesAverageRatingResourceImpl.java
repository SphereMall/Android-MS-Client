package com.spheremall.core.resources.documets;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.documents.EntityAverageRating;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

public class EntitiesAverageRatingResourceImpl extends BaseResource<EntityAverageRating, EntitiesAverageRatingResource> implements EntitiesAverageRatingResource {

    public EntitiesAverageRatingResourceImpl(SMClient smClient) {
        super(smClient);
    }

    public EntitiesAverageRatingResourceImpl(SMClient client, String version) {
        super(client, version);
    }

    @Override
    public String getURI() {
        return "entitiesaveragerating";
    }

    @Override
    public ObjectMaker<EntityAverageRating> initializeMaker() {
        return new ObjectMaker<>(EntityAverageRating.class);
    }

    @Override
    protected EntitiesAverageRatingResource currentContext() {
        return this;
    }
}
