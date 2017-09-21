package com.yossisegev.data.authentication.api;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Yossi Segev on 28/07/2017.
 */

public interface AuthenticationService {

    @POST("oauth/token")
    Observable<AuthResponse> authenticate(@Body AuthRequestData authRequestData);

}
