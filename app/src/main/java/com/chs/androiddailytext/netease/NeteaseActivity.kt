package com.chs.androiddailytext.netease

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import android.view.View
import com.chs.androiddailytext.R
import kotlinx.android.synthetic.main.activity_netease.*

/**
 *  作者：chs
 *  时间：2019-03-26 10:17
 *  描述：
 */
class NeteaseActivity : AppCompatActivity() {

    var datas = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_netease)
        datas.add("刮刮卡")
        datas.add("粒子效果")
        datas.add("splash")
        datas.add("QQ红点拖拽")
        datas.add("PathMeasure")
        datas.add("屏幕适配")
        datas.add("刘海适配")
        datas.add("FlowLayout")
        recycleview.layoutManager = GridLayoutManager(this, 3)
//        recycleview.addItemDecoration(DividerItemDecoration(this, HORIZONTAL))
        val adapter = NeteaseAdapter(datas, this)
        recycleview.adapter = adapter
        adapter.setOnItemClickListener(object : NeteaseAdapter.OnItemClickListenter {
            override fun onItemClick(view: View, position: Int) {
                when (position) {
                    0 -> startActivity(XfermodesActivity::class.java)
                    1 -> startActivity(SplitActivity::class.java)
                    2 -> startActivity(SplashActivity::class.java)
                    3 -> startActivity(QQBubbleActivity::class.java)
                    4 -> startActivity(PathMeasureActivity::class.java)
                    5 -> startActivity(ScreenActivity::class.java)
                    6 -> startActivity(BangAdapterActivity::class.java)
                    7 -> startActivity(FlowLayoutActivity::class.java)
                }
            }
        })

    }

    private fun startActivity(clazz: Class<*>) {
        val intent = Intent(this, clazz)
        startActivity(intent)
    }
}