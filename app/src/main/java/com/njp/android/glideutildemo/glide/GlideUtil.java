package com.njp.android.glideutildemo.glide;


import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

public class GlideUtil {

    /**
     * 普通的图片加载
     */
    public static void simpleProgress(Context context, String url, ImageView imageView) {
        Glide
                .with(context)
                .load(url)
                .into(imageView);
    }

    /**
     * 带进度的图片加载
     *
     * @param context
     * @param url
     * @param imageView
     * @param listener
     */
    public static void progressLoad(Context context, String url, ImageView imageView, final LoadImageListener listener) {

        ProgressInterceptor.addListener(url, new ProgressListener() {
            @Override
            public void onProgress(int progress) {
                listener.onProgress(progress);
            }
        });

        Glide
                .with(context)
                .load(url)
                .into(new GlideDrawableImageViewTarget(imageView) {
                    @Override
                    public void onLoadStarted(Drawable placeholder) {
                        super.onLoadStarted(placeholder);
                        listener.onStart();
                    }

                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);
                        listener.onSuccess(null);
                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);
                        listener.onFailure();
                    }
                });

    }

    /**
     * 带进度的图片下载
     *
     * @param context
     * @param url
     * @param listener
     */
    public static void progressDownload(Context context, String url, final LoadImageListener listener) {

        ProgressInterceptor.addListener(url, new ProgressListener() {
            @Override
            public void onProgress(int progress) {
                listener.onProgress(progress);
            }
        });

        Glide
                .with(context)
                .load(url)
                .downloadOnly(new DownloadTarget(listener));

    }

    /**
     * 带进度的图片下载并加载
     */
    public static void progressDownloadAndLoad(Context context, String url, ImageView imageView, final LoadImageListener listener) {

        ProgressInterceptor.addListener(url, new ProgressListener() {
            @Override
            public void onProgress(int progress) {
                listener.onProgress(progress);
            }
        });

        Glide
                .with(context)
                .load(url)
                .downloadOnly(new DownloadAndLoadTarget(listener, imageView));

    }
}
