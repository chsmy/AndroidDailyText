package com.chs.androiddailytext.coordinatorlayout;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chs.androiddailytext.R;
import com.chs.androiddailytext.coordinatorlayout.recycleview.BaseRecycleAdapter;
import com.chs.androiddailytext.coordinatorlayout.recycleview.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chs
 */
public class ThirdActivity extends AppCompatActivity {
    private RecyclerView recycleview;
    private List<String> mList = new ArrayList<>();
    private BaseRecycleAdapter mAdapter;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        initData();
        recycleview = (RecyclerView) findViewById(R.id.recycleview);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("CoordinatorLayout");
        LinearLayoutManager layoutManager =  new LinearLayoutManager(this);
        recycleview.setLayoutManager(layoutManager);
        mAdapter = new BaseRecycleAdapter<String>(this,R.layout.item,mList,recycleview) {
            @Override
            public void convert(ViewHolder holder, String str) {
                holder.setText(R.id.tv_item,str);
            }
        };
        recycleview.setAdapter(mAdapter);
    }
    private void initData() {
        for(int i = 0;i<20;i++){
            mList.add("item"+i);
        }
    }
}
