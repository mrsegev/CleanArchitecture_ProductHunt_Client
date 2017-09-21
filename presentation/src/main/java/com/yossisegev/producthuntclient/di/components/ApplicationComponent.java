package com.yossisegev.producthuntclient.di.components;

import com.yossisegev.producthuntclient.di.modules.ApiModule;
import com.yossisegev.producthuntclient.di.modules.ApplicationModule;
import com.yossisegev.producthuntclient.di.modules.AuthenticationModule;
import com.yossisegev.producthuntclient.di.modules.FeedModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Yossi Segev on 08/09/2017.
 */

@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class})
public interface ApplicationComponent {
    AuthenticationComponent plus(AuthenticationModule authenticationModule);
    FeedComponent plus(FeedModule feedModule);
}
