package com.spheremall.core.resources.users;

import com.spheremall.core.SMClient;
import com.spheremall.core.api.configuration.Method;
import com.spheremall.core.api.response.ResponseMonada;
import com.spheremall.core.entities.SMEntity;
import com.spheremall.core.entities.products.Product;
import com.spheremall.core.entities.users.WishListItem;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.filters.FilterOperators;
import com.spheremall.core.filters.Predicate;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;
import com.spheremall.core.resources.products.EntitiesResource;
import com.spheremall.core.resources.products.EntitiesResourceImpl;
import com.spheremall.core.resources.products.ProductResource;
import com.spheremall.core.resources.products.ProductResourceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    @Override
    public WishListItem addToWishList(int userId, int objectId, String entity) throws SphereMallException, IOException {
        EntitiesResource entitiesResource = new EntitiesResourceImpl(smClient);
        SMEntity response = entitiesResource
                .filters(new Predicate("code", FilterOperators.EQUAL, entity)).first().data();
        return addToWishList(userId, objectId, response.getId());
    }

    @Override
    public WishListItem addToWishList(int userId, int objectId, int entityId) throws SphereMallException, IOException {

        HashMap<String, String> params = new HashMap<>();
        params.put("userId", String.valueOf(userId));
        params.put("objectId", String.valueOf(objectId));
        params.put("entityId", String.valueOf(entityId));

        ResponseMonada responseMonada = request.handle(Method.POST, "", params);
        if (responseMonada.hasError()) {
            throw new EntityNotFoundException(responseMonada.getErrorResponse());
        }
        List<WishListItem> entities = maker.makeAsList(responseMonada.getResponse()).data();
        if (entities.size() > 0) {
            WishListItem wishListItem = entities.get(0);
            wishListItem.entity = smClient.products().full(wishListItem.objectId).data();
            return wishListItem;
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public List<WishListItem> getWishList(int userId, int limit, int offset) throws SphereMallException, IOException {
        List<WishListItem> wishListItems = filters(new Predicate("userId", FilterOperators.EQUAL, String.valueOf(userId)))
                .limit(100)
                .all().data();

        List<Integer> ids = new ArrayList<>();
        for (WishListItem item : wishListItems) {
            ids.add(item.objectId);
        }

        ProductResource productResource = new ProductResourceImpl(smClient);
        List<Product> entities = productResource
                .ids(ids)
                .limit(limit)
                .offset(offset)
                .detail().data();

        List<WishListItem> responseItems = new ArrayList<>();

        for (Product entity : entities) {
            for (WishListItem item : wishListItems) {
                if (item.objectId == entity.getId()) {
                    item.entity = entity;
                    responseItems.add(item);
                }
            }
        }
        return responseItems;
    }
}
