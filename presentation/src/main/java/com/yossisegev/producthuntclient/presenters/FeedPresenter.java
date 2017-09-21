package com.yossisegev.producthuntclient.presenters;

import com.yossisegev.domain.feed.GetFeedUseCase;
import com.yossisegev.domain.feed.Post;
import com.yossisegev.domain.feed.PostsStorage;
import com.yossisegev.producthuntclient.common.Presenter;
import com.yossisegev.producthuntclient.views.FeedView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Yossi Segev on 09/09/2017.
 */

public class FeedPresenter extends Presenter<FeedView> {

    private GetFeedUseCase getFeedUseCase;

    @Inject
    public FeedPresenter(GetFeedUseCase getFeedUseCase) {
        this.getFeedUseCase = getFeedUseCase;
    }

    @Override
    public void onAttach(FeedView view) {
    }

    public void fetch() {
        getView().showLoading(true);
        getFeedUseCase.execute(new DisposableObserver<List<Post>>() {
            @Override
            public void onNext(@NonNull List<Post> posts) {
                getView().showLoading(false);
                getView().showFeed(posts);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                getView().showLoading(false);
            }

            @Override
            public void onComplete() { }
        });
    }

    @Override
    public void onDetach() {
        getFeedUseCase.cancel();
    }
}
