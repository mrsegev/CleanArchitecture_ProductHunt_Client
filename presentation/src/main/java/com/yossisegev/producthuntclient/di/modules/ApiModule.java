package com.yossisegev.producthuntclient.di.modules;

import com.yossisegev.data.authentication.AddHeadersInterceptor;
import com.yossisegev.domain.authentication.AuthStorage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Yossi Segev on 08/09/2017.
 */

@Module
public class ApiModule {

    private final String baseUrl;

    public ApiModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    public Interceptor requestInterceptor(AuthStorage authStorage) {
        return new AddHeadersInterceptor(authStorage);
    }
    @Provides
    @Singleton
    public OkHttpClient okHttpClient(Interceptor interceptor) {
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
