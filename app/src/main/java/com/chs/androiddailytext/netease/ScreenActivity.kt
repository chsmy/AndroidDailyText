package com.chs.androiddailytext.netease

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.chs.androiddailytext.R
import com.chs.androiddailytext.utils.DensityUtil
import kotlinx.android.synthetic.main.activity_screen.*

/**
 *  @author chs
 *  date：2019-04-01 14:56
 *  des：
 */
class ScreenActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        ScreenAdapter.adapter(this)
        setContentView(R.layout.activity_screen)

        UIUtils.getInstance(this)

        Log.i("160dp",""+DensityUtil.dip2px(this,160f))

        ViewCalculateUtil.setViewLayoutParam(tv_3,540,540,0,0,0,0)
        ViewCalculateUtil.setViewLayoutParam(tv_4,540,540,0,0,0,0)

    }
}