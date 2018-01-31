package com.spheremall.core.makers;

import com.google.gson.Gson;
import com.spheremall.core.entities.Response;
import com.spheremall.core.entities.users.Token;

import java.util.HashMap;

public class AuthMaker extends ObjectMaker<Token> {
    public AuthMaker(Class<Token> clazz) {
        super(clazz);
    }

    @Override
    public Response<Token> makeSingle(String response) {
        Gson gson = new Gson();
        return new Response<>(gson.fromJson(response, Token.class), new HashMap<>());
    }
}
