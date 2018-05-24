package com.example.nikit.eventsapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by harsimar on 20/05/18.
 */
@Data
@EqualsAndHashCode(callSuper = false)

public class EventList {

    @SerializedName("data")
    private List<Event> eventList;

}
