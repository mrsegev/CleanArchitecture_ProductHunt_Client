package com.yossisegev.domain.feed;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Yossi Segev on 09/09/2017.
 */

public interface FeedDataSource {
    Observable<List<Post>> getPosts();
}
