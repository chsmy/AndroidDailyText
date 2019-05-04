package com.chs.androiddailytext.netease

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chs.androiddailytext.R
import kotlinx.android.synthetic.main.activity_pathmeasure.*

/**
 * author：chs
 * date：2019/3/30
 * des：
 */
class PathMeasureActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pathmeasure)

        button.setOnClickListener {
            measure_dui.reset()
            measure_cha.reset()
        }

    }
}