package com.chs.androiddailytext.netease

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.chs.androiddailytext.R

/**
 *  @author chs
 *  date：2019-04-01 14:56
 *  des：
 */
class ScreenActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ScreenAdapter.adapter(this)
        setContentView(R.layout.activity_screen)
    }
}