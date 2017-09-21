package com.yossisegev.producthuntclient.di.modules;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.yossisegev.data.authentication.local.AuthPrefsStorage;
import com.yossisegev.data.feed.PostsMemStorage;
import com.yossisegev.domain.authentication.AuthStorage;
import com.yossisegev.domain.feed.PostsStorage;
import com.yossisegev.producthuntclient.GlideImageLoader;
import com.yossisegev.producthuntclient.ImageLoader;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Yossi Segev on 08/09/2017.
 */

@Module
public class ApplicationModule {

    private Application application;
    public static final String SCHEDULER_SUBSCRIBE_ON = "subscribe_on";
    public static final String SCHEDULER_OBSERVE_ON = "observe_on";

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application application() {
        return application;
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    public AuthStorage provideAuthStorage(SharedPreferences sharedPreferences) {
        return new AuthPrefsStorage(sharedPreferences);
    }

    @Provides
    @Singleton
    public PostsStorage postsStorage() {
        return new PostsMemStorage();
    }

    @Provides
    @Named(SCHEDULER_SUBSCRIBE_ON)
    public Scheduler provideSubscribeOnScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Named(SCHEDULER_OBSERVE_ON)
    public Scheduler provideObserveOnScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    public ImageLoader imageLoader(Application application) {
        return new GlideImageLoader(application);
    }

}
