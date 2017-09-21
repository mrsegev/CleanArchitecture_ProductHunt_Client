package com.yossisegev.producthuntclient.common;

import android.app.Application;

import com.yossisegev.producthuntclient.di.components.ApplicationComponent;

import com.yossisegev.producthuntclient.di.components.DaggerApplicationComponent;
import com.yossisegev.producthuntclient.di.modules.ApiModule;
import com.yossisegev.producthuntclient.di.modules.ApplicationModule;

/**
 * Created by Yossi Segev on 08/09/2017.
 */

public class App extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .apiModule(new ApiModule("https://api.producthunt.com/v1/"))
                .build();


    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }


}
