package com.yossisegev.data.feed;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Yossi Segev on 09/09/2017.
 */

public interface FeedService {

    @GET("posts?days_ago=1")
    Observable<FeedResponse> getFeed();
}
