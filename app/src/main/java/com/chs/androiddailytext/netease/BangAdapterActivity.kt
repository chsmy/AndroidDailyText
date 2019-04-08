package com.chs.androiddailytext.netease

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.DisplayCutout
import android.view.Window
import android.view.WindowManager
import com.chs.androiddailytext.R


/**
 *  @author chs
 *  date：2019-04-02 09:56
 *  des：
 */
class BangAdapterActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //1.设置全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        val window = window
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        if(hasDisplayCutout(window)){
            //2.让内容区域延伸进刘海
            val params = window.attributes
            /**
             *  * @see #LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT 全屏模式，内容下移，非全屏不受影响
             *  * @see #LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES 允许内容去延伸进刘海区
             *  * @see #LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER 不允许内容延伸进刘海区
             */
            params.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            window.attributes = params

        }
//        //3.设置成沉浸式
//        val flags = SYSTEM_UI_FLAG_FULLSCREEN or SYSTEM_UI_FLAG_HIDE_NAVIGATION or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//        var visibility = window.decorView.systemUiVisibility
//        visibility = visibility or flags //追加沉浸式设置
//        window.decorView.systemUiVisibility = visibility

        setContentView(R.layout.activity_bang)

//        root_view.setPadding(0,getStatusBarHeight(this),0,0)

    }

    /**
     * 判断是否有刘海
     */
    @TargetApi(28)
    private fun hasDisplayCutout(window: Window): Boolean {
        val displayCutout: DisplayCutout?
        val rootView = window.decorView
        val insets = rootView.rootWindowInsets
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P && insets != null) {
            displayCutout = insets.displayCutout
            if (displayCutout != null) {
                if (displayCutout.boundingRects != null && displayCutout.boundingRects.size > 0 && displayCutout.safeInsetTop > 0) {
                    return true
                }
            }
        }
        return false
    }

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    fun getStatusBarHeight(context: Context): Int {
        var statusBarHeight = 0
        val resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId)
        }
        return statusBarHeight
    }

}