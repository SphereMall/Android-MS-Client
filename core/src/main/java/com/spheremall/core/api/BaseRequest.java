package com.spheremall.core.api;

import com.spheremall.core.api.configuration.Method;
import com.spheremall.core.api.response.ResponseMonada;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;

import java.io.IOException;
import java.util.HashMap;

public interface BaseRequest {

    ResponseMonada handle(Method method, String uriAppend, HashMap<String, String> queryParams) throws ServiceException, IOException, EntityNotFoundException;

}
