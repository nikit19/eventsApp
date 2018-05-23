package com.example.nikit.eventsapp.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by nikit on 11/5/18.
 */
@Data
@EqualsAndHashCode(callSuper = false)

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
