package com.spheremall.core.makers;

import com.google.gson.Gson;
import com.spheremall.core.entities.Response;
import com.spheremall.core.entities.shop.OrderHistoryJson;

import java.util.HashMap;

public class OrderHistoryMaker extends ObjectMaker<OrderHistoryJson> {

    public OrderHistoryMaker(Class<OrderHistoryJson> clazz) {
        super(clazz);
    }

    @Override
    public Response<OrderHistoryJson> makeSingle(String response) {
        Gson gson = new Gson();
        return new Response<>(gson.fromJson(response, OrderHistoryJson.class), new HashMap<>());
    }
}
