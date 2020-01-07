package com.chs.androiddailytext.tabscroll;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chs.androiddailytext.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chs
 */
public class TabStickyActivity extends AppCompatActivity {
    /**
     * 实际操作的tablayout，
     */
    private TabLayout mTabLayout;
    private RecyclerView mRlcontent;
    private String[] tabTxt = {"第一", "第二", "第三", "第四", "第五"};
    private List<String> datas = new ArrayList<>();
    //用于recyclerView滑动到指定的位置
    private boolean canScroll;
    private int scrollToPosition;
    //判读是否是scrollview主动引起的滑动，true-是，false-否，由tablayout引起的
    private boolean isScroll;
    //记录上一次位置，防止在同一内容块里滑动 重复定位到tablayout
    private int lastPos = 0;
    private LinearLayoutManager manager;
    //判读是否是recyclerView主动引起的滑动，true- 是，false- 否，由tablayout引起的
    private boolean isRecyclerScroll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_scroll2);
        mTabLayout = findViewById(R.id.tablayout);
        mRlcontent = findViewById(R.id.rl_content);
        manager = new LinearLayoutManager(this);
        mRlcontent.setLayoutManager(manager);
        for (int i = 0; i < 100; i++) {
            datas.add("item+++++"+i);
        }
        mRlcontent.setAdapter(new BaseQuickAdapter<String, BaseViewHolder>(R.layout.list_item,datas) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.tvname,item);
            }
        });
        for (int i = 0; i < tabTxt.length; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(tabTxt[i]));
        }
        //实际的tablayout的点击切换
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                isRecyclerScroll = false;
                mRlcontent.scrollToPosition(pos*20);
//                moveToPosition(pos*20);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
//        mRlcontent.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                //当滑动由recyclerView触发时，isRecyclerScroll 置true
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    isRecyclerScroll = true;
//                }
//                return false;
//            }
//        });
        mRlcontent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
//                if (canScroll) {
//                    canScroll = false;
//                    moveToPosition(scrollToPosition/20);
//                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                if (isRecyclerScroll) {
//                    //第一个可见的view的位置，即tablayou需定位的位置
//                    int position = manager.findFirstVisibleItemPosition()/20;
//                    if (lastPos != position) {
//                        mTabLayout.setScrollPosition(position, 0, true);
//                    }
//                    lastPos = position;
//                }
                int position = manager.findFirstVisibleItemPosition()/20;
                mTabLayout.setScrollPosition(position, 0, true);
            }
        });
    }
    public void moveToPosition(int position) {
        // 第一个可见的view的位置
        int firstItem = manager.findFirstVisibleItemPosition();
        // 最后一个可见的view的位置
        int lastItem = manager.findLastVisibleItemPosition();
        if (position <= firstItem) {
            // 如果跳转位置firstItem 之前(滑出屏幕的情况)，就smoothScrollToPosition可以直接跳转，
            mRlcontent.scrollToPosition(position);
        } else if (position <= lastItem) {
            // 跳转位置在firstItem 之后，lastItem 之间（显示在当前屏幕），smoothScrollBy来滑动到指定位置
            int top = mRlcontent.getChildAt(position - firstItem).getTop();
            mRlcontent.smoothScrollBy(0, top);
        } else {
            // 如果要跳转的位置在lastItem 之后，则先调用smoothScrollToPosition将要跳转的位置滚动到可见位置
            // 再通过onScrollStateChanged控制再次调用当前moveToPosition方法，执行上一个判断中的方法
            mRlcontent.scrollToPosition(position);
            scrollToPosition = position;
            canScroll = true;
        }
    }
}
