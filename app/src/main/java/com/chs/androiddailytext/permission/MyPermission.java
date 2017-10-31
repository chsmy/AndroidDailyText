package com.chs.androiddailytext.permission;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.util.List;

/**
 * 作者：chs on 2017-10-31 11:09
 * 邮箱：657083984@qq.com
 */

public class MyPermission {
    static final String TAG = "RxPermissions";

    /**
     * 检查是否有权限
     * @param context
     * @param perms
     * @return
     */
    public static boolean hasPermissions(Context context, @NonNull String... perms) {
        // 安卓权限机制从6.0开始  小于6.0的直接返回true
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            Log.w(TAG, "hasPermissions: API version < M, returning true by default");
            return true;
        }

        if (context == null) {
            throw new IllegalArgumentException("Can't check permissions for null context");
        }

        for (String perm : perms) {
            if (ContextCompat.checkSelfPermission(context, perm)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }

        return true;
    }

    /**
     * 用户拒绝了权限  小米手机上shouldShowRequestPermissionRationale 总是返回false
     * @param activity
     * @param perms
     * @return
     */
    public static boolean somePermissionPermanentlyDenied(Activity activity, @NonNull List<String> perms) {
        if(Build.VERSION.SDK_INT < 23) {
            return false;
        }
        for (String deniedPermission : perms) {
            if (!activity.shouldShowRequestPermissionRationale(deniedPermission)) {
                return true;
            }
        }
        return false;
    }

    @NonNull
    public static PermissionController with(@NonNull Activity activity) {
        return new PermissionController(activity);
    }
}
