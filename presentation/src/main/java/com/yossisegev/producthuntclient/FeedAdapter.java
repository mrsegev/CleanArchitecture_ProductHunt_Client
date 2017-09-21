package com.yossisegev.producthuntclient;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yossisegev.domain.feed.Post;

import java.util.List;

/**
 * Created by Yossi Segev on 09/09/2017.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.PostViewHolder> {

    private List<Post> posts;
    private ImageLoader imageLoader;

    public FeedAdapter(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feedadapter_item, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.name.setText(post.getName());
        holder.tagline.setText(post.getTagline());
        if (!TextUtils.isEmpty(post.getThumbnail().getImageUrl())) {
            imageLoader.loadImage(post.getThumbnail().getImageUrl(), holder.image);
        }
    }

    @Override
    public int getItemCount() {
        return posts != null ? posts.size() : 0;
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView tagline;
        ImageView image;

        public PostViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.feedadapter_item_name);
            tagline = itemView.findViewById(R.id.feedadapter_item_tagline);
            image = itemView.findViewById(R.id.feedadapter_item_image);
        }
    }
}
