package com.example.nikit.eventsapp.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by harsimar on 20/05/18.
 */
@Data
@EqualsAndHashCode(callSuper = false)

public class Attributes {
    @SerializedName("identifier")
    private String identifier;
    @SerializedName("name")
    private String name;
    @SerializedName("organizer-description")
    private String organizerDescription;
    @SerializedName("starts-at")
    private String startsAt;
    @SerializedName("original-image-url")
    private String originalImageUrl;
    @SerializedName("description")
    private String desciption;

}
