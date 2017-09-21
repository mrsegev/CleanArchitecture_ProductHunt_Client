package com.yossisegev.producthuntclient.common;

import java.lang.ref.WeakReference;

/**
 * Created by Yossi Segev on 09/09/2017.
 */

public abstract class Presenter<V extends MvpView> {

    private WeakReference<V> view;

    public void attach(V mvpView) {
        view = new WeakReference<>(mvpView);
        onAttach(view.get());
    }

    public void detach() {
        if (view.get() != null) {
            view.clear();
        }
        onDetach();
    }

    protected V getView() {
        return view.get();
    }

    public abstract void onAttach(V view);
    public abstract void onDetach();
}
