package org.fossasia.openevent.general.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by nikit on 11/5/18.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LoginResponse {
    @SerializedName("access_token")
    private String accessToken;
    public String getAccessToken() {
        return accessToken;
    }
}
