package com.yossisegev.producthuntclient.di.components;

import com.yossisegev.producthuntclient.di.PerActivity;
import com.yossisegev.producthuntclient.di.modules.FeedModule;
import com.yossisegev.producthuntclient.views.activities.FeedActivity;

import dagger.Subcomponent;

/**
 * Created by Yossi Segev on 11/08/2017.
 */

@PerActivity
@Subcomponent(modules = {FeedModule.class})

public interface FeedComponent {
    void inject(FeedActivity feedActivity);
}
