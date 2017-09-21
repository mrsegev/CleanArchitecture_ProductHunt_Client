package com.yossisegev.producthuntclient.views;

import com.yossisegev.domain.feed.Post;
import com.yossisegev.producthuntclient.common.MvpView;

import java.util.List;

/**
 * Created by Yossi Segev on 09/09/2017.
 */

public interface FeedView extends MvpView {
    void showFeed(List<Post> posts);
    void showLoading(boolean show);
}
