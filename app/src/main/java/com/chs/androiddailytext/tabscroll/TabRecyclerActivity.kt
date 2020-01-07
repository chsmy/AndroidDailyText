package com.chs.androiddailytext.tabscroll

import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chs.androiddailytext.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_tab_scroll.*

/**
 * tablayout recyclerview 联动
 */
class TabRecyclerActivity : AppCompatActivity() {

    private val tabTxt = arrayOf("第一", "第二", "第三", "第四", "第五")
    private var isRecyclerScroll = false
    //用于recyclerView滑动到指定的位置
    private var canScroll = false
    private var scrollToPosition = 0
    //记录上一次位置，防止在同一内容块里滑动 重复定位到tablayout
    private var lastPos = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_scroll)
        tabTxt.forEach {
            tablayout.addTab(tablayout.newTab().setText(it))
        }

        var list = mutableListOf<String>()

        for (i in 1..100){
            list.add("item++++++++++${i}")
        }

        val manager = LinearLayoutManager(this)
        recyclerView.layoutManager = manager
        recyclerView.adapter = MyAdapter(R.layout.list_item,list)

        tablayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                //点击标签，使recyclerView滑动，isRecyclerScroll置false
                val pos: Int? = tab?.position
                isRecyclerScroll = false
                moveToPosition(manager, recyclerView, pos!!*20)
            }

        })

        recyclerView.setOnTouchListener { _, event ->
            //当滑动由recyclerView触发时，isRecyclerScroll 置true
            if (event.action == MotionEvent.ACTION_DOWN) {
                isRecyclerScroll = true
            }
            false
        }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (canScroll) {
                    canScroll = false
                    moveToPosition(manager, recyclerView, scrollToPosition)
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (isRecyclerScroll) {
                    //第一个可见的view的位置，即tablayou需定位的位置
                    val position = manager.findFirstVisibleItemPosition()/20
                    if (lastPos != position) {
                        tablayout.setScrollPosition(position, 0F, true)
                    }
                    lastPos = position
                }
            }
        })
    }

    private fun moveToPosition(manager: LinearLayoutManager, recyclerView: RecyclerView, position: Int) {
        // 第一个可见的view的位置
        val firstItem = manager.findFirstVisibleItemPosition()
        // 最后一个可见的view的位置
        val lastItem = manager.findLastVisibleItemPosition()
        when {
            position <= firstItem -> { // 如果跳转位置firstItem 之前(滑出屏幕的情况)，就smoothScrollToPosition可以直接跳转，
                recyclerView.scrollToPosition(position)
            }
            position <= lastItem -> { // 跳转位置在firstItem 之后，lastItem 之间（显示在当前屏幕），smoothScrollBy来滑动到指定位置
                val top: Int = recyclerView.getChildAt(position - firstItem).getTop()
                recyclerView.smoothScrollBy(0, top)
            }
            else -> { // 如果要跳转的位置在lastItem 之后，则先调用smoothScrollToPosition将要跳转的位置滚动到可见位置
                // 再通过onScrollStateChanged控制再次调用当前moveToPosition方法，执行上一个判断中的方法
                recyclerView.scrollToPosition(position)
                scrollToPosition = position
                canScroll = true
            }
        }
    }
}

class MyAdapter(resId:Int,datas:List<String>) :BaseQuickAdapter<String,BaseViewHolder>(resId,datas) {

    override fun convert(helper: BaseViewHolder?, item: String?) {
        helper?.setText(R.id.tvname,item)
    }
}