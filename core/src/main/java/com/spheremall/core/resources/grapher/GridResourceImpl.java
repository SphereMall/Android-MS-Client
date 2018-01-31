package com.spheremall.core.resources.grapher;

import com.spheremall.core.SMClient;
import com.spheremall.core.api.configuration.Method;
import com.spheremall.core.api.response.ResponseMonada;
import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.Response;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.MethodNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.filters.Filter;
import com.spheremall.core.filters.Predicate;
import com.spheremall.core.makers.GridMaker;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;
import com.spheremall.core.specifications.base.FilterSpecification;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GridResourceImpl extends BaseResource<Entity, GridResource> implements GridResource {

    public GridResourceImpl(SMClient smClient) {
        super(smClient);
    }

    public GridResourceImpl(SMClient client, String version) {
        super(client, version);
    }

    @Override
    public String getURI() {
        return "grid";
    }

    @Override
    public ObjectMaker<Entity> initializeMaker() {
        return new ObjectMaker<>(Entity.class);
    }

    @Override
    public GridResource filters(Filter filter) {
        throw new MethodNotFoundException("Method filters(Filter filter) can not be use with GRID");
    }

    @Override
    public GridResource filters(Predicate... filters) {
        throw new MethodNotFoundException("Method filters(Predicate... filters) can not be use with GRID");
    }

    @Override
    public GridResource filters(FilterSpecification filter) {
        throw new MethodNotFoundException("Method filters(FilterSpecification filter) can not be use with GRID");
    }

    @Override
    protected GridResource currentContext() {
        return this;
    }

    @Override
    public Response<List<Entity>> all() throws ServiceException, IOException, EntityNotFoundException {

        HashMap<String, String> params = getQueryParams();

        ResponseMonada responseMonada = request
                .handle(Method.GET, "", params);

        if (responseMonada.hasError()) {
            throw new EntityNotFoundException(responseMonada.getErrorResponse().error.message);
        }
        maker = new GridMaker(Entity.class);
        return maker.makeAsList(responseMonada.getResponse());
    }

    @Override
    public Response<Entity> get(int id) throws ServiceException, IOException, EntityNotFoundException {
        throw new MethodNotFoundException("Method data() can not be use with GRID");
    }

    @Override
    public Response<Entity> create(HashMap<String, String> params) throws EntityNotFoundException, IOException, ServiceException {
        throw new MethodNotFoundException("Method create() can not be use with GRID");
    }

    @Override
    public Response<Entity> update(Integer id, HashMap<String, String> params) throws EntityNotFoundException, IOException, ServiceException {
        throw new MethodNotFoundException("Method update() can not be use with GRID");
    }

    @Override
    public Boolean delete(Integer id) throws EntityNotFoundException, IOException, ServiceException {
        throw new MethodNotFoundException("Method delete() can not be use with GRID");
    }
}
