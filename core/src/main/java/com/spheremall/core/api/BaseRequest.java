package com.spheremall.core.api;

import com.spheremall.core.api.configuration.Method;
import com.spheremall.core.api.response.ResponseMonada;
import com.spheremall.core.exceptions.SphereMallException;

import java.io.IOException;
import java.util.HashMap;

public interface BaseRequest {
    ResponseMonada handle(Method method, String uriAppend, HashMap<String, String> queryParams) throws SphereMallException, IOException;
}