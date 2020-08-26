package com.chs.app_video

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager

/**
 * author：chs
 * date：2020/7/3
 * des：
 */
class AppUtil {

    companion object{
        private var sApplication : Application? = null
        private val UTIL_HANDLER = Handler(Looper.getMainLooper())

        fun init(app:Application){
            sApplication = app
            if(sApplication == null){
                sApplication = getApplicationByReflect()
            }
        }
        @JvmStatic
        fun getApp() : Application{
            if(sApplication != null){
                return sApplication!!
            }
            val app = getApplicationByReflect()
            sApplication = app
            return sApplication!!
        }

        @SuppressLint("PrivateApi")
        private fun getApplicationByReflect(): Application {
            return try {
                val app =
                    Class.forName("android.app.ActivityThread")
                        .getMethod("currentApplication")
                        .invoke(null) as Application
                app
            }catch (e:Exception){
                e.printStackTrace()
                throw NullPointerException("you should init first")
            }
        }

        @JvmStatic
        fun runOnUiThread(runnable: Runnable) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                runnable.run()
            } else {
                UTIL_HANDLER.post(runnable)
            }
        }
        @JvmStatic
        fun fixSoftInputLeaks(window: Window) {
            val imm =
                getApp()
                    .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    ?: return
            val leakViews = arrayOf(
                "mLastSrvView",
                "mCurRootView",
                "mServedView",
                "mNextServedView"
            )
            for (leakView in leakViews) {
                try {
                    val leakViewField =
                        InputMethodManager::class.java.getDeclaredField(
                            leakView
                        )
                            ?: continue
                    if (!leakViewField.isAccessible) {
                        leakViewField.isAccessible = true
                    }
                    val obj = leakViewField[imm] as? View ?: continue
                    if (obj.rootView === window.decorView.rootView) {
                        leakViewField[imm] = null
                    }
                } catch (ignore: Throwable) { /**/
                }
            }
        }

    }

}