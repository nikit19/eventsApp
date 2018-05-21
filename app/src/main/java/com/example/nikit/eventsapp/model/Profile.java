package com.example.nikit.eventsapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nikit on 11/5/18.
 */

public class Profile {
    @SerializedName("email")
    private String email;
    @SerializedName("first-name")
    private String firstName;
    @SerializedName("last-name")
    private String lastName;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
