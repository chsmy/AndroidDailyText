package com.chs.androiddailytext.jetpack

import android.content.Context
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 *  作者：chs
 *  时间：2019-03-25 19:52
 *  描述：
 */

class MyLocationListener(private val context: Context,
                         private val callback: (Location) -> Unit) : LifecycleObserver{


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() {
        // 连接到系统位置服务
        Log.i("LifecycleEvent","start")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() {
        // 断开连接
        Log.i("LifecycleEvent","stop")
    }
}
class MyActivity : AppCompatActivity() {

    lateinit var myLocationListener:MyLocationListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        myLocationListener = MyLocationListener(this){ _ ->
             //更新UI
        }
        lifecycle.addObserver(myLocationListener)
    }
}