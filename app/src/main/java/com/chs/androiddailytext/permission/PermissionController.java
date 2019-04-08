package com.chs.androiddailytext.permission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Build;

import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

/**
 * 作者：chs on 2017-10-31 15:28
 * 邮箱：657083984@qq.com
 */

public class PermissionController {

    static final String TAG = "PermissionController";

    private PermissionFragment mPermissionFragment;

    private PermissionListener mPermissionListener;

    private Activity mActivity;

    public PermissionController(@NonNull Activity activity) {
        mActivity = activity;
        mPermissionFragment = getPermissionFragment(activity);
    }
    private PermissionFragment getPermissionFragment(Activity activity) {
        PermissionFragment permissionsFragment = (PermissionFragment) activity.getFragmentManager().findFragmentByTag(TAG);
        boolean isNewInstance = permissionsFragment == null;
        if (isNewInstance) {
            permissionsFragment = new PermissionFragment();
            FragmentManager fragmentManager = activity.getFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .add(permissionsFragment, TAG)
                    .commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
        return permissionsFragment;
    }

    public PermissionController listener(PermissionListener listener){
        mPermissionListener = listener;
        return this;
    }
    /**
     *是否有用户拒绝的权限需要再次请求
     * @param perms
     * @return
     */
    public boolean shouldShowRationale(@NonNull String... perms) {
        for (String perm : perms) {
            if (mPermissionFragment.shouldShowRequestPermissionRationale(perm)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 请求权限
     * rationale 用户拒绝一次权限，再次申请时先征求用户同意，再打开授权对话框； 这样避免用户勾选不再提示，导致以后无法申请权限。
     * @param requestCode
     * @param perms
     */
    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissions(int requestCode, @NonNull String... perms) {
//        if (MyPermission.hasPermissions(mActivity, perms)) {
//            //所请求的权限都有了 直接返回确定
//            mPermissionListener.onPermissionsGranted(requestCode, Arrays.asList(perms));
//        }else {
            if(shouldShowRationale(perms)){
                //用户有拒绝的权限需要重新请求  这里自定义dialog 让用户重新设置
                setDialog(requestCode,perms);
            }else {
                //直接请求
                mPermissionFragment.requestPermissions(perms,requestCode,mPermissionListener);
            }
//        }
    }

    private void setDialog(final int requestCode, @NonNull final String... perms) {
        new AlertDialog.Builder(mActivity)
                .setCancelable(false)
                .setTitle("权限请求失败")
                .setMessage("我们需要此请求干什么")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPermissionFragment.requestPermissions(perms,requestCode,mPermissionListener);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPermissionListener.onPermissionsDenied(requestCode, Arrays.asList(perms));
                    }
                }).create().show();
    }
}
