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
    private LinearLayoutManager manager;
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
        //实际的tablayout的点击切换
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                mRlcontent.stopScroll();
                manager.scrollToPositionWithOffset(pos*20,0);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        mRlcontent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int top = manager.findFirstVisibleItemPosition();
                if(top%20 == 0){
                    int position = top/20;
                    mTabLayout.setScrollPosition(position, 0, true);
                    TabLayout.Tab tabAt = mTabLayout.getTabAt(position);
                    if(tabAt!=null){
                        tabAt.select();
                    }
                }
            }
        });
        for (int i = 0; i < tabTxt.length; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(tabTxt[i]));
        }
    }
}
