package com.spheremall.core.jsonapi.retrofit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.spheremall.core.entities.products.AttributeValue;
import com.spheremall.core.entities.products.Brand;
import com.spheremall.core.entities.products.FunctionalName;
import com.spheremall.core.entities.products.Product;
import com.spheremall.core.jsonapi.ErrorUtils;
import com.spheremall.core.jsonapi.IOUtils;
import com.spheremall.core.jsonapi.JSONAPIDocument;
import com.spheremall.core.jsonapi.ResourceConverter;
import com.spheremall.core.jsonapi.errors.Error;
import com.spheremall.core.jsonapi.errors.Errors;
import com.spheremall.core.jsonapi.models.Document;
import com.spheremall.core.jsonapi.models.User;
import com.spheremall.core.jsonapi.models.inheritance.Entity;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Retrofit plugin tests.
 *
 * @author jbegic
 */
public class RetrofitTest {
    private ResourceConverter converter;
    private MockWebServer server;
    private SimpleService service;

    @Before
    public void setup() throws IOException {
        // Setup server
        server = new MockWebServer();
        server.start();

        // Setup retrofit
        converter = new ResourceConverter(
                User.class,
                Product.class,
                Document.class,
                Entity.class,
                Brand.class,
                FunctionalName.class,
                AttributeValue.class
        );

        JSONAPIConverterFactory converterFactory = new JSONAPIConverterFactory(converter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(server.url("/").toString())
                .addConverterFactory(converterFactory)
                .build();

        service = retrofit.create(SimpleService.class);
    }

    @After
    public void destroy() throws IOException {
        server.shutdown();
    }

    @Test
    public void getGridCollectionTest() throws IOException {
        String gridResponse = IOUtils.getResourceAsString("products.json");

        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(gridResponse));

        Response<ResponseBody> response = service.getGridResourceList().execute();
        String responseString = response.body().string();

        List<Product> product = converter
                .readDocumentCollection(responseString.getBytes(), Product.class).get();


        Assert.assertNotNull(response);
        Assert.assertNotNull(response.body());
        Assert.assertTrue(response.isSuccessful());
        Assert.assertNotNull(product);
    }

    @Test
    public void testGettingIgnoredParams() throws IOException {
        String documentsBody = IOUtils.getResourceAsString("documents.json");
        server.enqueue(new MockResponse().setResponseCode(200).setBody(documentsBody));

        Response<ResponseBody> bodyResource = service.getDocuments().execute();
        String responseString = bodyResource.body().string();
        List<Document> documents = converter.readDocumentCollection(responseString.getBytes(), Document.class).get();
        Assert.assertNotNull(documents);
        Assert.assertEquals(1, documents.size());
        Assert.assertEquals("title", documents.get(0).getTitle());


        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("title", "data");

        Assert.assertEquals("data", stringStringHashMap.get("title"));
        Assert.assertNull(stringStringHashMap.get("key"));
    }

    @Test
    public void getResourceTest() throws IOException {
        String userResponse = IOUtils.getResourceAsString("user-liz.json");

        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(userResponse));

        Response<ResponseBody> response = service.getExampleResourceAsResource().execute();

        Assert.assertTrue(response.isSuccessful());

        User user = converter.readDocument(response.body().byteStream(), User.class).get();

        Assert.assertNotNull(user);
        Assert.assertEquals("liz", user.getName());
    }

    @Test
    public void getProductTest() throws IOException {
        String userResponse = IOUtils.getResourceAsString("product_detail.json");

        Gson gson = new Gson();
        Product product = gson.fromJson(userResponse, Product.class);

        Assert.assertNotNull(product);
    }

    @Test
    public void getResourceCollectionTest() throws IOException {
        String usersResponse = IOUtils.getResourceAsString("users.json");

        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(usersResponse));

        Response<List<User>> response = service.getExampleResourceList().execute();

        Assert.assertTrue(response.isSuccessful());

        List<User> users = response.body();
        Assert.assertEquals(2, users.size());
    }

    @Test
    public void testError() throws IOException {
        String errorString = IOUtils.getResourceAsString("errors.json");

        server.enqueue(new MockResponse()
                .setResponseCode(400)
                .setBody(errorString));

        Response<User> response = service.getExampleResource().execute();

        Assert.assertFalse(response.isSuccessful());

        Errors errorResponse = ErrorUtils.parseErrorResponse(new ObjectMapper(), response.errorBody(), Errors.class);

        Assert.assertNotNull(errorResponse);
        Assert.assertEquals(1, errorResponse.getErrors().size());

        Error error = errorResponse.getErrors().get(0);

        Assert.assertEquals("id", error.getId());
        Assert.assertEquals("status", error.getStatus());
        Assert.assertEquals("code", error.getCode());
        Assert.assertEquals("title", error.getTitle());
        Assert.assertEquals("about", error.getLinks().getAbout());
        Assert.assertEquals("title", error.getTitle());
        Assert.assertEquals("pointer", error.getSource().getPointer());
        Assert.assertEquals("detail", error.getDetail());

        // Shutdown server
        server.shutdown();
    }

    @Test(expected = IllegalArgumentException.class)
    public void getUnregisteredResourceTest() throws IOException {
        String userResponse = IOUtils.getResourceAsString("errors.json");

        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(userResponse));

        service.getNonJSONSPECResource().execute();
    }

    @Test
    public void testRequestParsing() throws Exception {
        String userResponse = IOUtils.getResourceAsString("user-liz.json");

        server.enqueue(new MockResponse()
                .setResponseCode(201)
                .setBody(userResponse));

        User user = new User();
        user.setId("id");
        user.setName("name");

        User response = service.createUser(user).execute().body();

        Assert.assertEquals("liz", response.getName());

        RecordedRequest request = server.takeRequest();

        String requestBody = new String(request.getBody().readByteArray());

        Assert.assertEquals(new String(converter.writeObject(user)), requestBody);
    }

    @Test
    public void testPostWithCollection() throws Exception {
        String usersResponse = IOUtils.getResourceAsString("users.json");

        server.enqueue(new MockResponse()
                .setResponseCode(201)
                .setBody(usersResponse));


        List<User> users = new ArrayList<>();

        User user = new User();
        user.setId("id");
        user.setName("John");
        users.add(user);

        List<User> result = service.createMultipleUsers(new JSONAPIDocument<>(users)).execute().body().get();
        RecordedRequest request = server.takeRequest();
        String requestBody = new String(request.getBody().readByteArray());


        Assert.assertEquals(2, result.size());

        JSONAPIDocument<List<User>> serverSide = converter.readDocumentCollection(requestBody.getBytes(), User.class);

        Assert.assertEquals(1, serverSide.get().size());
        Assert.assertEquals(user.getId(), serverSide.get().iterator().next().getId());
        Assert.assertEquals(user.getName(), serverSide.get().iterator().next().getName());
    }
}
