package com.chs.androiddailytext.myanimator;

import android.animation.TimeInterpolator;
import android.view.View;

import java.lang.ref.WeakReference;

/**
 * @author chs
 * date：2019-04-11 15:54
 * des：
 */
public class MyObjectAnimator implements VSYNCManager.AnimatorFrameCallBack{

    private long mDuration = 0;
    private TimeInterpolator interpolator;
    private MyFloatPropertyValuesHolder mPropertyValuesHolder;
    /**
     * View是个比较重量级的对象，放到WeakReference中方便回收
     */
    private WeakReference<View> target;

    private Long mStartTime = -1L;
    /**
     * 执行到哪里
     */
    private float index = 0;

    public void setDuration(long duration) {
        mDuration = duration;
    }
    public void setInterpolator(TimeInterpolator interpolator) {
        this.interpolator = interpolator;
    }

    private MyObjectAnimator(View view,String propertyName, float... values){
        target = new WeakReference<>(view);
        mPropertyValuesHolder = new MyFloatPropertyValuesHolder(propertyName,values);
    }

    public static MyObjectAnimator ofFloat(View view,String propertyName, float... values){
       return new MyObjectAnimator(view,propertyName,values);
    }


    public void start() {
        mPropertyValuesHolder.setupSetter(target);
        mStartTime = System.currentTimeMillis();
        VSYNCManager.getInstance().add(this);
    }

    @Override
    public void doAnimator(long currentTime) {
        float total= mDuration / 16;
        //执行的百分比
        float fraction = (index++)/total;

        //通过插值器，改变百分比的值
        if(interpolator != null){
            interpolator.getInterpolation(fraction);
        }
        //循环播放
        if(index>=total){
            index = 0;
        }
        mPropertyValuesHolder.setAnimatedValue(target.get(),fraction);
    }
}
