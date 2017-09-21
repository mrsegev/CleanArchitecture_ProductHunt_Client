package com.yossisegev.data.authentication.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Yossi Segev on 28/07/2017.
 */

public class AuthResponse {

    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("token_type")
    private String tokenType;
    private String scope;

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getScope() {
        return scope;
    }
}
