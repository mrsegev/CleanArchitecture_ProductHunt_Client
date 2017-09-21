package com.yossisegev.domain.authentication;

import com.yossisegev.domain.UseCase;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Created by Yossi Segev on 07/09/2017.
 */

public class AuthenticateUseCase extends UseCase<String> {

    private AuthDataSource authRepository;

    public AuthenticateUseCase(AuthDataSource authRepository, Scheduler subscribeOn, Scheduler observeOn) {
        super(subscribeOn, observeOn);
        this.authRepository = authRepository;
    }

    @Override
    public Observable<String> createObservable() {
        return authRepository.getAuthenticationToken();
    }
}
