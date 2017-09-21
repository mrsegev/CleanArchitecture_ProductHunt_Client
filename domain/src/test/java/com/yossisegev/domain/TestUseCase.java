package com.yossisegev.domain;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Yossi Segev on 05/09/2017.
 */

public class TestUseCase extends UseCase<String> {

    public static final String OUTPUT = "test_output";

    public TestUseCase(Scheduler subscribeOn, Scheduler observeOn) {
        super(subscribeOn, observeOn);
    }

    @Override
    public Observable<String> createObservable() {
        return Observable.just(OUTPUT);
    }
}
