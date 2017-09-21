package com.yossisegev.domain;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Yossi Segev on 07/09/2017.
 */

public class TestDisposableObserver<T> extends DisposableObserver<T> {

    private T value;
    private Throwable error;
    private boolean completed;

    @Override
    public void onNext(@NonNull T t) {
        value = t;
    }

    @Override
    public void onError(@NonNull Throwable e) {
        error = e;
    }

    @Override
    public void onComplete() {
        completed = true;
    }

    public T getValue() {
        return value;
    }

    public Throwable getError() {
        return error;
    }

    public boolean isCompleted() {
        return completed;
    }
}

