package com.yossisegev.data;

/**
 * Created by Yossi Segev on 20/09/2017.
 */

import com.yossisegev.data.feed.FeedRepository;
import com.yossisegev.data.feed.PostsMemStorage;
import com.yossisegev.domain.feed.FeedDataSource;
import com.yossisegev.domain.feed.Post;
import com.yossisegev.domain.feed.PostsStorage;

import org.junit.Test;
import org.junit.Assert.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FeedTests {

    @Test
    public void testFeedRepositoryReturnsApiData() {
        FeedDataSource feedDataSource = mock(FeedDataSource.class);
        PostsStorage postsStorage = mock(PostsStorage.class);
        FeedRepository feedRepository = new FeedRepository(feedDataSource, postsStorage);

        List<Post> mockPostsList = generateMockPostsList();
        when(postsStorage.getPosts()).thenReturn(Observable.just(new ArrayList<>()));
        when(feedDataSource.getPosts()).thenReturn(Observable.just(mockPostsList));

        TestObserver<List<Post>> testObserver = new TestObserver<>();
        feedRepository.getPosts().subscribeWith(testObserver);

        testObserver.assertResult(mockPostsList);
    }

    @Test
    public void testFeedRepositorySaveApiDataInStore() {
        FeedDataSource feedDataSource = mock(FeedDataSource.class);
        PostsStorage postsStorage = new PostsMemStorage();
        FeedRepository feedRepository = new FeedRepository(feedDataSource, postsStorage);

        List<Post> mockPostsList = generateMockPostsList();
        when(feedDataSource.getPosts()).thenReturn(Observable.just(mockPostsList));

        TestObserver<List<Post>> testObserver = new TestObserver<>();
        feedRepository.getPosts().subscribeWith(testObserver);

        TestObserver<List<Post>> testObserver2 = new TestObserver<>();
        postsStorage.getPosts().subscribeWith(testObserver2);
        assertEquals(mockPostsList.size(), testObserver2.values().get(0).size());
    }

    @Test
    public void testFeedRepositoryReturnsStoreData() {
        FeedDataSource feedDataSource = mock(FeedDataSource.class);
        PostsStorage postsStorage = mock(PostsStorage.class);
        FeedRepository feedRepository = new FeedRepository(feedDataSource, postsStorage);

        List<Post> mockPostsList = generateMockPostsList();
        when(postsStorage.getPosts()).thenReturn(Observable.just(mockPostsList));
        when(feedDataSource.getPosts()).thenReturn(Observable.just(new ArrayList<>()));

        TestObserver<List<Post>> testObserver = new TestObserver<>();
        feedRepository.getPosts().subscribeWith(testObserver);

        verifyZeroInteractions(feedDataSource);
        testObserver.assertResult(mockPostsList);
    }

    private List<Post> generateMockPostsList() {
        List<Post> posts = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Post post = mock(Post.class);
            when(post.getId()).thenReturn(i);
            posts.add(post);
        }
        return posts;
    }


}
