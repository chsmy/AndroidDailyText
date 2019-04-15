package com.chs.androiddailytext.jetpack;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * @author chs
 * date：2019-04-15 14:11
 * des：
 */
public class MyListener implements LifecycleObserver{
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void connectListener() {
        // 连接到服务
        Log.i("LifecycleEvent","start");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void disconnectListener() {
        // 断开连接
        Log.i("LifecycleEvent","stop");
    }
}
