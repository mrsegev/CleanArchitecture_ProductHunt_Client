package com.yossisegev.data.authentication.api;

import com.yossisegev.domain.authentication.AuthDataSource;

import io.reactivex.Observable;

/**
 * Created by Yossi Segev on 24/08/2017.
 */

public class ApiAuthDataSource implements AuthDataSource {

    private AuthenticationService authenticationService;
    private AuthRequestData authRequestData;


    public ApiAuthDataSource(AuthenticationService authenticationService, AuthRequestData authRequestData) {
        this.authenticationService = authenticationService;
        this.authRequestData = authRequestData;
    }

    @Override
    public Observable<String> getAuthenticationToken() {
        return authenticationService.authenticate(authRequestData).flatMap(
                authResponse ->
                        Observable.just(authResponse.getAccessToken()));
    }
}
