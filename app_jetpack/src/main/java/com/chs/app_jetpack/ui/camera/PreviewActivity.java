package com.chs.app_jetpack.ui.camera;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.chs.app_jetpack.R;
import com.github.chrisbanes.photoview.PhotoView;

import java.io.File;

/**
 * author：chs
 * date：2020/5/25
 * des： 预览
 */
public class PreviewActivity extends AppCompatActivity {
    public static final String KEY_PREVIEW_URL = "key_preview_url";
    public static final String KEY_IS_VIDEO = "key_is_video";
    private PhotoView mPhotoView;
    private VideoView mVideoView;


    public static void start(Activity activity, String previewUrl, boolean isVideo){
        Intent intent = new Intent(activity,PreviewActivity.class);
        intent.putExtra(KEY_PREVIEW_URL,previewUrl);
        intent.putExtra(KEY_IS_VIDEO,isVideo);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        mPhotoView = findViewById(R.id.photo_view);
        mVideoView = findViewById(R.id.video_view);
        String path = getIntent().getStringExtra(KEY_PREVIEW_URL);
        boolean isVideo = getIntent().getBooleanExtra(KEY_IS_VIDEO,false);

        if(!isVideo){
            Glide.with(this).load(path).into(mPhotoView);
            mVideoView.setVisibility(View.GONE);
        }else {
            mPhotoView.setVisibility(View.GONE);
            showVideo(path);
        }

    }

    private void showVideo(String path) {
        MediaController controller = new MediaController(this);
        mVideoView.setVideoPath(path);
        controller.setMediaPlayer(mVideoView);
        mVideoView.setMediaController(controller);
        mVideoView.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVideoView.stopPlayback();
    }
}
