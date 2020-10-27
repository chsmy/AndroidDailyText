package com.chs.app_jetpack

import android.app.Application
import com.chs.app_jetpack.hilt.HiltSimple
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

/**
 * @author: chs
 * @date: Create in 2020/10/27
 * @description
 */
@HiltAndroidApp
class App : Application() {

    /**
     * 在使用Hilt的时候 必须在项目的application上添加 @HiltAndroidApp注解
     * 这个注解会出发Hilt生成各种代码
     */

    @Inject
    lateinit var mHiltSimple: HiltSimple

    override fun onCreate() {
        super.onCreate()
        mHiltSimple.doSomething()
    }
}