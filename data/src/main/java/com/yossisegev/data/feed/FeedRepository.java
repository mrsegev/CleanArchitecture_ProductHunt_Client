package com.yossisegev.data.feed;

import com.yossisegev.domain.feed.FeedDataSource;
import com.yossisegev.domain.feed.Post;
import com.yossisegev.domain.feed.PostsStorage;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Yossi Segev on 17/09/2017.
 */

public class FeedRepository implements FeedDataSource {

    private FeedDataSource feedDataSource;
    private PostsStorage postsStorage;

    public FeedRepository(FeedDataSource feedDataSource, PostsStorage postsStorage) {
        this.feedDataSource = feedDataSource;
        this.postsStorage = postsStorage;
    }

    @Override
    public Observable<List<Post>> getPosts() {

        return postsStorage.getPosts().flatMap((localPosts -> {

            if (localPosts != null && !localPosts.isEmpty()) {
                return Observable.just(localPosts);
            }

            return feedDataSource.getPosts().doOnNext(apiPosts -> {
                postsStorage.savePosts(apiPosts);
            });
        }));
    }
}
