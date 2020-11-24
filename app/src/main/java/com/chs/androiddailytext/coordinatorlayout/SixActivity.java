package com.chs.androiddailytext.coordinatorlayout;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.DrawableUtils;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SizeUtils;
import com.chs.androiddailytext.R;
import com.chs.androiddailytext.coordinatorlayout.recycleview.BaseRecycleAdapter;
import com.chs.androiddailytext.coordinatorlayout.recycleview.ViewHolder;
import com.chs.androiddailytext.utils.DrawableUtil;
import com.chs.androiddailytext.utils.StatusBarUtil;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chs
 */
public class SixActivity extends AppCompatActivity {
    private RecyclerView recycleview;
    private List<String> mList = new ArrayList<>();
    private BaseRecycleAdapter mAdapter;
    private CoordinatorLayout container;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_six);
        StatusBarUtil.setTranslateStateBar(this);
        initData();
        recycleview = findViewById(R.id.recycleview);
        toolbar =  findViewById(R.id.toolbar);
        container =  findViewById(R.id.container);
        LinearLayoutManager layoutManager =  new LinearLayoutManager(this);
        recycleview.setLayoutManager(layoutManager);
        mAdapter = new BaseRecycleAdapter<String>(this,R.layout.item,mList,recycleview) {
            @Override
            public void convert(ViewHolder holder, String str) {
                holder.setText(R.id.tv_item,str);
            }
        };
        recycleview.setAdapter(mAdapter);
        recycleview.setBackground(DrawableUtil.getDrawableTop(this,R.color.white,15));
        Palette.from(BitmapFactory.decodeResource(getResources(),R.mipmap.background))
                .generate(palette -> {
                    if(palette!=null){
                        Palette.Swatch dominantSwatch = palette.getDominantSwatch();
                        container.setBackgroundColor(dominantSwatch.getRgb());
                    }

                });
    }

    private void initData() {
        for(int i = 0;i<20;i++){
            mList.add("item"+i);
        }
    }
}
