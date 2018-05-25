package org.fossasia.openevent.general.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by harsimar on 20/05/18.
 */
@Data
@EqualsAndHashCode(callSuper = false)

public class User {
    @SerializedName("data")
    private UserData user;
}