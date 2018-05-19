package com.example.nikit.eventsapp.rest;

import com.example.nikit.eventsapp.model.Event;
import com.example.nikit.eventsapp.model.Login;
import com.example.nikit.eventsapp.model.LoginResponse;
import com.example.nikit.eventsapp.model.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @POST("../../users")
    Observable<User> signUp(@Body User user);

    @POST("../../../auth/session")
    Observable<LoginResponse> login(@Body Login login);

    @GET("../../users/{id}")
    Observable<User> getUser(@Path("id") long id);

    @PATCH("../../users/{id}")
    Observable<User> updateUser(@Body User user, @Path("id") long id);

    @GET("../../events")
    Call<List<Event>> getEvents();
}
