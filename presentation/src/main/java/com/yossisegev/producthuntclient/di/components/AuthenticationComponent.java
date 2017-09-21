package com.yossisegev.producthuntclient.di.components;

import com.yossisegev.producthuntclient.di.PerActivity;
import com.yossisegev.producthuntclient.di.modules.AuthenticationModule;
import com.yossisegev.producthuntclient.views.activities.SplashActivity;

import dagger.Subcomponent;

/**
 * Created by Yossi Segev on 11/08/2017.
 */

@PerActivity
@Subcomponent(modules = {AuthenticationModule.class})

public interface AuthenticationComponent {
    void inject(SplashActivity splashActivity);
}
