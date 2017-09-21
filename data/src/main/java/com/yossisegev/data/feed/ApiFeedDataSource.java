package com.yossisegev.data.feed;

import com.yossisegev.domain.feed.FeedDataSource;
import com.yossisegev.domain.feed.Post;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Yossi Segev on 09/09/2017.
 */

public class ApiFeedDataSource implements FeedDataSource {

    private FeedService feedService;

    public ApiFeedDataSource(FeedService feedService) {
        this.feedService = feedService;
    }

    @Override
    public Observable<List<Post>> getPosts() {
        return feedService.getFeed().flatMap((feedResponse) ->
                Observable.just(feedResponse.getPosts()));
    }
}
