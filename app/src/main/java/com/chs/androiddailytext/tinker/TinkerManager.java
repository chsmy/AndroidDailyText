package com.chs.androiddailytext.tinker;

import android.content.Context;

import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.ApplicationLike;

/**
 * @author chs
 * date：2019-05-05 15:04
 * des： 管理tinker
 */
public class TinkerManager {

    private static boolean isInstalled = false;
    private static ApplicationLike mAppLike;
    public static void installTinker(ApplicationLike applicationLike){
        mAppLike = applicationLike;
        if(isInstalled){
            return;
        }
        TinkerInstaller.install(applicationLike);
        isInstalled = true;
    }

    public static void loadPath(String path){
       if(Tinker.isTinkerInstalled()){
         TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(),path);
       }
    }

    public static Context getApplicationContext(){
        if(mAppLike!=null){
            return mAppLike.getApplication().getApplicationContext();
        }
        return null;
    }

}
