package com.spheremall.core.resources.users;

import com.spheremall.core.SMClient;
import com.spheremall.core.api.configuration.Method;
import com.spheremall.core.api.response.ResponseMonada;
import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.users.User;
import com.spheremall.core.entities.users.WishListItem;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.filters.FilterOperators;
import com.spheremall.core.filters.Predicate;
import com.spheremall.core.makers.GridMaker;
import com.spheremall.core.makers.Maker;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;
import com.spheremall.core.specifications.users.IsUserEmail;
import com.spheremall.core.specifications.users.IsUserSubscriber;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class UserResourceImpl extends BaseResource<User, UserResource> implements UserResource {

    public UserResourceImpl(SMClient smClient) {
        super(smClient);
    }

    @Override
    public String getURI() {
        return "users";
    }

    @Override
    public ObjectMaker<User> initializeMaker() {
        return new ObjectMaker<>(User.class);
    }

    @Override
    protected UserResource currentContext() {
        return this;
    }

    @Override
    public User subscribe(String email, String name) throws EntityNotFoundException, IOException, ServiceException {
        User user;
        try {
            user = filters(new IsUserEmail(email)).first().data();
        } catch (EntityNotFoundException e) {
            user = new User();
            user.email = email;
            user.name = name;
            user.guid = UUID.randomUUID().toString();
            user.isSubscriber = 1;
        }

        if (new IsUserSubscriber().isSatisfied(user)) {
            return user;
        }

        HashMap<String, String> params = new HashMap<>();

        params.put("isSubscriber", "1");
        params.put("email", user.email);
        params.put("name", user.name);
        params.put("guid", user.guid);

        if (user.getId() != 0) {
            return update(user.getId(), params).data();
        }

        return create(params).data();
    }

    @Override
    public User unSubscribe(String guId) throws EntityNotFoundException, IOException, ServiceException {
        User user;
        try {
            user = filters(new Predicate("guid", FilterOperators.EQUAL, guId)).first().data();
            HashMap<String, String> params = new HashMap<>();
            params.put("isSubscriber", "0");
            return update(user.getId(), params).data();
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    @Override
    public List<Entity> getWishList(int userId) throws EntityNotFoundException, IOException, ServiceException {
        ResponseMonada responseMonada = request.handle(Method.GET, "wishlist/" + userId, new HashMap<>());
        if (responseMonada.hasError()) {
            throw new EntityNotFoundException(responseMonada.getErrorResponse().error.message);
        }
        Maker<Entity> maker = new GridMaker(Entity.class);
        return maker.makeAsList(responseMonada.getResponse()).data();
    }

    @Override
    public Entity addToWishList(int userId, int objectId, String entity) throws EntityNotFoundException, ServiceException, IOException {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", String.valueOf(userId));
        params.put("objectId", String.valueOf(objectId));
        params.put("entity", entity);
        return smClient.wishListItems().create(params).data();
    }

    @Override
    public boolean removeFromWishList(int userId, int objectId, String entity) {
        try {
            WishListItem wishListItem = smClient.wishListItems()
                    .filters(
                            new Predicate("userId", FilterOperators.EQUAL, String.valueOf(userId)),
                            new Predicate("objectId", FilterOperators.EQUAL, String.valueOf(objectId)),
                            new Predicate("entity", FilterOperators.EQUAL, entity))
                    .first().data();
            return smClient.wishListItems().delete(wishListItem.getId());
        } catch (Throwable error) {
            return false;
        }
    }
}
