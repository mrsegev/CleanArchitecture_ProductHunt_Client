package com.yossisegev.data.authentication.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Yossi Segev on 28/07/2017.
 */

public class AuthRequestData {

    @SerializedName("client_id")
    private String clientId;
    @SerializedName("client_secret")
    private String clientSecret;
    @SerializedName("grant_type")
    private String grantType = "client_credentials";

    public AuthRequestData(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }
}
