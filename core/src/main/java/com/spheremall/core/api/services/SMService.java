package com.spheremall.core.api.services;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface SMService {

    @GET("/{version}/{resource}")
    Call<ResponseBody> get(
            @Header("Authorization") String token,
            @Header("User-Agent") String userAgent,
            @Path("version") String version,
            @Path("resource") String resource,
            @QueryMap Map<String, String> options
    );

    @FormUrlEncoded
    @PUT("/{version}/{resource}")
    Call<ResponseBody> update(
            @Header("Authorization") String token,
            @Header("User-Agent") String userAgent,
            @Path("version") String version,
            @Path("resource") String resource,
            @FieldMap HashMap<String, String> params
    );

    @FormUrlEncoded
    @POST("/{version}/{resource}")
    Call<ResponseBody> create(
            @Header("Authorization") String token,
            @Header("User-Agent") String userAgent,
            @Path("version") String version,
            @Path("resource") String resource,
            @FieldMap HashMap<String, String> params
    );

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "/{version}/{resource}", hasBody = true)
    Call<ResponseBody> delete(
            @Header("Authorization") String token,
            @Header("User-Agent") String userAgent,
            @Path("version") String version,
            @Path("resource") String resource,
            @FieldMap(encoded = true) Map<String, String> params
    );
}
