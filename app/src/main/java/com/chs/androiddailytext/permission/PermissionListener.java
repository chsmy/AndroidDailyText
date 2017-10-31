package com.chs.androiddailytext.permission;

import java.util.List;

/**
 * 作者：chs on 2017-10-31 15:16
 * 邮箱：657083984@qq.com
 * 权限请求的回调接口
 */

public interface PermissionListener {
    //权限请求成功
    void onPermissionsGranted(int requestCode, List<String> perms);

    //权限请求被拒绝
    void onPermissionsDenied(int requestCode, List<String> perms);
}
