package com.yossisegev.producthuntclient.views;

import com.yossisegev.producthuntclient.common.MvpView;

/**
 * Created by Yossi Segev on 09/09/2017.
 */

public interface AuthenticationView extends MvpView {
    void onAuthenticationSuccess();
    void onError();
}
