package com.spheremall.core.resources.full;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.Response;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.Resource;

import java.io.IOException;
import java.util.List;

public interface DetailResource<T extends Entity, R extends Resource> extends Resource<R, T> {

    Response<List<T>> detail() throws SphereMallException, IOException;

    T detail(int id) throws SphereMallException, IOException;

    T detail(String urlCode) throws SphereMallException, IOException;
}
