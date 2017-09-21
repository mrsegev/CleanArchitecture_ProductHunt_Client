package com.yossisegev.domain.usecase;

/**
 * Created by Yossi Segev on 07/09/2017.
 */

import com.yossisegev.domain.TestDisposableObserver;
import com.yossisegev.domain.authentication.AuthDataSource;
import com.yossisegev.domain.authentication.AuthenticateUseCase;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthenticateUseCaseTests {

    private static final String TEST_TOKEN = "test_token";

    @Test
    public void testAuthenticationUseCaseReturnCorrectValue() {
        AuthDataSource mockAuthRepository = mock(AuthDataSource.class);
        when(mockAuthRepository.getAuthenticationToken()).thenReturn(Observable.just(TEST_TOKEN));
        AuthenticateUseCase authenticateUseCase = new AuthenticateUseCase(
                mockAuthRepository,
                Schedulers.trampoline(),
                Schedulers.trampoline());

        TestDisposableObserver<String> testDisposableObserver = new TestDisposableObserver<>();
        authenticateUseCase.execute(testDisposableObserver);
        assertEquals(TEST_TOKEN, testDisposableObserver.getValue());
    }
}
