package com.chs.androiddailytext.slideview;

import android.os.Bundle;

import com.chs.androiddailytext.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author：chs date：2019/4/17
 * des：
 */
public class SlideActivity extends AppCompatActivity {
    private SlideDetailsLayout mSlideDetailsLayout;
    private boolean isFinish = true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);
        mSlideDetailsLayout = findViewById(R.id.slidedetails);
        int currentHeight = getResources().getDisplayMetrics().heightPixels*2/3;
        boolean isFirstIn = true;
        mSlideDetailsLayout.setFirstIn(isFirstIn);
        if(isFirstIn){
            isFinish = false;
            mSlideDetailsLayout.processTouchEvent(-currentHeight);
            mSlideDetailsLayout.setOnFinishListener(new SlideDetailsLayout.OnFinishListener() {
                @Override
                public void finish() {
                    isFinish = true;
                }
            });
            getWindow().getDecorView().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(!isFinish){
                        mSlideDetailsLayout.smoothOpen(true);
                    }
                }
            },3000);
        }

        SmartRefreshLayout refreshLayout = findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(new ArrayList<String>(),this));

    }
}
