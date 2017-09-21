package com.yossisegev.data.authentication;

import android.support.annotation.NonNull;

import com.yossisegev.domain.authentication.AuthStorage;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Yossi Segev on 09/09/2017.
 */

public class AddHeadersInterceptor implements Interceptor {

    private AuthStorage authStorage;

    public AddHeadersInterceptor(AuthStorage authStorage) {
        this.authStorage = authStorage;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        Request request = chain.request();
        Request.Builder newRequestBuilder = request.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Host", "api.producthunt.com");

        if (authStorage.getToken() != null) {
            newRequestBuilder.addHeader("Authorization", "Bearer " + authStorage.getToken());
        }
        return chain.proceed(newRequestBuilder.build());
    }
}
