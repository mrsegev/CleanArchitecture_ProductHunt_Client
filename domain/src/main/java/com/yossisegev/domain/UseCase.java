package com.yossisegev.domain;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Yossi Segev on 29/08/2017.
 */

public abstract class UseCase<T> {

    private CompositeDisposable compositeDisposable;
    private Scheduler subscribeOn;
    private Scheduler observeOn;

    public UseCase(Scheduler subscribeOn, Scheduler observeOn) {
        this.subscribeOn = subscribeOn;
        this.observeOn = observeOn;
        compositeDisposable = new CompositeDisposable();
    }

    public abstract Observable<T> createObservable();


    public void execute(DisposableObserver<T> observer) {

        Observable observable = createObservable()
                .subscribeOn(subscribeOn)
                .observeOn(observeOn);

        compositeDisposable.add((Disposable) observable.subscribeWith(observer));
    }

    public void cancel() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }
}
