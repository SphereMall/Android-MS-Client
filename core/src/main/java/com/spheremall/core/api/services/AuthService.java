package com.spheremall.core.api.services;


import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AuthService {

    @FormUrlEncoded
    @POST("/{version}/{resource}")
    Call<ResponseBody> getToken(
            @Header("User-Agent") String userAgent,
            @Path("version") String version,
            @Path("resource") String resource,
            @FieldMap HashMap<String, String> params
    );

}
