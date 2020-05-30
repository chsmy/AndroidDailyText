package com.chs.app_jetpack.ui.camera;

import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.VideoCapture;
import androidx.camera.view.CameraView;

import com.chs.app_jetpack.R;
import com.chs.app_jetpack.camera.RecordView;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author：chs
 * date：2020/5/29
 * des：
 */
public class CameraViewActivity extends AppCompatActivity {

    private String TAG = this.getClass().getSimpleName();
    private RecordView mRecordView;
    private ImageButton mBtnCameraSwitch;
    private CameraView mCameraView;
    private ExecutorService mExecutorService;
    private String outputFilePath;
    /**
     * 是否是照相
     */
    private boolean takingPicture;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_view);
        mCameraView = findViewById(R.id.view_finder);
        mRecordView = findViewById(R.id.record_view);
        mBtnCameraSwitch = findViewById(R.id.camera_switch_button);

        mCameraView.bindToLifecycle(this);

        mExecutorService = Executors.newSingleThreadExecutor();

        takePictureAndVideo();

        mBtnCameraSwitch.setOnClickListener(v -> {
            Integer cameraLensFacing = mCameraView.getCameraLensFacing();
            if(cameraLensFacing == CameraSelector.LENS_FACING_FRONT){
                cameraLensFacing = CameraSelector.LENS_FACING_BACK;
            }else {
                cameraLensFacing = CameraSelector.LENS_FACING_FRONT;
            }
            mCameraView.setCameraLensFacing(cameraLensFacing);
        });
    }

    private void takePictureAndVideo() {
        mRecordView.setOnRecordListener(new RecordView.OnRecordListener() {
            @Override
            public void onTackPicture() {
                //拍照
                takingPicture = true;
                takePicture();
            }

            @Override
            public void onRecordVideo() {
                //视频
                takingPicture = false;
                takeVideo();
            }

            @Override
            public void onFinish() {
                mCameraView.stopRecording();
            }
        });
    }

    private void takePicture() {
        //创建图片保存的文件地址
        File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath(),
                System.currentTimeMillis() + ".jpeg");
        mCameraView.setCaptureMode(CameraView.CaptureMode.IMAGE);
        mCameraView.takePicture(file, mExecutorService, new ImageCapture.OnImageSavedCallback() {
            @Override
            public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                Uri savedUri = outputFileResults.getSavedUri();
                if(savedUri == null){
                    savedUri = Uri.fromFile(file);
                }
                outputFilePath = file.getAbsolutePath();
                onFileSaved(savedUri);
            }

            @Override
            public void onError(@NonNull ImageCaptureException exception) {
                Log.e(TAG, "Photo capture failed: "+exception.getMessage(), exception);
            }
        });
    }

    private void takeVideo() {
        //创建视频保存的文件地址
        File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath(),
                System.currentTimeMillis() + ".mp4");
        mCameraView.setCaptureMode(CameraView.CaptureMode.VIDEO);
        mCameraView.startRecording(file, mExecutorService, new VideoCapture.OnVideoSavedCallback() {
            @Override
            public void onVideoSaved(@NonNull File file) {
                outputFilePath = file.getAbsolutePath();
                onFileSaved(Uri.fromFile(file));
            }

            @Override
            public void onError(int videoCaptureError, @NonNull String message, @Nullable Throwable cause) {
                Log.i(TAG,message);
            }
        });
    }

    private void onFileSaved(Uri savedUri) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            sendBroadcast(new Intent(android.hardware.Camera.ACTION_NEW_PICTURE, savedUri));
        }
        String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap
                .getFileExtensionFromUrl(savedUri.getPath()));
        MediaScannerConnection.scanFile(getApplicationContext(),
                new String[]{new File(savedUri.getPath()).getAbsolutePath()},
                new String[]{mimeTypeFromExtension}, new MediaScannerConnection.OnScanCompletedListener() {
                    @Override
                    public void onScanCompleted(String path, Uri uri) {
                        Log.d(TAG, "Image capture scanned into media store: $uri"+uri);
                    }
                });
        PreviewActivity.start(this, outputFilePath, !takingPicture);
    }

    @Override
    protected void onDestroy() {
        mExecutorService.shutdown();
        super.onDestroy();
    }

}
