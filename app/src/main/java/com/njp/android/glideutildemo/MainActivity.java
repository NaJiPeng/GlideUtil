package com.njp.android.glideutildemo;

import android.app.ProgressDialog;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.njp.android.glideutildemo.glide.GlideUtil;
import com.njp.android.glideutildemo.glide.LoadImageListener;

public class MainActivity extends AppCompatActivity {

    private String url = "https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-629448.jpg";

    private Button mBtnLoad;
    private ImageView mIvImage;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnLoad = findViewById(R.id.btn_load);
        mIvImage = findViewById(R.id.iv_image);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setMessage("加载中");

        mBtnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //simpleLoad();
                //progressLoad();
                //progressDownload();
                progressDownloadAndLoad();

            }
        });

    }

    private void progressDownloadAndLoad() {
        GlideUtil.progressDownloadAndLoad(MainActivity.this, url, mIvImage, new LoadImageListener() {
            @Override
            public void onStart() {
                mProgressDialog.show();
            }

            @Override
            public void onProgress(int progress) {
                mProgressDialog.setProgress(progress);
            }

            @Override
            public void onSuccess(@Nullable String path) {
                mProgressDialog.dismiss();
                Toast.makeText(getApplication(),path,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure() {
                mProgressDialog.dismiss();
                Toast.makeText(getApplication(),"fail",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void simpleLoad() {
        GlideUtil.simpleProgress(MainActivity.this,url,mIvImage);
    }

    private void progressDownload() {
        GlideUtil.progressDownload(getApplicationContext(), url, new LoadImageListener() {
            @Override
            public void onStart() {
                mProgressDialog.show();
            }

            @Override
            public void onProgress(int progress) {
                mProgressDialog.setProgress(progress);
            }

            @Override
            public void onSuccess(@Nullable String path) {
                mProgressDialog.dismiss();
                Toast.makeText(getApplication(), path, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure() {
                mProgressDialog.dismiss();
                Toast.makeText(getApplication(), "fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void progressLoad() {
        GlideUtil.progressLoad(MainActivity.this, url, mIvImage, new LoadImageListener() {
            @Override
            public void onStart() {
                mProgressDialog.show();
            }

            @Override
            public void onProgress(int progress) {
                mProgressDialog.setProgressStyle(progress);
            }

            @Override
            public void onSuccess(@Nullable String path) {
                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                mProgressDialog.dismiss();
            }


            @Override
            public void onFailure() {
                Toast.makeText(getApplicationContext(),"Fail",Toast.LENGTH_SHORT).show();
                mProgressDialog.dismiss();
            }
        });
    }
}
