package com.yossisegev.domain;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UseCaseTests {

    private TestDisposableObserver<String> testDisposableObserver;
    private TestUseCase testUseCase;

    @Before
    public void setup() throws Exception {
        testDisposableObserver = new TestDisposableObserver<>();
        testUseCase = new TestUseCase(Schedulers.trampoline(), Schedulers.trampoline());
    }

    @Test
    public void testUseCaseReturnCorrectValue() {
        testUseCase.execute(testDisposableObserver);
        assertEquals(testDisposableObserver.getValue(), TestUseCase.OUTPUT);
    }

    @Test
    public void testDispose() {
        testUseCase.execute(testDisposableObserver);
        testUseCase.cancel();
        assertTrue(testDisposableObserver.isDisposed());
    }
}