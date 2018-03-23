package com.spheremall.core.makers;

import com.google.gson.Gson;
import com.spheremall.core.entities.Response;
import com.spheremall.core.entities.pojo.UserId;

import java.util.HashMap;

public class UserIdMaker extends ObjectMaker<UserId> {
    public UserIdMaker(Class<UserId> clazz) {
        super(clazz);
    }

    @Override
    public Response<UserId> makeSingle(String response) {
        Gson gson = new Gson();
        return new Response<>(gson.fromJson(response, UserId.class), new HashMap<>());
    }
}
