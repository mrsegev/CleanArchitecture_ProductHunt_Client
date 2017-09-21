package com.yossisegev.domain.authentication;

import io.reactivex.Observable;

/**
 * Created by Yossi Segev on 07/09/2017.
 */

public interface AuthDataSource {
    Observable<String> getAuthenticationToken();
}
