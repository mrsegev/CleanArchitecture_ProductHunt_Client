package com.yossisegev.domain.feed;

import com.yossisegev.domain.UseCase;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Created by Yossi Segev on 09/09/2017.
 */

public class GetFeedUseCase extends UseCase<List<Post>> {

    private FeedDataSource feedDataSource;

    public GetFeedUseCase(FeedDataSource feedDataSource,
                          Scheduler subscribeOn,
                          Scheduler observeOn) {

        super(subscribeOn, observeOn);
        this.feedDataSource = feedDataSource;
    }

    @Override
    public Observable<List<Post>> createObservable() {
        return feedDataSource.getPosts();
    }
}
