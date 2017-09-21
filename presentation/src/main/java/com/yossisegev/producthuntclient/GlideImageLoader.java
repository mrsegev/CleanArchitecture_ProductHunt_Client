package com.yossisegev.producthuntclient;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Yossi Segev on 20/09/2017.
 */

public class GlideImageLoader implements ImageLoader {

    private Context context;

    public GlideImageLoader(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    public void loadImage(String url, ImageView imageView) {
        Glide.with(context).load(url).into(imageView);
    }
}
