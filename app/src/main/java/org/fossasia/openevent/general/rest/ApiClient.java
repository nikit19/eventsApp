package org.fossasia.openevent.general.rest;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jasminb.jsonapi.retrofit.JSONAPIConverterFactory;

import org.fossasia.openevent.general.LoginActivity;
import org.fossasia.openevent.general.model.User;

import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.OkHttpClient;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ApiClient {

    private static final int CONNECT_TIMEOUT_SECONDS = 15; // 15s

    private static final int READ_TIMEOUT_SECONDS = 15; // 15s

    public static final String BASE_URL = "https://open-event-api-dev.herokuapp.com/";

    private static Retrofit retrofit = null;
    private static ApiInterface apiInterface;
    private static OkHttpClient.Builder okHttpClientBuilder;
    private static String TOKEN2 = null;
    private static Authenticator authenticator =null;

    static {
        okHttpClientBuilder = new OkHttpClient().newBuilder()
                .connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS);

    }

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ApiInterface getClient2() {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        OkHttpClient okHttpClient = okHttpClientBuilder.addInterceptor(new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BASIC))
                .authenticator(getAuthenticator())
                .build();

        apiInterface = new Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(new JSONAPIConverterFactory(objectMapper, User.class))
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .baseUrl(BASE_URL)
                .build()
                .create(ApiInterface.class);

        return apiInterface;

    }

    private static Authenticator getAuthenticator() {
        TOKEN2 = LoginActivity.TOKEN;
        if (authenticator == null) {
            authenticator = (route, response) ->
                    response.request().newBuilder()
                    .header("Authorization", TOKEN2)
                    .build();
        }
        return authenticator;
    }

}