package com.spheremall.core.resources.full;

import com.spheremall.core.SMClient;
import com.spheremall.core.api.configuration.Method;
import com.spheremall.core.api.response.ResponseMonada;
import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.Response;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.resources.BaseResource;
import com.spheremall.core.resources.Resource;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public abstract class FullResourceImpl<T extends Entity, R extends Resource> extends BaseResource<T, R> {

    public FullResourceImpl(SMClient smClient) {
        super(smClient);
    }

    public Response<List<T>> full() throws EntityNotFoundException, ServiceException, IOException {
        String uriAppend = "full";
        return getListOfFullEntities(uriAppend);
    }

    public Response<T> full(int id) throws EntityNotFoundException, ServiceException, IOException {
        String uriAppend = "full";
        if (id > 0) {
            uriAppend = uriAppend + "/" + id;
        }
        Response<List<T>> entities = getListOfFullEntities(uriAppend);

        if (entities.data().size() == 0) {
            throw new EntityNotFoundException();
        }
        return new Response<>(entities.data().get(0), entities.meta());
    }

    public Response<T> full(String urlCode) throws EntityNotFoundException, ServiceException, IOException {
        String uriAppend = "full";
        if (urlCode != null && !urlCode.isEmpty()) {
            uriAppend = "url/" + urlCode;
        }
        Response<List<T>> entities = getListOfFullEntities(uriAppend);

        if (entities.data().size() == 0) {
            throw new EntityNotFoundException();
        }
        return new Response<>(entities.data().get(0), entities.meta());
    }

    private Response<List<T>> getListOfFullEntities(String uriAppend) throws EntityNotFoundException, IOException, ServiceException {
        HashMap<String, String> params = getQueryParams();
        ResponseMonada responseMonada = request.handle(Method.GET, uriAppend, params);
        if (responseMonada.hasError()) {
            throw new EntityNotFoundException();
        }
        return maker.makeAsList(responseMonada.getResponse());
    }
}
