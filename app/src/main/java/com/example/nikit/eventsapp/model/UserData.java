package com.example.nikit.eventsapp.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by harsimar on 20/05/18.
 */
@Data
@EqualsAndHashCode(callSuper = false)

public class UserData {
    @SerializedName("attributes")
    private AttributesUser attributes;
    @SerializedName("id")
    private String id;
    @SerializedName("type")
    private String type;

}