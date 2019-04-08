package com.chs.androiddailytext.netease

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
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