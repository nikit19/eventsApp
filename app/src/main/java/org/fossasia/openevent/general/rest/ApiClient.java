package org.fossasia.openevent.general.rest;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jasminb.jsonapi.retrofit.JSONAPIConverterFactory;

import org.fossasia.openevent.general.model.User;
import org.fossasia.openevent.general.utils.AuthUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class ApiClient {
    private static final int CONNECT_TIMEOUT_SECONDS = 15; // 15s

    private static final int READ_TIMEOUT_SECONDS = 15; // 15s

    private static ApiInterface apiInterface;
    public static final String BASE_URL = "https://open-event-api-dev.herokuapp.com/";

    private static ObjectMapper objectMapper;
    private static OkHttpClient.Builder okHttpClientBuilder;
    
    static {

        okHttpClientBuilder = new OkHttpClient().newBuilder()
                .connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS);

    }

    public static ObjectMapper getObjectMapper(){
        if (objectMapper == null){
            objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }
        return objectMapper;
    }

    public static ApiInterface getOpenEventAPI() {
        if (apiInterface == null) {
            OkHttpClient okHttpClient = okHttpClientBuilder.addInterceptor(new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BASIC))
                    .authenticator(AuthUtil.getAuthenticator())
                    .build();

            ObjectMapper objectMapper = getObjectMapper();

            Class[] classes = { User.class};

            apiInterface = new Retrofit.Builder()
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(new JSONAPIConverterFactory(objectMapper, classes))
                    .addConverterFactory(JacksonConverterFactory.create(getObjectMapper()))
                    .baseUrl(BASE_URL)
                    .build()
                    .create(ApiInterface.class);
        }

        return apiInterface;
    }


}
