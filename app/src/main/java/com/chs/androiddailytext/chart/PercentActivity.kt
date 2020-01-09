package com.chs.androiddailytext.chart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chs.androiddailytext.R
import kotlinx.android.synthetic.main.activity_percent.*

class PercentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_percent)

        percent.setScales(0.6f)

        percent1.setScales(0.6f)
        percent1.setGradient(true)

        circle_percent.setPercentage(50f)

        circle_percent1.setPercentage(50f)
        circle_percent1.setGradient(false)
    }
}
