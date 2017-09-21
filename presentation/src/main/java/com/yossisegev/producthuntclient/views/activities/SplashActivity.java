package com.yossisegev.producthuntclient.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yossisegev.producthuntclient.R;
import com.yossisegev.producthuntclient.common.App;
import com.yossisegev.producthuntclient.di.modules.AuthenticationModule;
import com.yossisegev.producthuntclient.presenters.AuthenticationPresenter;
import com.yossisegev.producthuntclient.views.AuthenticationView;

import javax.inject.Inject;

public class SplashActivity extends AppCompatActivity implements AuthenticationView {

    @Inject
    AuthenticationPresenter authenticationPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((App) getApplication()).getApplicationComponent().plus(new AuthenticationModule()).inject(this);


        authenticationPresenter.attach(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        authenticationPresenter.detach();
    }

    @Override
    public void onAuthenticationSuccess() {

        finish();
        startActivity(new Intent(this, FeedActivity.class));
    }

    @Override
    public void onError() {

    }
}
