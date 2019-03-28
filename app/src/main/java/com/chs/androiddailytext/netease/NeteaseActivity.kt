package com.chs.androiddailytext.netease

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.DividerItemDecoration.HORIZONTAL
import android.support.v7.widget.GridLayoutManager
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
        recycleview.layoutManager = GridLayoutManager(this, 3)
        recycleview.addItemDecoration(DividerItemDecoration(this, HORIZONTAL))
        val adapter = NeteaseAdapter(datas, this)
        recycleview.adapter = adapter
        adapter.setOnItemClickListener(object : NeteaseAdapter.OnItemClickListenter {
            override fun onItemClick(view: View, position: Int) {
                when (position) {
                    0 -> startActivity(XfermodesActivity::class.java)
                    1 -> startActivity(SplitActivity::class.java)
                    2 -> startActivity(SplashActivity::class.java)
                    3 -> startActivity(QQBubbleActivity::class.java)
                }
            }
        })

    }

    private fun startActivity(clazz: Class<*>) {
        val intent = Intent(this, clazz)
        startActivity(intent)
    }
}