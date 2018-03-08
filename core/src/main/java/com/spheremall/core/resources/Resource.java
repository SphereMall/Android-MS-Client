package com.spheremall.core.resources;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.Response;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.filters.Filter;
import com.spheremall.core.filters.InPredicate;
import com.spheremall.core.filters.Predicate;
import com.spheremall.core.specifications.base.FilterSpecification;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

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

    int count() throws EntityNotFoundException, IOException, ServiceException;

    Response<E> get(int id) throws ServiceException, IOException, EntityNotFoundException;

    Response<List<E>> all() throws ServiceException, IOException, EntityNotFoundException;

    Response<E> first() throws ServiceException, IOException, EntityNotFoundException;

    Response<E> create(HashMap<String, String> params) throws EntityNotFoundException, IOException, ServiceException;

    Response<E> update(Integer id, HashMap<String, String> params) throws EntityNotFoundException, IOException, ServiceException;

    Boolean delete(Integer id) throws EntityNotFoundException, IOException, ServiceException;
}
