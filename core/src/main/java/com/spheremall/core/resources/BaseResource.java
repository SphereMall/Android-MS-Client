package com.spheremall.core.resources;

import com.spheremall.core.SMClient;
import com.spheremall.core.api.BaseRequest;
import com.spheremall.core.api.Request;
import com.spheremall.core.api.configuration.Method;
import com.spheremall.core.api.response.ResponseMonada;
import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.Response;
import com.spheremall.core.entities.pojo.Count;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.filters.Filter;
import com.spheremall.core.filters.InPredicate;
import com.spheremall.core.filters.Predicate;
import com.spheremall.core.makers.CountMaker;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.specifications.base.FilterSpecification;
import com.spheremall.core.utils.TextUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseResource<E extends Entity, R extends Resource> implements Resource<R, E> {

    protected SMClient smClient = null;
    protected String version;

    protected BaseRequest request;
    protected ObjectMaker<E> maker;

    protected Filter filter;
    protected int limit = 10;
    protected int offset = 0;
    protected List<Integer> ids = new ArrayList<>();
    protected List<String> fields = new ArrayList<>();
    protected List<String> sort = new ArrayList<>();
    protected Map<String, String> queryExtraParams = new HashMap<>();
    protected InPredicate in;

    public BaseResource(SMClient smClient) {
        this.smClient = smClient;
        this.version = smClient.getVersion();
        this.request = new Request(this.smClient, this);
        this.maker = initializeMaker();
    }

    public BaseResource(SMClient client, String version) {
        this(client);
        this.version = version;
    }

    public abstract String getURI();

    public abstract ObjectMaker<E> initializeMaker();

    protected abstract R currentContext();

    public String getVersion() {
        return version;
    }

    @Override
    public R limit(int limit) {
        this.limit = limit;
        return currentContext();
    }

    @Override
    public int getLimit() {
        return limit;
    }

    @Override
    public R offset(int offset) {
        this.offset = offset;
        return currentContext();
    }

    @Override
    public int getOffset() {
        return offset;
    }

    @Override
    public R ids(Integer... ids) {
        this.ids = Arrays.asList(ids);
        return currentContext();
    }

    @Override
    public R ids(List<Integer> ids) {
        this.ids = ids;
        return currentContext();
    }

    @Override
    public List<Integer> getIds() {
        return ids;
    }

    @Override
    public R fields(String... fields) {
        this.fields = Arrays.asList(fields);
        return currentContext();
    }

    @Override
    public List<String> getFields() {
        return fields;
    }

    @Override
    public R filters(Filter filter) {
        this.filter = filter;
        return currentContext();
    }

    @Override
    public R filters(Predicate... filters) {
        this.filter = new Filter(Arrays.asList(filters));
        return currentContext();
    }

    @Override
    public R filters(FilterSpecification filter) {
        this.filter = filter.asFilter();
        return currentContext();
    }

    @Override
    public Filter getFilters() {
        return this.filter;
    }

    @Override
    public R in(String field, String... values) {
        in = new InPredicate(field, Arrays.asList(values));
        return currentContext();
    }

    @Override
    public InPredicate getIn() {
        return in;
    }

    @Override
    public R sort(String... sort) {
        this.sort = Arrays.asList(sort);
        return currentContext();
    }

    @Override
    public R addExtraParam(String key, String value) {
        queryExtraParams.put(key, value);
        return currentContext();
    }

    @Override
    public Map<String, String> getExtraParams() {
        return queryExtraParams;
    }

    @Override
    public int count() throws IOException, SphereMallException {
        String uriAppend = "count";

        HashMap<String, String> params = getQueryParams();
        ResponseMonada responseMonada = request.handle(Method.GET, uriAppend, params);

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
    public Response<E> get(int id) throws IOException, SphereMallException {

        HashMap<String, String> params = new HashMap<>();

        if (fields.size() > 0) {
            params.put("fields", TextUtils.join(",", fields));
        }

        ResponseMonada responseMonada = request
                .handle(Method.GET, Integer.toString(id), params);

        if (responseMonada.hasError()) {
            throw new EntityNotFoundException(responseMonada.getErrorResponse());
        }
        return maker.makeSingle(responseMonada.getResponse());
    }

    @Override
    public Response<List<E>> all() throws IOException, SphereMallException {
        String uriAppend = "by";

        HashMap<String, String> params = getQueryParams();
        ResponseMonada responseMonada = request
                .handle(Method.GET, uriAppend, params);

        if (responseMonada.hasError()) {
            throw new EntityNotFoundException(responseMonada.getErrorResponse());
        }

        return maker.makeAsList(responseMonada.getResponse());
    }

    @Override
    public Response<E> first() throws IOException, SphereMallException {
        String uriAppend = "by";

        HashMap<String, String> params = getQueryParams();
        ResponseMonada responseMonada = request
                .handle(Method.GET, uriAppend, params);

        if (responseMonada.hasError()) {
            throw new EntityNotFoundException();
        }

        Response<List<E>> entities = maker.makeAsList(responseMonada.getResponse());

        if (entities.data().size() == 0) {
            throw new EntityNotFoundException();
        }

        return new Response<>(entities.data().get(0), entities.meta());
    }

    @Override
    public Response<E> create(HashMap<String, String> params) throws IOException, SphereMallException {
        ResponseMonada responseMonada = request.handle(Method.POST, "", params);
        if (responseMonada.hasError()) {
            throw new EntityNotFoundException(responseMonada.getErrorResponse());
        }
        return maker.makeSingle(responseMonada.getResponse());
    }

    @Override
    public Response<E> update(Integer id, HashMap<String, String> params) throws IOException, SphereMallException {
        ResponseMonada responseMonada = request.handle(Method.PUT, String.valueOf(id), params);
        if (responseMonada.hasError()) {
            throw new EntityNotFoundException(responseMonada.getErrorResponse());
        }
        return maker.makeSingle(responseMonada.getResponse());
    }

    @Override
    public Boolean delete(Integer id) throws IOException, SphereMallException {
        ResponseMonada responseMonada = request.handle(Method.DELETE, String.valueOf(id), new HashMap<>());
        if (responseMonada.hasError()) {
            throw new EntityNotFoundException(responseMonada.getErrorResponse());
        }
        return true;
    }

    protected HashMap<String, String> getQueryParams() {
        HashMap<String, String> params = new HashMap<>();

        params.put("limit", String.valueOf(limit));
        params.put("offset", String.valueOf(offset));

        if (ids.size() > 0) {
            params.put("ids", TextUtils.join(",", ids));
        }

        if (fields.size() > 0) {
            params.put("fields", TextUtils.join(",", fields));
        }

        if (in != null) {
            params.put("in", in.toString());
        }

        if (sort.size() > 0) {
            params.put("sort", TextUtils.join(",", sort));
        }

        if (filter != null) {
            params.put("where", filter.toString());
        }

        if (queryExtraParams.size() > 0) {
            params.putAll(queryExtraParams);
        }

        return params;
    }
}