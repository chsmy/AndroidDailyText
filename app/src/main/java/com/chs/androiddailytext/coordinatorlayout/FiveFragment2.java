package com.chs.androiddailytext.coordinatorlayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chs.androiddailytext.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class FiveFragment2 extends Fragment {
    /**
     * 实际操作的tablayout，
     */
    private TabLayout mTabLayout;
    private RecyclerView mRlcontent;
    private String[] tabTxt = {"第一", "第二", "第三", "第四", "第五"};
    private List<String> datas = new ArrayList<>();
    private LinearLayoutManager manager;
    static FiveFragment2 newInstance(String title) {
        FiveFragment2 fragment = new FiveFragment2();
        Bundle args = new Bundle();
        args.putString("title",title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_five, container, false);
        mTabLayout = view.findViewById(R.id.tablayout);
        mRlcontent = view.findViewById(R.id.rl_content);
        manager = new LinearLayoutManager(requireContext());
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
        return view;
    }
}
