package com.yossisegev.producthuntclient.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.yossisegev.domain.feed.Post;
import com.yossisegev.producthuntclient.FeedAdapter;
import com.yossisegev.producthuntclient.ImageLoader;
import com.yossisegev.producthuntclient.R;
import com.yossisegev.producthuntclient.common.App;
import com.yossisegev.producthuntclient.di.modules.FeedModule;
import com.yossisegev.producthuntclient.presenters.FeedPresenter;
import com.yossisegev.producthuntclient.views.FeedView;

import java.util.List;

import javax.inject.Inject;

public class FeedActivity extends AppCompatActivity implements FeedView {

    @Inject
    FeedPresenter feedPresenter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private FeedAdapter adapter;
    @Inject
    ImageLoader imageLoader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ((App) getApplication()).getApplicationComponent().plus(new FeedModule()).inject(this);
        recyclerView = findViewById(R.id.activity_feed_recycler_view);
        progressBar = findViewById(R.id.activity_feed_progress_bar);

        adapter = new FeedAdapter(imageLoader);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        feedPresenter.attach(this);
        feedPresenter.fetch();
    }

    @Override
    public void showFeed(List<Post> posts) {
        adapter.setPosts(posts);
    }

    @Override
    public void showLoading(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
