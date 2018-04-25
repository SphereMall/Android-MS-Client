package com.spheremall.core.resources.shop;

import com.spheremall.core.SMClient;
import com.spheremall.core.api.configuration.Method;
import com.spheremall.core.api.response.ResponseMonada;
import com.spheremall.core.entities.Response;
import com.spheremall.core.entities.shop.BasketOrder;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;
import com.spheremall.core.utils.TextUtils;

import java.io.IOException;
import java.util.HashMap;

public class BasketResourceImpl extends BaseResource<BasketOrder, BasketResource> implements BasketResource {

    public BasketResourceImpl(SMClient client, String version) {
        super(client, version);
    }

    @Override
    public String getURI() {
        return "basket";
    }

    @Override
    public ObjectMaker<BasketOrder> initializeMaker() {
        return new ObjectMaker<>(BasketOrder.class);
    }

    @Override
    protected BasketResource currentContext() {
        return this;
    }

    @Override
    public Response<BasketOrder> get(int id) throws SphereMallException, IOException{
        HashMap<String, String> params = new HashMap<>();

        if (fields.size() > 0) {
            params.put("fields", TextUtils.join(",", fields));
        }

        String uriAppend = "byId/" + Integer.toString(id);

        ResponseMonada responseMonada = request
                .handle(Method.GET, uriAppend, params);

        if (responseMonada.hasError()) {
            throw new EntityNotFoundException(responseMonada.getErrorResponse());
        }
        return maker.makeSingle(responseMonada.getResponse());
    }

    @Override
    public Response<BasketOrder> getByUserId(int userId) throws SphereMallException, IOException{
        HashMap<String, String> params = new HashMap<>();

        if (fields.size() > 0) {
            params.put("fields", TextUtils.join(",", fields));
        }

        ResponseMonada responseMonada = request
                .handle(Method.GET, String.valueOf(userId), params);

        if (responseMonada.hasError()) {
            throw new EntityNotFoundException(responseMonada.getErrorResponse());
        }
        return maker.makeSingle(responseMonada.getResponse());
    }

    @Override
    public BasketOrder createNew() throws SphereMallException, IOException{
        ResponseMonada responseMonada = request.handle(Method.POST, "new", new HashMap<>());
        if (responseMonada.hasError()) {
            throw new EntityNotFoundException(responseMonada.getErrorResponse());
        }
        return maker.makeSingle(responseMonada.getResponse()).data();
    }

    @Override
    public Response<BasketOrder> update(Integer id, HashMap<String, String> params) throws SphereMallException, IOException{
        ResponseMonada responseMonada = request.handle(Method.PUT, "", params);
        if (responseMonada.hasError()) {
            throw new EntityNotFoundException(responseMonada.getErrorResponse());
        }
        return maker.makeSingle(responseMonada.getResponse());
    }

    @Override
    public BasketOrder removeItems(HashMap<String, String> data) throws SphereMallException, IOException{

        ResponseMonada responseMonada = request.handle(Method.DELETE, "", data);
        if (responseMonada.hasError()) {
            throw new EntityNotFoundException(responseMonada.getErrorResponse());
        }

        return maker.makeSingle(responseMonada.getResponse()).data();
    }
}
