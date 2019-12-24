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
    }
}
