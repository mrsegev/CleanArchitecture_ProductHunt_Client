package com.yossisegev.producthuntclient.di.modules;

import com.yossisegev.data.feed.ApiFeedDataSource;
import com.yossisegev.data.feed.FeedRepository;
import com.yossisegev.data.feed.FeedService;
import com.yossisegev.data.feed.PostsMemStorage;
import com.yossisegev.domain.feed.FeedDataSource;
import com.yossisegev.domain.feed.GetFeedUseCase;
import com.yossisegev.domain.feed.PostsStorage;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import retrofit2.Retrofit;

/**
 * Created by Yossi Segev on 09/09/2017.
 */

@Module
public class FeedModule {

    @Provides
    public FeedService feedService(Retrofit retrofit) {
        return retrofit.create(FeedService.class);
    }

    @Provides
    public FeedDataSource feedDataSource(FeedService feedService) {
        return new ApiFeedDataSource(feedService);
    }

    @Provides
    @Named("feed_repository")
    public FeedDataSource feedRepository(FeedDataSource dataSource, PostsStorage postsStorage) {
        return new FeedRepository(dataSource, postsStorage);
    }
    @Provides
    public GetFeedUseCase getFeedUseCase(@Named("feed_repository") FeedDataSource feedDataSource,
                                         @Named(ApplicationModule.SCHEDULER_SUBSCRIBE_ON) Scheduler subscribeOn,
                                         @Named(ApplicationModule.SCHEDULER_OBSERVE_ON) Scheduler observeOn) {
        return new GetFeedUseCase(feedDataSource, subscribeOn, observeOn);
    }
}
