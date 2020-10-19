package com.chs.androiddailytext.changeSkin;

import android.content.Context;

import com.blankj.utilcode.util.SPUtils;
import com.chs.androiddailytext.R;

/**
 * @author: chs
 * @date: Create in 2020/10/19
 * @description 主题切换工具类
 */
public class ThemeUtil {

    public static final String THEME_TYPE_STR = "themeType";
    public static final int THEME_NORMAL = 1;
    public static final int THEME_BLACK = 2;

    public static int sCurrentTheme = -1;

    public static int getThemeType(){
        if(sCurrentTheme != -1){
            return sCurrentTheme;
        }else {
            int themeType = SPUtils.getInstance().getInt(THEME_TYPE_STR,1);
            sCurrentTheme = themeType;
            return themeType;
        }
    }

    /**
     * 设置初始的主题
     * @param context
     */
    public static void setBaseTheme(Context context) {
        int themeType = SPUtils.getInstance().getInt(THEME_TYPE_STR,1);
        int themeId;
        if (themeType == THEME_NORMAL) {
            themeId = R.style.LightStyle;
        } else {
            themeId = R.style.BlackStyle;
        }
        context.setTheme(themeId);
    }

    /**
     * 保存设置新主题
     * @param context
     * @param theme THEME_NORMAL or THEME_BLACK
     */
    public static void setNewTheme(Context context,int theme){
        sCurrentTheme = theme;
        SPUtils.getInstance().put(THEME_TYPE_STR,theme);
        context.setTheme(theme);
    }


}
