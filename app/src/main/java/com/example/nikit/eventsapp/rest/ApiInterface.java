package com.example.nikit.eventsapp.rest;

import com.example.nikit.eventsapp.model.Event;
import com.example.nikit.eventsapp.model.Login;
import com.example.nikit.eventsapp.model.LoginResponse;
import com.example.nikit.eventsapp.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

   // @Headers("Content-Type: application/json")
    @POST("auth/session")
    Call<LoginResponse> login(@Body Login login);

    @GET("v1/events")
    Call<Event> getEvent();
}
