package com.example.nikit.eventsapp.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class LoginResponse {
    @SerializedName("access_token")
    private String accessToken;

}
