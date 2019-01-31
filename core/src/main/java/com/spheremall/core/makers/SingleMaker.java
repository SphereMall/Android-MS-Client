package com.spheremall.core.makers;

import com.spheremall.core.entities.Response;

import java.util.List;

public interface SingleMaker<T> {
    Response<T> makeSingle(String response);
}
