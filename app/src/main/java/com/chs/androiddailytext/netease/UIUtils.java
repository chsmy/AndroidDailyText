package com.chs.androiddailytext.netease;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * @author chs
 * date：2019-04-30 15:11
 * des：屏幕适配工具
 */
public class UIUtils {

    private static UIUtils instance;

    public static float displayMetricsWidth;
    public static float displayMetricsHeight;
    public static float systemBarHeight;

    /**
     * 缩放标准
     */
    public static final float STANDARD_WIDTH=1080f;
    public static final float STANDARD_HEIGHT=1920f;

    public static UIUtils getInstance(Context context) {
        if (instance == null) {
            synchronized (UIUtils.class) {
                if (instance == null) {
                    instance = new UIUtils(context);
                }
            }
        }
        return instance;
    }

    /**
     * 屏幕切换的时候调用
     * @return
     */
    public static UIUtils notityInstance(Context context){
        instance=new UIUtils(context);
        return instance;
    }

    public static UIUtils getInstance() {
        if (instance == null) {
            throw new RuntimeException("UiUtil应该先调用含有构造方法进行初始化");
        }
        return instance;
    }

    private UIUtils(Context context) {
        //计算缩放系数
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        if (displayMetricsWidth == 0.0f || displayMetricsHeight == 0.0f) {
            windowManager.getDefaultDisplay().getMetrics(metrics);
            //状态栏
            systemBarHeight = getStatusBarHeight();
            //横屏的时候宽大于高
            if(metrics.widthPixels>metrics.heightPixels){
                displayMetricsWidth = metrics.heightPixels;
                displayMetricsHeight = metrics.widthPixels - systemBarHeight;
            }else {
                //竖屏
                displayMetricsHeight = metrics.heightPixels - systemBarHeight;
                displayMetricsWidth = metrics.widthPixels;
            }
        }
    }

    /**
     * 横向缩放系数
     * @return
     */
    public float getHorizontalScaleValue(){
        return displayMetricsWidth/STANDARD_WIDTH;
    }

    /**
     * 纵向缩放系数
     * @return
     */
    public float getVerticalScaleValue(){
        return ((float)(displayMetricsHeight))/(STANDARD_HEIGHT-systemBarHeight);
    }

    public int getWidth(int width) {
        return Math.round((float)width * displayMetricsWidth / STANDARD_WIDTH);
    }
    public int getHeight(int height) {
        return Math.round((float)height * displayMetricsHeight / (STANDARD_HEIGHT-systemBarHeight));
    }

    /**
     * 状态栏高度
     * @return
     */
    private static int getStatusBarHeight() {
        Resources resources = Resources.getSystem();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int dimensionPixelSize = resources.getDimensionPixelSize(resourceId);
        return dimensionPixelSize==0?48:dimensionPixelSize;
    }
}
