package com.yossisegev.producthuntclient.presenters;

import com.yossisegev.domain.authentication.AuthenticateUseCase;
import com.yossisegev.producthuntclient.common.Presenter;
import com.yossisegev.producthuntclient.views.AuthenticationView;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Yossi Segev on 08/09/2017.
 */

public class AuthenticationPresenter extends Presenter<AuthenticationView> {

    private AuthenticateUseCase authenticateUseCase;

    @Inject
    public AuthenticationPresenter(AuthenticateUseCase authenticateUseCase) {
        this.authenticateUseCase = authenticateUseCase;
    }

    private void fetchAuthenticationToken() {

        authenticateUseCase.execute(new DisposableObserver<String>() {
            @Override
            public void onNext(@NonNull String s) {
                getView().onAuthenticationSuccess();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                getView().onError();
            }

            @Override
            public void onComplete() {}
        });
    }


    @Override
    public void onAttach(AuthenticationView view) {
        fetchAuthenticationToken();
    }

    @Override
    public void onDetach() {
        authenticateUseCase.cancel();
    }
}
