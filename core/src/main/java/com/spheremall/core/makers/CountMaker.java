package com.spheremall.core.makers;

import com.google.gson.Gson;
import com.spheremall.core.entities.pojo.Count;
import com.spheremall.core.entities.Response;

import java.util.HashMap;

public class CountMaker extends ObjectMaker<Count> {

    public CountMaker(Class<Count> clazz) {
        super(clazz);
    }

    @Override
    public Response<Count> makeSingle(String response) {
        Gson gson = new Gson();
        return new Response<>(gson.fromJson(response, Count.class), new HashMap<>());
    }
}
