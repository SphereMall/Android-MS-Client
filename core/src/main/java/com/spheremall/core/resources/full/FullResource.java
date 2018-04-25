package com.spheremall.core.resources.full;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.Response;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.Resource;

import java.io.IOException;
import java.util.List;

public interface FullResource<T extends Entity, R extends Resource> extends Resource<R, T> {

    Response<List<T>> full() throws SphereMallException, IOException;

    Response<T> full(int id) throws SphereMallException, IOException;

    Response<T> full(String urlCode) throws SphereMallException, IOException;

}
