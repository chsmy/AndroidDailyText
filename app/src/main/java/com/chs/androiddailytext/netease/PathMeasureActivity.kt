package com.chs.androiddailytext.netease

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

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