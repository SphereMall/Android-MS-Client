package com.spheremall.core.resources;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.Response;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.filters.Filter;
import com.spheremall.core.filters.InPredicate;
import com.spheremall.core.filters.Predicate;
import com.spheremall.core.specifications.base.FilterSpecification;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Resource<R extends Resource, E extends Entity> {

    R limit(int limit);

    int getLimit();

    R offset(int offset);

    int getOffset();

    R ids(Integer... ids);

    R ids(List<Integer> ids);

    List<Integer> getIds();

    R fields(String... fields);

    List<String> getFields();

    R filters(Filter filter);

    R filters(Predicate... filters);

    R filters(FilterSpecification filter);

    Filter getFilters();

    R sort(String... sort);

    R in(String field, String... values);

    InPredicate getIn();

    R addExtraParam(String key, String value);

    Map<String, String> getExtraParams();

    int count() throws IOException, SphereMallException;

    Response<E> get(int id) throws IOException, SphereMallException;

    Response<List<E>> all() throws IOException, SphereMallException;

    Response<E> first() throws IOException, SphereMallException;

    Response<E> create(HashMap<String, String> params) throws IOException, SphereMallException;

    Response<E> update(Integer id, HashMap<String, String> params) throws IOException, SphereMallException;

    Boolean delete(Integer id) throws IOException, SphereMallException;
}
