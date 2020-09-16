package com.chs.app_jetpack.ui.camera;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Matrix;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.TextureView;
import android.webkit.MimeTypeMap;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.VideoCapture;
import androidx.camera.view.CameraView;
import androidx.core.app.ActivityCompat;

import com.blankj.utilcode.util.ScreenUtils;
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
    private int mLensFacing = CameraSelector.LENS_FACING_BACK;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_view);
        mCameraView = findViewById(R.id.view_finder);
        mRecordView = findViewById(R.id.record_view);
        mBtnCameraSwitch = findViewById(R.id.camera_switch_button);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mCameraView.bindToLifecycle(this);

        mExecutorService = Executors.newSingleThreadExecutor();

        takePictureAndVideo();

        mBtnCameraSwitch.setOnClickListener(v -> {
            if(mLensFacing == CameraSelector.LENS_FACING_FRONT){
                mLensFacing = CameraSelector.LENS_FACING_BACK;
            }else {
                mLensFacing = CameraSelector.LENS_FACING_FRONT;
            }
            mCameraView.setCameraLensFacing(mLensFacing);
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
        ImageCapture.Metadata metadata = new ImageCapture.Metadata();
        metadata.setReversedHorizontal(mLensFacing == CameraSelector.LENS_FACING_FRONT);
        ImageCapture.OutputFileOptions outputFileOptions = new ImageCapture
                .OutputFileOptions.Builder(file)
                .setMetadata(metadata)
                .build();
        mCameraView.takePicture(outputFileOptions, mExecutorService, new ImageCapture.OnImageSavedCallback() {
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
        VideoCapture.Metadata metadata = new VideoCapture.Metadata();

        mCameraView.startRecording(file, mExecutorService, new VideoCapture.OnVideoSavedCallback() {
            @Override
            public void onVideoSaved(@NonNull VideoCapture.OutputFileResults outputFileResults) {
                outputFilePath = file.getAbsolutePath();
                onFileSaved(Uri.fromFile(file));
            }

            @Override
            public void onError(int videoCaptureError, @NonNull String message, @Nullable Throwable cause) {
                Log.i(TAG,message);
            }
        });
    }

    /**
     * 预览自拍视频时 旋转TextureView 解决左右镜像的问题
     *
     * @param textureView
     */
    private void transformsTextureView(TextureView textureView) {
        Matrix matrix = new Matrix();
        int screenHeight = ScreenUtils.getScreenHeight();
        int screenWidth = ScreenUtils.getScreenWidth();
        if (mLensFacing == CameraSelector.LENS_FACING_FRONT) {
            matrix.postScale(-1, 1, 1f * screenWidth / 2, 1f * screenHeight / 2);
        } else {
            matrix.postScale(1, 1, 1f * screenWidth / 2, 1f * screenHeight / 2);
        }
        textureView.setTransform(matrix);
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
