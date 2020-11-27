package com.chs.androiddailytext.permission;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import com.chs.androiddailytext.R;
import com.chs.androiddailytext.utils.AppToast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class PermissionActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_PERMISSION_CALL_PHONE = 100;
    private static final int REQUEST_CODE_PERMISSION_CAMERA = 101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

    }

    public void callPhone(View view) {
        boolean b = MyPermission.hasPermissions(this, Manifest.permission.CALL_PHONE);

        if(MyPermission.hasPermissions(this,Manifest.permission.CALL_PHONE)){
            callPhone();
        }else {
            MyPermission.with(this)
                    .listener(new PermissionListener() {
                        @Override
                        public void onPermissionsGranted(int requestCode, List<String> perms) {
                            if(requestCode == REQUEST_CODE_PERMISSION_CALL_PHONE){
                                callPhone();
                            }
                        }
                        @Override
                        public void onPermissionsDenied(int requestCode, List<String> perms) {
                         AppToast.show(PermissionActivity.this,"请求被拒绝");
                        }
                    })
                    .requestPermissions(REQUEST_CODE_PERMISSION_CALL_PHONE,Manifest.permission.CALL_PHONE);
        }

    }

    private void callPhone(){
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:"+"13161083183");
        intent.setData(data);
        startActivity(intent);
    }

    public void camera(View view) {
        if(MyPermission.hasPermissions(this,Manifest.permission.CAMERA)){
            AppToast.show(PermissionActivity.this,"有权限啦");
        }else {
            MyPermission.with(this)
                    .listener(new PermissionListener() {
                        @Override
                        public void onPermissionsGranted(int requestCode, List<String> perms) {
                            if(requestCode == REQUEST_CODE_PERMISSION_CAMERA){
                                AppToast.show(PermissionActivity.this,"请求权限成功");
                            }
                        }
                        @Override
                        public void onPermissionsDenied(int requestCode, List<String> perms) {
                            AppToast.show(PermissionActivity.this,"请求被拒绝");
                            if(MyPermission.somePermissionPermanentlyDenied(PermissionActivity.this,perms)){
                                setDialog(requestCode,perms);
                            }
                        }
                    })
                    .requestPermissions(REQUEST_CODE_PERMISSION_CAMERA,Manifest.permission.CAMERA);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    private static final int APP_SETTINGS_RC = 7534;
    private void setDialog(final int requestCode, @NonNull final List<String> perms) {
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle("权限请求失败")
                .setMessage("我们需要此请求干什么")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivityForResult(
                                new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                                        .setData(Uri.fromParts("package", getPackageName(), null)),
                                APP_SETTINGS_RC);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).create().show();
    }

    public void jump(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("ylyk://selfcheck?type=1&id=10"));
        startActivity(intent);
    }
}
