package com.yossisegev.data;

import com.yossisegev.data.authentication.api.ApiAuthDataSource;
import com.yossisegev.data.authentication.AuthRepository;
import com.yossisegev.data.authentication.local.LocalAuthDataStore;
import com.yossisegev.domain.authentication.AuthStorage;


import org.junit.Before;
import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class AuthenticationTests {

    private static final String LOCAL_TOKEN = "local_token";
    private static final String API_TOKEN = "api_token";
    private TestObserver<String> testObserver;
    private ApiAuthDataSource mockApiDataSource;
    private LocalAuthDataStore mockLocalAuthDataStore;
    @Before
    public void setup() {
        testObserver = new TestObserver<>();
        mockApiDataSource = mock(ApiAuthDataSource.class);
        mockLocalAuthDataStore = mock(LocalAuthDataStore.class);
    }

    @Test
    public void testLocalDataSourceReturnCorrectValue() {
        AuthStorage mockAuthStorage = mock(AuthStorage.class);
        when(mockAuthStorage.getToken()).thenReturn(LOCAL_TOKEN);
        LocalAuthDataStore localAuthDataStore = new LocalAuthDataStore(mockAuthStorage);
        assertTrue(localAuthDataStore.hasStoredToken());
        localAuthDataStore.getAuthenticationToken().subscribeWith(testObserver);
        testObserver.assertResult(LOCAL_TOKEN);
    }

    @Test
    public void testLocalDataSourceReturnEmpty() {
        AuthStorage mockAuthStorage = mock(AuthStorage.class);
        when(mockAuthStorage.getToken()).thenReturn(null);
        LocalAuthDataStore localAuthDataStore = new LocalAuthDataStore(mockAuthStorage);
        assertFalse(localAuthDataStore.hasStoredToken());
    }

    @Test
    public void testReturnsApiTokenWhenNothingStored() {

        when(mockApiDataSource.getAuthenticationToken()).thenReturn(Observable.just(API_TOKEN));

        when(mockLocalAuthDataStore.hasStoredToken()).thenReturn(false);

        AuthRepository authRepository = new AuthRepository(mockLocalAuthDataStore, mockApiDataSource);

        authRepository.getAuthenticationToken().subscribeWith(testObserver);

        verify(mockApiDataSource).getAuthenticationToken();
        verify(mockLocalAuthDataStore).hasStoredToken();
        testObserver.assertResult(API_TOKEN);
    }

    @Test
    public void testReturnsLocalTokenWhenStored() {
        // Api
        when(mockApiDataSource.getAuthenticationToken()).thenReturn(Observable.just(API_TOKEN));

        // Local
        when(mockLocalAuthDataStore.hasStoredToken()).thenReturn(true);
        when(mockLocalAuthDataStore.getAuthenticationToken()).thenReturn(Observable.just(LOCAL_TOKEN));

        AuthRepository authRepository = new AuthRepository(mockLocalAuthDataStore, mockApiDataSource);

        authRepository.getAuthenticationToken().subscribeWith(testObserver);
        verifyZeroInteractions(mockApiDataSource);
        testObserver.assertResult(LOCAL_TOKEN);
    }


}