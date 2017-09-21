package com.yossisegev.data.authentication.local;


import com.yossisegev.domain.authentication.AuthDataSource;
import com.yossisegev.domain.authentication.AuthStorage;

import io.reactivex.Observable;

/**
 * Created by Yossi Segev on 24/08/2017.
 */

public class LocalAuthDataStore implements AuthDataSource {

    private AuthStorage authStorage;

    public LocalAuthDataStore(AuthStorage authStorage) {
        this.authStorage = authStorage;
    }

    @Override
    public Observable<String> getAuthenticationToken() {
        return Observable.just(authStorage.getToken());
    }

    public boolean hasStoredToken() {
        return authStorage.getToken() != null;
    }

    public void storeAuthenticationToken(String token) {
        authStorage.saveToken(token);
    }
}
