package com.chs.app_jetpack.ui.camera;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.service.autofill.LuhnChecksumValidator;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Size;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.AspectRatio;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraInfoUnavailableException;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.core.VideoCapture;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.VideoCaptureConfig;
import androidx.camera.extensions.AutoImageCaptureExtender;
import androidx.camera.extensions.AutoPreviewExtender;
import androidx.camera.extensions.BeautyImageCaptureExtender;
import androidx.camera.extensions.BeautyPreviewExtender;
import androidx.camera.extensions.BokehImageCaptureExtender;
import androidx.camera.extensions.BokehPreviewExtender;
import androidx.camera.extensions.HdrImageCaptureExtender;
import androidx.camera.extensions.HdrPreviewExtender;
import androidx.camera.extensions.NightImageCaptureExtender;
import androidx.camera.extensions.NightPreviewExtender;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.CameraView;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.chs.app_jetpack.R;
import com.chs.app_jetpack.camera.RecordView;
import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author：chs
 * date：2020/5/21
 * des：
 */
public class CameraActivity extends AppCompatActivity {
    private static final String[] PERMISSIONS = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO};
    private static final int  PERMISSIONS_REQUEST_CODE = 10;
    private static final double RATIO_4_3_VALUE = 4.0 / 3.0;
    private static final double  RATIO_16_9_VALUE = 16.0 / 9.0;
    private Size resolution = new Size(1080,1920);
    private ArrayList<String> deniedPermission = new ArrayList<>();
    private String TAG = this.getClass().getSimpleName();
    private String outputFilePath;
    private PreviewView mPreviewView;
    private RecordView mRecordView;
    private ImageButton mBtnCameraSwitch;
    private Preview mPreview;
    private ExecutorService mExecutorService;
    /**
     * 照相
     */
    private ImageCapture mImageCapture;
    /**
     * 录制视频
     */
    private VideoCapture mVideoCapture;
    private ImageAnalysis mImageAnalysis;
    private Camera mCamera;
    /**
     * 可以将一个camera跟任意的LifecycleOwner绑定的一个单例类
     */
    private ProcessCameraProvider mCameraProvider;
    /**
     * 摄像头朝向 默认向后
     */
    private int mLensFacing = CameraSelector.LENS_FACING_BACK;
    /**
     * 是否是照相
     */
    private boolean takingPicture;

    public static boolean hsaPermission(Context context){
        for (String permission : PERMISSIONS) {
            boolean res = ContextCompat.checkSelfPermission(context,permission) == PackageManager.PERMISSION_DENIED;
            if(res){
                return false;
            }
        }
        return true;
    }

    public static void start(Activity activity){
        Intent intent = new Intent(activity,CameraActivity .class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        mExecutorService = Executors.newSingleThreadExecutor();
        if(!hsaPermission(this)){
            ActivityCompat.requestPermissions(this,PERMISSIONS,PERMISSIONS_REQUEST_CODE);
        }else {
            setUpCamera();
        }
        mPreviewView = findViewById(R.id.view_finder);
        mRecordView = findViewById(R.id.record_view);
        mBtnCameraSwitch = findViewById(R.id.camera_switch_button);

        updateCameraUi();
        setRecordListener();
        mBtnCameraSwitch.setOnClickListener(v -> {
            if (CameraSelector.LENS_FACING_FRONT == mLensFacing){
                mLensFacing = CameraSelector.LENS_FACING_BACK;
            }else {
                mLensFacing = CameraSelector.LENS_FACING_FRONT;
            }
            bindCameraUseCases();
        });
    }

    private void setRecordListener() {
        mRecordView.setOnRecordListener(new RecordView.OnRecordListener() {
            @Override
            public void onTackPicture() {
                //拍照
                takingPicture = true;
                //创建图片保存的文件地址
                File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath(),
                        System.currentTimeMillis() + ".jpeg");
                ImageCapture.OutputFileOptions outputFileOptions = new ImageCapture.OutputFileOptions.Builder(file).build();
                mImageCapture.takePicture(outputFileOptions,mExecutorService , new ImageCapture.OnImageSavedCallback() {
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

            @SuppressLint("RestrictedApi")
            @Override
            public void onRecordVideo() {
                //视频
                takingPicture = false;
                //创建视频保存的文件地址
                File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath(),
                        System.currentTimeMillis() + ".mp4");
                mVideoCapture.startRecording(file, Executors.newSingleThreadExecutor(), new VideoCapture.OnVideoSavedCallback() {
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

            @SuppressLint("RestrictedApi")
            @Override
            public void onFinish() {
                //录制完成
                mVideoCapture.stopRecording();
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISSIONS_REQUEST_CODE){
            deniedPermission.clear();
            for (int i = 0; i < permissions.length; i++) {
                String permission = permissions[i];
                int grant = grantResults[i];
                if(grant == PackageManager.PERMISSION_DENIED){
                    deniedPermission.add(permission);
                }
            }
            if(deniedPermission.isEmpty()){
                setUpCamera();
            }else {
                new AlertDialog.Builder(this)
                        .setMessage("有权限没有授权，无法使用")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String[] denied = new String[deniedPermission.size()];
                                ActivityCompat.requestPermissions(CameraActivity.this, deniedPermission.toArray(denied), PERMISSIONS_REQUEST_CODE);
                            }
                        }).create().show();
            }
        }
    }

    private void updateCameraUi() {
        //必须先remove在add这样视频流画面才能正确的显示出来
        ViewGroup parent = (ViewGroup) mPreviewView.getParent();
        parent.removeView(mPreviewView);
        parent.addView(mPreviewView,0);
    }

    private void setUpCamera() {
        //Future表示一个异步的任务，ListenableFuture可以监听这个任务，当任务完成的时候执行回调
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture =
                ProcessCameraProvider.getInstance(this);
        cameraProviderFuture.addListener(new Runnable() {
            @Override
            public void run() {
                try {
                    mCameraProvider = cameraProviderFuture.get();
                    //选择摄像头的朝向
                    mLensFacing = getLensFacing();
                    if(mLensFacing == -1){
                        Toast.makeText(getApplicationContext(),"无可用的设备cameraId!,请检查设备的相机是否被占用",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    // 构建并绑定照相机用例
                    bindCameraUseCases();

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, ContextCompat.getMainExecutor(this));
    }

    @SuppressLint("RestrictedApi")
    private void bindCameraUseCases() {
        //获取屏幕的分辨率
        DisplayMetrics displayMetrics = new DisplayMetrics();
        mPreviewView.getDisplay().getRealMetrics(displayMetrics);
        //获取宽高比
        int screenAspectRatio = aspectRatio(displayMetrics.widthPixels, displayMetrics.heightPixels);

        int rotation = mPreviewView.getDisplay().getRotation();

        if(mCameraProvider == null){
            Toast.makeText(getApplicationContext(),"相机初始化失败",Toast.LENGTH_SHORT).show();
            return;
        }

        ProcessCameraProvider cameraProvider = mCameraProvider;

        CameraSelector cameraSelector = new CameraSelector.Builder().requireLensFacing(mLensFacing).build();

        Preview.Builder pBuilder = new Preview.Builder();

        setPreviewExtender(pBuilder,cameraSelector);

        mPreview = pBuilder
                //设置宽高比
                .setTargetAspectRatio(screenAspectRatio)
                //设置当前屏幕的旋转
                .setTargetRotation(rotation)
                .build();

        ImageCapture.Builder builder = new ImageCapture.Builder();

        setImageCaptureExtender(builder,cameraSelector);

        mImageCapture = builder
                //优化捕获速度，可能降低图片质量
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                //设置宽高比
                .setTargetAspectRatio(screenAspectRatio)
                //设置初始的旋转角度
                .setTargetRotation(rotation)
                .build();
        mVideoCapture = new VideoCaptureConfig.Builder()
                //设置当前旋转
                .setTargetRotation(rotation)
                //设置宽高比
                .setTargetAspectRatio(screenAspectRatio)
//                分辨率
//                .setTargetResolution(resolution)
                //视频帧率  越高视频体积越大
                .setVideoFrameRate(25)
                //bit率  越大视频体积越大
                .setBitRate(3 * 1024 * 1024)
                .build();
        mImageAnalysis = new ImageAnalysis.Builder()
                .setTargetAspectRatio(screenAspectRatio)
                .setTargetRotation(rotation)
                .build();
        mImageAnalysis.setAnalyzer(mExecutorService, new ImageAnalysis.Analyzer() {
            @Override
            public void analyze(@NonNull ImageProxy image) {

            }
        });


        //重新绑定之前必须先取消绑定
        cameraProvider.unbindAll();

        mCamera = cameraProvider.bindToLifecycle(CameraActivity.this,
                cameraSelector,mPreview,mImageCapture,mVideoCapture);
        mPreview.setSurfaceProvider(mPreviewView.createSurfaceProvider(mCamera.getCameraInfo()));
    }

    /**
     * 给预览设置外部扩展
     * @param builder
     * @param cameraSelector
     */
    private void setPreviewExtender(Preview.Builder builder, CameraSelector cameraSelector) {
        AutoPreviewExtender extender = AutoPreviewExtender.create(builder);
        if(extender.isExtensionAvailable(cameraSelector)){
            extender.enableExtension(cameraSelector);
        }
        BokehPreviewExtender bokehPreviewExtender = BokehPreviewExtender.create(builder);
        if(bokehPreviewExtender.isExtensionAvailable(cameraSelector)){
            bokehPreviewExtender.enableExtension(cameraSelector);
        }
        HdrPreviewExtender hdrPreviewExtender = HdrPreviewExtender.create(builder);
        if(hdrPreviewExtender.isExtensionAvailable(cameraSelector)){
            hdrPreviewExtender.enableExtension(cameraSelector);
        }
        BeautyPreviewExtender beautyPreviewExtender = BeautyPreviewExtender.create(builder);
        if(beautyPreviewExtender.isExtensionAvailable(cameraSelector)){
            beautyPreviewExtender.enableExtension(cameraSelector);
        }
        NightPreviewExtender nightPreviewExtender = NightPreviewExtender.create(builder);
        if(nightPreviewExtender.isExtensionAvailable(cameraSelector)){
            nightPreviewExtender.enableExtension(cameraSelector);
        }
    }

    /**
     * 给拍照设置外部预览
     * @param builder
     * @param cameraSelector
     */
    private void setImageCaptureExtender(ImageCapture.Builder builder, CameraSelector cameraSelector) {
        AutoImageCaptureExtender autoImageCaptureExtender = AutoImageCaptureExtender.create(builder);
        if (autoImageCaptureExtender.isExtensionAvailable(cameraSelector)) {
            autoImageCaptureExtender.enableExtension(cameraSelector);
        }
        BokehImageCaptureExtender bokehImageCaptureExtender = BokehImageCaptureExtender.create(builder);
        if(bokehImageCaptureExtender.isExtensionAvailable(cameraSelector)){
            bokehImageCaptureExtender.enableExtension(cameraSelector);
        }
        HdrImageCaptureExtender hdrImageCaptureExtender = HdrImageCaptureExtender.create(builder);
        if(hdrImageCaptureExtender.isExtensionAvailable(cameraSelector)){
            hdrImageCaptureExtender.enableExtension(cameraSelector);
        }
        BeautyImageCaptureExtender beautyImageCaptureExtender = BeautyImageCaptureExtender.create(builder);
        if(beautyImageCaptureExtender.isExtensionAvailable(cameraSelector)){
            beautyImageCaptureExtender.enableExtension(cameraSelector);
        }
        NightImageCaptureExtender nightImageCaptureExtender = NightImageCaptureExtender.create(builder);
        if(nightImageCaptureExtender.isExtensionAvailable(cameraSelector)){
            nightImageCaptureExtender.enableExtension(cameraSelector);
        }
    }

    private int aspectRatio(int widthPixels, int heightPixels) {
        double previewRatio = (double) Math.max(widthPixels, heightPixels) / (double) Math.min(widthPixels, heightPixels);
        if (Math.abs(previewRatio - RATIO_4_3_VALUE) <= Math.abs(previewRatio - RATIO_16_9_VALUE)) {
            return AspectRatio.RATIO_4_3;
        }
        return AspectRatio.RATIO_16_9;
    }

    private int getLensFacing() {
        if(hasBackCamera()){
            return CameraSelector.LENS_FACING_BACK;
        }
        if(hasFrontCamera()){
            return CameraSelector.LENS_FACING_FRONT;
        }
        return -1;
    }

    /**
     * 是否有后摄像头
     */
    private boolean hasBackCamera(){
        if(mCameraProvider == null){
            return false;
        }
        try {
            return mCameraProvider.hasCamera(CameraSelector.DEFAULT_BACK_CAMERA);
        } catch (CameraInfoUnavailableException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 是否有前摄像头
     */
    private boolean hasFrontCamera(){
        if(mCameraProvider == null){
            return false;
        }
        try {
            return mCameraProvider.hasCamera(CameraSelector.DEFAULT_BACK_CAMERA);
        } catch (CameraInfoUnavailableException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        mExecutorService.shutdown();
        super.onDestroy();
    }
}
