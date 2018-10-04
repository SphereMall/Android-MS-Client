package com.spheremall.core.api.configuration;

import com.spheremall.core.SMClient;
import com.spheremall.core.api.auth.BasicAuthInterceptor;
import com.spheremall.core.api.services.AuthService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.Route;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.spheremall.core.api.configuration.ApiConstants.CONNECT_TIMEOUT;
import static com.spheremall.core.api.configuration.ApiConstants.READ_TIMEOUT;
import static com.spheremall.core.api.configuration.ApiConstants.WRITE_TIMEOUT;

public class RetrofitApiConfigurationFactory implements ApiConfigurationFactory<Retrofit> {

    public final String endpoint;
    public final boolean debug;
    public final HttpLoggingInterceptor.Level level;
    public SMClient client = null;

    public RetrofitApiConfigurationFactory(SMClient client) {
        this.client = client;
        this.endpoint = client.getGatewayUrl();
        this.debug = client.isDebug();
        this.level = client.getLoggingLevel();
    }

    public RetrofitApiConfigurationFactory(String endpoint, boolean debug) {
        this.endpoint = endpoint;
        this.debug = debug;
        this.level = HttpLoggingInterceptor.Level.BASIC;
    }

    @Override
    public Retrofit createConfiguration() {
        return createComponent(createApiClient());
    }

    private Retrofit createComponent(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(endpoint)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    private OkHttpClient createApiClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (debug) {
            final HttpLoggingInterceptor httpLoggingInterceptor =
                    new HttpLoggingInterceptor()
                            .setLevel(level);
            builder.addInterceptor(httpLoggingInterceptor);
        }

        if (client != null && client.getBasicAuth() != null) {
            builder.addInterceptor(new BasicAuthInterceptor(client.getBasicAuth()));
        }

//        TODO: Test this way of authentication
//        if (client != null) {
//            builder.authenticator(new SMAuthenticator());
//        }

        return builder
                .addNetworkInterceptor(new RequestInterceptor())
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    private class SMAuthenticator implements Authenticator {

        @Override
        public Request authenticate(Route route, Response response) {
            if (responseCount(response) >= 2) {
                return null;
            }

            HashMap<String, String> authParams = new HashMap<>();
            authParams.put(ApiConstants.API_CLIENT_ID_TITLE, client.getClientId());
            authParams.put(ApiConstants.API_SECRET_TITLE, client.getSecretKey());
            AuthService authService = createComponent(createApiClient()).create(AuthService.class);
            try {
                retrofit2.Response<ResponseBody> tokenResponse = authService.getToken(ApiConstants.API_USER_AGENT_PREFIX + SMClient.userAgent,
                        client.getVersion(), "/oauth", authParams).execute();
                if (tokenResponse.code() == 200) {
                    JSONObject mainObject = new JSONObject(response.body().string());
                    JSONArray data = mainObject.getJSONArray("data");
                    JSONObject tokenObj = data.getJSONObject(0);
                    String token = tokenObj.getString("token");
                    client.getPreferencesManager().setToken(token);
                    return response.request().newBuilder()
                            .header("Authorization", "Bearer " + token)
                            .build();
                } else {
                    return null;
                }

            } catch (IOException | JSONException e) {
                return null;
            }
        }
    }

    private static int responseCount(Response response) {
        int result = 1;
        while ((response = response.priorResponse()) != null) {
            result++;
        }
        return result;
    }
}
