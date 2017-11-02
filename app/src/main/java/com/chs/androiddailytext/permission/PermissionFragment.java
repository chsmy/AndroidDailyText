package com.chs.androiddailytext.permission;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：chs on 2017-10-31 14:33
 * 邮箱：657083984@qq.com
 */

public class PermissionFragment extends Fragment {

    private PermissionListener mPermissionListener;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean shouldShowRequestPermissionRationale(@NonNull String permission) {
        return super.shouldShowRequestPermissionRationale(permission);
    }


    @TargetApi(Build.VERSION_CODES.M)
    void requestPermissions(@NonNull String[] permissions, int requestCode,PermissionListener listener) {
        mPermissionListener = listener;
        requestPermissions(permissions, requestCode);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        List<String> granted = new ArrayList<>();
        List<String> denied = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            String perm = permissions[i];
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                granted.add(perm);
            } else {
                denied.add(perm);
            }
        }

        if (!granted.isEmpty() && mPermissionListener!=null) {
            mPermissionListener.onPermissionsGranted(requestCode,granted);
        }
        if(!denied.isEmpty() && mPermissionListener!=null){
            mPermissionListener.onPermissionsDenied(requestCode,denied);
        }
    }
}
