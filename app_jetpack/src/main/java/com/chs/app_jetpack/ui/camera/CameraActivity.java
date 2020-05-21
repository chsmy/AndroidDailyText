package com.chs.app_jetpack.ui.camera;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.AspectRatio;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraInfoUnavailableException;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Preview;
import androidx.camera.core.VideoCapture;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.chs.app_jetpack.R;
import com.chs.app_jetpack.camera.RecordView;
import com.chs.app_jetpack.ui.home.HomeFragment;
import com.chs.app_jetpack.ui.home.HomeViewModel;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * author：chs
 * date：2020/5/21
 * des：
 */
public class CameraActivity extends AppCompatActivity {
    private static final String[] PERMISSIONS = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO};
    private static final int  PERMISSIONS_REQUEST_CODE = 10;
    private ArrayList<String> deniedPermission = new ArrayList<>();
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

    private static final double RATIO_4_3_VALUE = 4.0 / 3.0;
    private static final double  RATIO_16_9_VALUE = 16.0 / 9.0;
    private PreviewView mPreviewView;
    private RecordView mRecordView;
    private Preview mPreview;
    private Camera mCamera;
    /**
     * 照相
     */
    private ImageCapture mImageCapture;
    /**
     * 录制视频
     */
    private VideoCapture mVideoCapture;
    /**
     * 可以将一个camera跟任意的LifecycleOwner绑定的一个单例类
     */
    private ProcessCameraProvider mCameraProvider;
    /**
     * 摄像头朝向 默认向后
     */
    private int mLensFacing = CameraSelector.LENS_FACING_BACK;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        if(!hsaPermission(this)){
            ActivityCompat.requestPermissions(this,PERMISSIONS,PERMISSIONS_REQUEST_CODE);
        }else {
            setUpCamera();
        }
        mPreviewView = findViewById(R.id.view_finder);
        mRecordView = findViewById(R.id.record_view);
        updateCameraUi();
        mRecordView.setOnRecordListener(new RecordView.OnRecordListener() {
            @Override
            public void onTackPicture() {
                //拍照

            }

            @Override
            public void onRecordVideo() {
                //视频
            }

            @Override
            public void onFinish() {
                //录制完成
            }
        });
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

                    CameraSelector cameraSelector = new CameraSelector.Builder().requireLensFacing(mLensFacing).build();

                    mPreview = new Preview.Builder()
                            //设置宽高比
                            .setTargetAspectRatio(screenAspectRatio)
                            //设置当前旋转
                            .setTargetRotation(rotation)
                            .build();
                    mImageCapture = new ImageCapture.Builder()
                            //优化捕获速度，可能降低图片质量
                            .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                            //设置宽高比
                            .setTargetAspectRatio(screenAspectRatio)
                            //设置当前旋转
                            .setTargetRotation(rotation)
                            .build();

                    //重新绑定之前必须先取消绑定
                    mCameraProvider.unbindAll();

                    mCamera = mCameraProvider.bindToLifecycle(CameraActivity.this,
                            cameraSelector,mPreview,mImageCapture);
                    if(mCamera!=null){
                        mPreview.setSurfaceProvider(mPreviewView.createSurfaceProvider(mCamera.getCameraInfo()));
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, ContextCompat.getMainExecutor(this));
    }

    private int aspectRatio(int widthPixels, int heightPixels) {
        Double previewRatio = Double.valueOf(Math.max(widthPixels,heightPixels) / Math.min(widthPixels,heightPixels));
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

}
