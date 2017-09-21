package com.yossisegev.data.authentication;


import com.yossisegev.data.authentication.api.ApiAuthDataSource;
import com.yossisegev.data.authentication.local.LocalAuthDataStore;
import com.yossisegev.domain.authentication.AuthDataSource;

import io.reactivex.Observable;

/**
 * Created by Yossi Segev on 24/08/2017.
 */

public class AuthRepository implements AuthDataSource {

    private LocalAuthDataStore localAuthDataStore;
    private ApiAuthDataSource apiAuthDataSource;

    public AuthRepository(LocalAuthDataStore localAuthDataStore, ApiAuthDataSource apiAuthDataSource) {
        this.localAuthDataStore = localAuthDataStore;
        this.apiAuthDataSource = apiAuthDataSource;
    }

    @Override
    public Observable<String> getAuthenticationToken() {
        if (localAuthDataStore.hasStoredToken()) {
            return localAuthDataStore.getAuthenticationToken();
        }
        return apiAuthDataSource
                .getAuthenticationToken()
                .flatMap((token) -> {
                    localAuthDataStore.storeAuthenticationToken(token);
                    return localAuthDataStore.getAuthenticationToken();
                });

    }
}
