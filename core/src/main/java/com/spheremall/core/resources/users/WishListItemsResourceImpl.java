package com.spheremall.core.resources.users;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.users.WishListItem;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

public class WishListItemsResourceImpl extends BaseResource<WishListItem, WishListItemsResource> implements WishListItemsResource {

    public WishListItemsResourceImpl(SMClient smClient) {
        super(smClient);
    }

    public WishListItemsResourceImpl(SMClient client, String version) {
        super(client, version);
    }

    @Override
    public String getURI() {
        return "wishlistitems";
    }

    @Override
    public ObjectMaker<WishListItem> initializeMaker() {
        return new ObjectMaker<>(WishListItem.class);
    }

    @Override
    protected WishListItemsResource currentContext() {
        return this;
    }
}
