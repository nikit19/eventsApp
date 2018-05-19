package com.example.nikit.eventsapp.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@JsonNaming(PropertyNamingStrategy.KebabCaseStrategy.class)

public class Login {

    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
