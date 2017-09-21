package com.yossisegev.data.feed;

import android.support.annotation.Nullable;
import android.util.SparseArray;

import com.yossisegev.domain.feed.Post;
import com.yossisegev.domain.feed.PostsStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Yossi Segev on 10/09/2017.
 */

public class PostsMemStorage implements PostsStorage {

    private HashMap<Integer, Post> hashMap;

    public PostsMemStorage() {
        hashMap = new HashMap<>();
    }

    @Override
    public void savePosts(List<Post> posts) {
        for (Post post : posts) {
            hashMap.put(post.getId(), post);
        }
    }

    @Override
    public Observable<List<Post>> getPosts() {
        List<Post> posts = new ArrayList<>(hashMap.values());
        return Observable.just(posts);
    }

    @Override
    public Observable<Post> getPostById(int id) {
        if (hashMap.get(id) != null) {
            return Observable.just(hashMap.get(id));
        }
        return Observable.empty();
    }

    @Override
    public void clear() {
        hashMap.clear();
    }


}
