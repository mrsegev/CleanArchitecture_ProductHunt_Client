package com.yossisegev.data.feed;

import com.yossisegev.domain.feed.Post;

import java.util.List;

/**
 * Created by Yossi Segev on 09/09/2017.
 */

public class FeedResponse {
    private List<Post> posts;

    public List<Post> getPosts() {
        return posts;
    }
}
