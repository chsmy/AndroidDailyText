package com.chs.androiddailytext;

import android.app.Application;

import com.chs.androiddailytext.changeSkin.CustomSDCardLoader;

import androidx.appcompat.app.AppCompatDelegate;
import skin.support.SkinCompatManager;

/**
 * 作者：chs on 2018-02-24 14:04
 * 邮箱：657083984@qq.com
 */

public class APP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SkinCompatManager.withoutActivity(this)
                .addStrategy(new CustomSDCardLoader()).
        loadSkin();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
}
