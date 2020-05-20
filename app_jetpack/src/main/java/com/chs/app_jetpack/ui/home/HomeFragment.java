package com.chs.app_jetpack.ui.home;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.AspectRatio;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraInfoUnavailableException;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Preview;
import androidx.camera.core.VideoCapture;
import androidx.camera.core.impl.VideoCaptureConfig;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.chs.app_jetpack.R;
import com.chs.app_jetpack.camera.RecordView;
import com.chs.lib_navannotation.FragmentDestination;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

@FragmentDestination(pageUrl = "main/tabs/home",asStarter = true)
public class HomeFragment extends Fragment {
    private static final double RATIO_4_3_VALUE = 4.0 / 3.0;
    private static final double  RATIO_16_9_VALUE = 16.0 / 9.0;
    private HomeViewModel homeViewModel;
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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPreviewView = view.findViewById(R.id.view_finder);
        mRecordView = view.findViewById(R.id.record_view);
        updateCameraUi();
        setUpCamera();
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

    private void updateCameraUi() {
        //必须先remove在add这样视频流画面才能正确的显示出来
        ViewGroup parent = (ViewGroup) mPreviewView.getParent();
        parent.removeView(mPreviewView);
        parent.addView(mPreviewView,0);
    }

    private void setUpCamera() {
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture =
                ProcessCameraProvider.getInstance(requireContext());
        cameraProviderFuture.addListener(new Runnable() {
            @Override
            public void run() {
                try {
                    mCameraProvider = cameraProviderFuture.get();
                    //选择摄像头的朝向
                    mLensFacing = getLensFacing();
                    if(mLensFacing == -1){
                        Toast.makeText(requireContext(),"无可用的设备cameraId!,请检查设备的相机是否被占用",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //获取屏幕的分辨率
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    mPreviewView.getDisplay().getRealMetrics(displayMetrics);
                   //获取宽高比
                    int screenAspectRatio = aspectRatio(displayMetrics.widthPixels, displayMetrics.heightPixels);

                    int rotation = mPreviewView.getDisplay().getRotation();

                    if(mCameraProvider == null){
                        Toast.makeText(requireContext(),"相机初始化失败",Toast.LENGTH_SHORT).show();
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

                    mCamera = mCameraProvider.bindToLifecycle(HomeFragment.this,
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
        }, ContextCompat.getMainExecutor(requireContext()));
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
