package com.yossisegev.producthuntclient.di.modules;

import com.yossisegev.data.authentication.AuthRepository;
import com.yossisegev.data.authentication.api.ApiAuthDataSource;
import com.yossisegev.data.authentication.api.AuthRequestData;
import com.yossisegev.data.authentication.api.AuthenticationService;
import com.yossisegev.data.authentication.local.LocalAuthDataStore;
import com.yossisegev.domain.authentication.AuthDataSource;
import com.yossisegev.domain.authentication.AuthStorage;
import com.yossisegev.domain.authentication.AuthenticateUseCase;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import retrofit2.Retrofit;

/**
 * Created by Yossi Segev on 08/09/2017.
 */

@Module
public class AuthenticationModule {

    @Provides
    public LocalAuthDataStore provideLocalAuthDataStore(AuthStorage authStorage) {
        return new LocalAuthDataStore(authStorage);
    }


    @Provides
    public AuthRequestData provideAuthRequestData() {
        return new AuthRequestData(
                "1e00b0636e8324d403893ec437b98ac98dc002170be194c85b54c5dd2e4e2782",
                "2075e212abebdb1f60fd14a1e42025402ba44f018f5925051e8712ba77eca10b"
        );
    }

    @Provides
    public AuthenticationService authenticationService(Retrofit retrofit) {
        return retrofit.create(AuthenticationService.class);
    }

    @Provides
    public ApiAuthDataSource provideApiAuthDataStore(AuthenticationService authenticationService,
                                                     AuthRequestData authRequestData) {
        return new ApiAuthDataSource(authenticationService, authRequestData);
    }

    @Provides
    public AuthDataSource provideAuthRepository(LocalAuthDataStore localAuthDataStore,
                                                ApiAuthDataSource apiAuthDataSource) {
        return new AuthRepository(localAuthDataStore, apiAuthDataSource);
    }

    @Provides
    public AuthenticateUseCase provideAuthenticationUseCase(AuthDataSource authDataSource,
                                                            @Named(ApplicationModule.SCHEDULER_SUBSCRIBE_ON) Scheduler subscribeOn,
                                                            @Named(ApplicationModule.SCHEDULER_OBSERVE_ON) Scheduler observeOn) {
        return new AuthenticateUseCase(authDataSource, subscribeOn, observeOn);
    }
}
