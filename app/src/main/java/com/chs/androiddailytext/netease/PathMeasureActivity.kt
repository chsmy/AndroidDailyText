package com.chs.androiddailytext.netease

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * author：chs
 * date：2019/3/30
 * des：
 */
class PathMeasureActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(PathMeasureView(this))
    }
}