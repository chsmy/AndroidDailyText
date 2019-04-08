package com.chs.androiddailytext.netease

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import android.view.ViewGroup
import android.widget.TextView
import com.chs.androiddailytext.R
import kotlinx.android.synthetic.main.activity_flowlayout.*

/**
 * author：chs
 * date：2019/4/7
 * des：
 */
class FlowLayoutActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flowlayout)
        var datas = mutableListOf<String>()
        datas.add("刮刮卡")
        datas.add("粒子效果")
        datas.add("splash")
        datas.add("QQ红点拖拽")
        datas.add("PathMeasure")
        datas.add("屏幕适配")
        datas.add("刘海适配")
        datas.add("刘海适配")
        datas.add("FlowLayout")
        datas.add("FlowLayout")

        for (str in datas){
            val textview = TextView(this)
            val lp = ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
            lp.setMargins(15,15,15,15)
            textview.layoutParams = lp
            textview.text = str
            textview.textSize = 20f
            textview.background = ContextCompat.getDrawable(this,R.drawable.text_bg)

            flow_layout.addView(textview)
        }

    }
}