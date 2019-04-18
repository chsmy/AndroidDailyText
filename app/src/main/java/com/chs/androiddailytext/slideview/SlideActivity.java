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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);
        mSlideDetailsLayout = findViewById(R.id.slidedetails);

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSlideDetailsLayout.smoothOpen(true);
            }
        },3000);

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
