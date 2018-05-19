package com.example.nikit.eventsapp.rest;

import com.example.nikit.eventsapp.BuildConfig;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class ApiClient {

    private static final int CONNECT_TIMEOUT_SECONDS = 15; // 15s

    private static final int READ_TIMEOUT_SECONDS = 15; // 15s
    private static final String CACHE_CONTROL = "Cache-Control";


    public static final String BASE_URL = "https://open-event-api.herokuapp.com/"; 
    private static Retrofit retrofit = null;
    private static OkHttpClient.Builder okHttpClientBuilder;

    static {
        okHttpClientBuilder = new OkHttpClient().newBuilder()
                .connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG)
            okHttpClientBuilder.addNetworkInterceptor(new StethoInterceptor());

    }


    public static Retrofit getClient() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader("Content-Type", "application/json").build();
                return chain.proceed(request);
            }
        });

        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .client(httpClient.build())
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
