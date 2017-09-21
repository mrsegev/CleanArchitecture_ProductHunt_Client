package com.yossisegev.domain.feed;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * Created by Yossi Segev on 09/09/2017.
 */

public interface PostsStorage  {

    Observable<List<Post>> getPosts();

    void savePosts(List<Post> posts);

    Observable<Post> getPostById(int id);

    void clear();
}
