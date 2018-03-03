package com.njp.android.glideutildemo.glide;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;

import java.io.File;


public class DownloadAndLoadTarget implements Target<File> {

    private LoadImageListener mListener;
    private ImageView mImageView;

    public DownloadAndLoadTarget(LoadImageListener listener, ImageView imageView) {
        mListener = listener;
        mImageView = imageView;
    }

    @Override
    public void onLoadStarted(Drawable placeholder) {
        mListener.onStart();
    }

    @Override
    public void onLoadFailed(Exception e, Drawable errorDrawable) {
        mListener.onFailure();
    }

    @Override
    public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {
        Bitmap bitmap = BitmapFactory.decodeFile(resource.getPath());
        mImageView.setImageBitmap(bitmap);
        mListener.onSuccess(resource.getPath());
    }

    @Override
    public void onLoadCleared(Drawable placeholder) {

    }

    @Override
    public void getSize(SizeReadyCallback cb) {
        cb.onSizeReady(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
    }

    @Override
    public void setRequest(Request request) {

    }

    @Override
    public Request getRequest() {
        return null;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }
}
