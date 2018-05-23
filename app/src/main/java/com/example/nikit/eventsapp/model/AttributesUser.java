package com.example.nikit.eventsapp.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by harsimar on 20/05/18.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AttributesUser {

    @SerializedName("last-name")
    private String lastName;
    @SerializedName("first-name")
    private String firstName;
    @SerializedName("email")
    private String email;
    @SerializedName("contact")
    private String contact;
    @SerializedName("avatar-url")
    private String avatarUrl;

    public String getLastName() {
        return lastName;
    }

}
