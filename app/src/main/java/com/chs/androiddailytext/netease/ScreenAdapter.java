package com.chs.androiddailytext.netease;

import android.app.Activity;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * @author chs
 * date：2019-04-01 10:08
 * des：
 */
public class ScreenAdapter {

    /**
     * 参考设计图的宽 单位dp
     * 比如设计图是1920*1080 按360dp为基准
     * 相同分辨率的手机，屏幕的尺寸不同也会导致最后的dp值不同。
     * 比如1920*1080分辨率，屏幕尺寸为5，最后是392.7dp
     */
    private static  float WIDTH = 320;


    public static void adapter(Activity activity){
        //系统的DisplayMetrics
        final DisplayMetrics systemDM = Resources.getSystem().getDisplayMetrics();

        //根据设计图的WIDTH计算当前的density, scaleDensity, densityDpi
        float targetDensity = systemDM.widthPixels/WIDTH;
        float targetScaleDensity = targetDensity *(systemDM.scaledDensity/systemDM.density);
        //px = density * dp;  density = dpi / 160;  px = dp * (dpi / 160);
        int targetDensityDpi = (int) (targetDensity*160);

        //替换Activity的density, scaleDensity, densityDpi
        //Activity的DisplayMetrics
        final  DisplayMetrics activityDM = activity.getResources().getDisplayMetrics();
        activityDM.density = targetDensity;
        activityDM.scaledDensity = targetScaleDensity;
        activityDM.densityDpi = targetDensityDpi;
    }

    public static void cancelAdapter(final Activity activity) {
        final DisplayMetrics systemDm = Resources.getSystem().getDisplayMetrics();
        final DisplayMetrics activityDm = activity.getResources().getDisplayMetrics();
        activityDm.density = systemDm.density;
        activityDm.scaledDensity = systemDm.scaledDensity;
        activityDm.densityDpi = systemDm.densityDpi;
    }
    public static void adaptWidth(Activity activity, int designWidth) {
        DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
        final  DisplayMetrics activityDM = activity.getResources().getDisplayMetrics();
        activityDM.xdpi= dm.xdpi = (dm.widthPixels * 72f) / designWidth;
    }

    public static void cancelPtAdapter(final Activity activity) {
        final DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
        final DisplayMetrics activityDm = activity.getResources().getDisplayMetrics();
        activityDm.xdpi = dm.xdpi = dm.density * 72;
    }

}
