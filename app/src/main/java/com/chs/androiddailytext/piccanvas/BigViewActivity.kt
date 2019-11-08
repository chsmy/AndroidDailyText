package com.chs.androiddailytext.piccanvas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chs.androiddailytext.R
import kotlinx.android.synthetic.main.activity_big_view.*

class BigViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_big_view)
        val inputStream = resources.assets.open("qmsht.png")
        bigview.setImage(inputStream)
    }
}
