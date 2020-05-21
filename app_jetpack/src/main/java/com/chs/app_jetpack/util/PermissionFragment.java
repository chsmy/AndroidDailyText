package com.chs.app_jetpack.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

/**
 * author：chs
 * date：2020/5/21
 * des：
 */
public class PermissionFragment extends Fragment {

    private static final String[] PERMISSIONS = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO};
    private static final int  PERMISSIONS_REQUEST_CODE = 10;
    private ArrayList<String> deniedPermission = new ArrayList<>();
    public static boolean hsaPermission(Context context){
        for (String permission : PERMISSIONS) {
            boolean res = ContextCompat.checkSelfPermission(context,permission) == PackageManager.PERMISSION_DENIED;
            if(!res){
                return false;
            }
        }
        return true;
    }

    public static PermissionFragment newInstance() {
        Bundle args = new Bundle();
        PermissionFragment fragment = new PermissionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!hsaPermission(requireContext())){
            requestPermissions(PERMISSIONS,PERMISSIONS_REQUEST_CODE);
        }else {

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
