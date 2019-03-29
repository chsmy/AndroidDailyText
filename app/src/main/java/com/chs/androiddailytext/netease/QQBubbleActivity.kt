package com.chs.androiddailytext.netease

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearLayoutManager.VERTICAL
import com.chs.androiddailytext.R
import kotlinx.android.synthetic.main.activity_netease.*

/**
 * author：chs
 * date：2019/3/28
 * des：
 */
class QQBubbleActivity :AppCompatActivity(){
    var datas = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qqbubble)

        for(i in 1..15){
            datas.add("item-----"+i)
        }

        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = VERTICAL
        recycleview.layoutManager = linearLayoutManager
        recycleview.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))
        val adapter = QQViewAdapter(datas,this)
        recycleview.adapter = adapter;
    }
}