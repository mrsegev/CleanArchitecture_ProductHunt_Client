package com.yossisegev.domain.usecase;

/**
 * Created by Yossi Segev on 09/09/2017.
 */

import com.yossisegev.domain.TestDisposableObserver;
import com.yossisegev.domain.feed.FeedDataSource;
import com.yossisegev.domain.feed.GetFeedUseCase;
import com.yossisegev.domain.feed.Post;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;
public class GetFeedUseCaseTests {

    private static final int MOCK_LIST_SIZE = 5;

    @Test
    public void testGetFeedUseCaseReturnCorrectValue() {
        FeedDataSource feedDataSource = mock(FeedDataSource.class);
        when(feedDataSource.getPosts()).thenReturn(Observable.just(getMockPostsList()));
        GetFeedUseCase getFeedUseCase = new GetFeedUseCase(
                feedDataSource,
                Schedulers.trampoline(),
                Schedulers.trampoline());
        TestDisposableObserver<List<Post>> testDisposableObserver = new TestDisposableObserver<>();
        getFeedUseCase.execute(testDisposableObserver);
        assertEquals(testDisposableObserver.getValue().size(), MOCK_LIST_SIZE);

    }

    private List<Post> getMockPostsList() {
        List<Post> posts = new ArrayList<>();
        for (int i = 0; i < MOCK_LIST_SIZE; i++) {
            posts.add(mock(Post.class));
        }
        return posts;
    }
}
