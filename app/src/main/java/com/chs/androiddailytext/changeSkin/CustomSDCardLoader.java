package com.chs.androiddailytext.changeSkin;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;

import skin.support.load.SkinSDCardLoader;

public class CustomSDCardLoader extends SkinSDCardLoader {
    public static final int SKIN_LOADER_STRATEGY_SDCARD = Integer.MAX_VALUE;

    @Override
    protected String getSkinPath(Context context, String skinName) {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "hellouf"+ File.separator;
        Log.i("path",path);
        return new File(path, skinName).getAbsolutePath();
    }

    @Override
    public int getType() {
        return SKIN_LOADER_STRATEGY_SDCARD;
    }
}
