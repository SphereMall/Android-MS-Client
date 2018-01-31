package com.spheremall.core.jsonapi.retrofit;

import com.spheremall.core.jsonapi.JSONAPIDocument;
import com.spheremall.core.jsonapi.errors.Errors;
import com.spheremall.core.jsonapi.models.User;
import com.spheremall.core.jsonapi.models.inheritance.Entity;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Simple Retrofit service interface used for unit-tests.
 *
 * @author jbegic
 */
public interface SimpleService {

    @GET("user")
    Call<User> getExampleResource();

    @GET("user")
    Call<ResponseBody> getExampleResourceAsResource();

    @GET("users")
    Call<List<User>> getExampleResourceList();

    @GET("products")
    Call<ResponseBody> getGridResourceList();

    @GET("products")
    Call<List<Entity>> getGridProductsList();

    @GET("notanjsonapiendpoint")
    Call<Errors> getNonJSONSPECResource();

    @POST("user")
    Call<User> createUser(@Body User user);

    @POST("users")
    Call<JSONAPIDocument<List<User>>> createMultipleUsers(@Body JSONAPIDocument<List<User>> users);

    @GET("documents")
    Call<ResponseBody> getDocuments();
}
