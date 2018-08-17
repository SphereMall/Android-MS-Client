package com.spheremall.core.resources.grapher;

import com.spheremall.core.SMClient;
import com.spheremall.core.api.configuration.Method;
import com.spheremall.core.api.response.ResponseMonada;
import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.Facets;
import com.spheremall.core.entities.Response;
import com.spheremall.core.entities.pojo.Count;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.MethodNotFoundException;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.filters.Filter;
import com.spheremall.core.filters.Predicate;
import com.spheremall.core.filters.grid.GridFilter;
import com.spheremall.core.makers.CountMaker;
import com.spheremall.core.makers.FacetsMaker;
import com.spheremall.core.makers.GridMaker;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.specifications.base.FilterSpecification;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GridResourceImpl extends GrapherResource<GridResource> implements GridResource {

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
        this.filter = filter;
        return this;
    }

    @Override
    public GridResource filters(Predicate... filters) {
        this.filter = new Filter(Arrays.asList(filters));
        return this;
    }

    @Override
    public GridResource filters(FilterSpecification filter) {
        GridFilter gridFilter = new GridFilter();
        gridFilter.setFilters(filter.asFilter().getFilters());
        this.filter = gridFilter;
        return this;
    }

    @Override
    protected GridResource currentContext() {
        return this;
    }

    @Override
    public Response<List<Entity>> all() throws SphereMallException, IOException {

        HashMap<String, String> params = getQueryParams();
        params.put("actions", "promotions");

        ResponseMonada responseMonada = request
                .handle(Method.GET, "", params);

        if (responseMonada.hasError()) {
            throw new EntityNotFoundException(responseMonada.getErrorResponse());
        }
        maker = new GridMaker(Entity.class);
        return maker.makeAsList(responseMonada.getResponse());
    }

    @Override
    public Response<Entity> get(int id) {
        throw new MethodNotFoundException("Method data() can not be use with GRID");
    }

    @Override
    public Response<Facets> facets() throws SphereMallException, IOException {
        HashMap<String, String> params = getQueryParams();
        ResponseMonada responseMonada = request.handle(Method.GET, "/filter", params);

        if (responseMonada.hasError()) {
            throw new EntityNotFoundException();
        }

        FacetsMaker facetsMaker = new FacetsMaker(Facets.class);
        return facetsMaker.makeSingle(responseMonada.getResponse());
    }

    @Override
    public int count() throws SphereMallException, IOException {

        HashMap<String, String> params = getQueryParams();
        ResponseMonada responseMonada = request.handle(Method.GET, "count", params);

        if (responseMonada.hasError()) {
            throw new EntityNotFoundException();
        }

        CountMaker countMaker = new CountMaker(Count.class);
        Count count = countMaker.makeSingle(responseMonada.getResponse()).data();

        if (count.data == null || count.data.size() == 0) {
            throw new EntityNotFoundException(responseMonada.getErrorResponse());
        }
        return count.data.get(0).attributes.count;

    }

    @Override
    public Response<Entity> create(HashMap<String, String> params) {
        throw new MethodNotFoundException("Method create() can not be use with GRID");
    }

    @Override
    public Response<Entity> update(Integer id, HashMap<String, String> params) {
        throw new MethodNotFoundException("Method update() can not be use with GRID");
    }

    @Override
    public Boolean delete(Integer id) {
        throw new MethodNotFoundException("Method delete() can not be use with GRID");
    }
}
