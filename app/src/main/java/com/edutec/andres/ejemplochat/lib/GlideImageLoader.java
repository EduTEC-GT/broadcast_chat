package com.edutec.andres.ejemplochat.lib;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.edutec.andres.ejemplochat.lib.base.ImageLoader;


/**
 * Created by javie on 11/12/2017.
 */

public class GlideImageLoader implements ImageLoader {

    private RequestManager glideRequestManager;

    public GlideImageLoader(RequestManager glideRequestManager) {
        this.glideRequestManager = glideRequestManager;
    }

    public GlideImageLoader(Context context) {
        this.glideRequestManager = Glide.with(context);
    }

    @Override
    public void load(ImageView imageView, String url) {
        this.glideRequestManager
                .load(url)
                .into(imageView);
    }
}
