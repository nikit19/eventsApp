package com.example.nikit.eventsapp.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by harsimar on 20/05/18.
 */
@Data
@EqualsAndHashCode(callSuper = false)

public class Event {
    @SerializedName("attributes")
    private Attributes attributes;
    @SerializedName("id")
    private String id;
    @SerializedName("type")
    private String type;

}