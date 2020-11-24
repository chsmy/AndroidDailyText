package com.chs.androiddailytext.utils;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;

import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.SizeUtils;

/**
 * @author: chs
 * @date: Create in 2020/9/11
 * @description 用来自定义各种圆角的drawable
 */
public class DrawableUtil {

    /**
     * 圆角背景
     *
     * @param colorId    背景颜色
     * @param radiusSize 圆角大小
     * @return
     */
    public static GradientDrawable getRoundDrawable(Context context, int colorId, int radiusSize) {
        GradientDrawable drawable = new GradientDrawable();
        float radius = SizeUtils.dp2px(radiusSize);
        drawable.setCornerRadius(radius);
        drawable.setColor(ContextCompat.getColor(context, colorId));
        return drawable;
    }

    /**
     * 圆角背景
     *
     * @param radiusSize 圆角大小
     * @return
     */
    public static GradientDrawable getRoundStrokeDrawable(Context context,
                                                          int bgColorId, int StrokeColor,
                                                          int radiusSize, float strokeWidth) {
        GradientDrawable drawable = new GradientDrawable();
        float radius = SizeUtils.dp2px(radiusSize);
        drawable.setCornerRadius(radius);
        drawable.setStroke(SizeUtils.dp2px(strokeWidth), ContextCompat.getColor(context, StrokeColor));
        drawable.setColor(ContextCompat.getColor(context, bgColorId));
        return drawable;
    }

    /**
     * 上边有圆角
     *
     * @return
     * @radius 单位dp
     */
    public static GradientDrawable getDrawableTop(Context context, int colorId, float radiusDp) {
        GradientDrawable drawable = new GradientDrawable();
        float radiusPix = SizeUtils.dp2px(radiusDp);
        drawable.setCornerRadii(new float[]{radiusPix, radiusPix, radiusPix, radiusPix, 0, 0, 0, 0});
        drawable.setColor(ContextCompat.getColor(context, colorId));
        return drawable;
    }

    /**
     * 获取有圆角的drawable
     *
     * @return
     * @radius 单位dp
     */
    public static GradientDrawable getDrawableWithCorners(Context context, int colorId
            , int leftTop, int rightTop, int leftBottom, int rightBottom) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadii(new float[]{
                SizeUtils.dp2px(leftTop), SizeUtils.dp2px(leftTop),
                SizeUtils.dp2px(rightTop), SizeUtils.dp2px(rightTop),
                SizeUtils.dp2px(rightBottom), SizeUtils.dp2px(rightBottom),
                SizeUtils.dp2px(leftBottom), SizeUtils.dp2px(leftBottom),});
        drawable.setColor(ContextCompat.getColor(context, colorId));
        return drawable;
    }

    /**
     * 获取有圆角 有stroke 的drawable
     *
     * @param
     * @return
     */
    public static GradientDrawable getRoundDrawableWithStrokeAndCorners
                   (Context context, int bgColorId, int StrokeColor, float strokeWidth, int leftTop,
                    int rightTop, int leftBottom, int rightBottom) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadii(new float[]{
                SizeUtils.dp2px(leftTop), SizeUtils.dp2px(leftTop),
                SizeUtils.dp2px(rightTop), SizeUtils.dp2px(rightTop),
                SizeUtils.dp2px(rightBottom), SizeUtils.dp2px(rightBottom),
                SizeUtils.dp2px(leftBottom), SizeUtils.dp2px(leftBottom)});
        drawable.setStroke(SizeUtils.dp2px(strokeWidth), ContextCompat.getColor(context, StrokeColor));
        drawable.setColor(ContextCompat.getColor(context, bgColorId));
        return drawable;
    }

    /**
     * 从上到下的渐变
     * @return
     */
    public static GradientDrawable getGradientDrawable(Context context,
                                                       int startColorId,int endColorId){
        int[] colors = {ContextCompat.getColor(context,startColorId), ContextCompat.getColor(context,endColorId)};
        GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,colors);
        drawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        return drawable;
    }
}
