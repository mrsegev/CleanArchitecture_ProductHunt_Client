package com.yossisegev.domain.feed;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Yossi Segev on 09/09/2017.
 */

public class Post {
    private int id;
    private String name;
    private String tagline;
    private Thumbnail thumbnail;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTagline() {
        return tagline;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public static class Thumbnail {
        @SerializedName("image_url")
        private String imageUrl;

        public String getImageUrl() {
            return imageUrl;
        }
    }


}
