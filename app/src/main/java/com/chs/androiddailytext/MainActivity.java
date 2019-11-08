package com.chs.androiddailytext;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chs.androiddailytext.base.BaseActivity;
import com.chs.androiddailytext.changeSkin.ChangeSkinActivity;
import com.chs.androiddailytext.chart.BarChartActivity;
import com.chs.androiddailytext.custom_views.WorkLoadStatisticActivity;
import com.chs.androiddailytext.dagger.DaggerActivity;
import com.chs.androiddailytext.glide.GlideActivity;
import com.chs.androiddailytext.jetpack.LiveDataFirstActivity;
import com.chs.androiddailytext.list.ListActivity;
import com.chs.androiddailytext.myanimator.AnimActivity;
import com.chs.androiddailytext.netease.NeteaseActivity;
import com.chs.androiddailytext.pattern.strategy.StrategyActivity;
import com.chs.androiddailytext.permission.PermissionActivity;
import com.chs.androiddailytext.piccanvas.BigViewActivity;
import com.chs.androiddailytext.piccanvas.PicCanvasActivity;
import com.chs.androiddailytext.popwindow.PopActivity;
import com.chs.androiddailytext.recyclerview.RecyclerActivity;
import com.chs.androiddailytext.retorfit.OkhttpTextActivity;
import com.chs.androiddailytext.retorfit.RetrofitTextActivity;
import com.chs.androiddailytext.slideview.SlideActivity;

import java.util.ArrayList;
import java.util.List;

import login.LoginActivity;
import login.RegisterActivity;

public class MainActivity extends BaseActivity {
    List<String> datas = new ArrayList<>();
    RecyclerView recyclerView;
    BaseQuickAdapter adapter;
    @Override
    public int getContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        recyclerView = findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        adapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_main,datas) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.tv_name,item);
            }
        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initListener() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position){
                    case 0:
                        startActivity(PermissionActivity.class);
                        break;
                    case 1:
                        startActivity(ViewActivity.class);
                        break;
                    case 2:
                        startActivity(AnimatorActivity.class);
                        break;
                    case 3:
                        startActivity(PullRefreshActivity.class);
                        break;
                    case 4:
                        startActivity(ChartActivity.class);
                        break;
                    case 5:
                        startActivity(StrategyActivity.class);
                        break;
                    case 6:
                        startActivity(ChangeSkinActivity.class);
                        break;
                    case 7:
                        startActivity(OkhttpTextActivity.class);
                        break;
                    case 8:
                        startActivity(RetrofitTextActivity.class);
                        break;
                    case 9:
                        startActivity(GlideActivity.class);
                        break;
                    case 10:
                        startActivity(WorkLoadStatisticActivity.class);
                        break;
                    case 11:
                        startActivity(BarChartActivity.class);
                        break;
                    case 12:
                        startActivity(DaggerActivity.class);
                        break;
                    case 13:
                        startActivity(ListActivity.class);
                        break;
                    case 14:
                        startActivity(PicCanvasActivity.class);
                        break;
                    case 15:
                        startActivity(PopActivity.class);
                        break;
                    case 16:
                        startActivity(LoginActivity.class);
                        break;
                    case 17:
                        startActivity(RegisterActivity.class);
                        break;
                    case 18:
                        startActivity(NeteaseActivity.class);
                        break;
                    case 19:
                        startActivity(LiveDataFirstActivity.class);
                        break;
                    case 20:
                        startActivity(AnimActivity.class);
                        break;
                    case 21:
                        startActivity(RecyclerActivity.class);
                        break;
                    case 22:
                        startActivity(SlideActivity.class);
                        break;
                    case 23:
                        startActivity(BigViewActivity.class);
                        break;
                    default:
                }
            }
        });
    }
    @Override
    public void initData() {
        datas.add("权限");
        datas.add("View");
        datas.add("动画");
        datas.add("刷新");
        datas.add("图表");
        datas.add("策略模式");
        datas.add("换肤");
        datas.add("okhttp");
        datas.add("retrofit");
        datas.add("glide");
        datas.add("统计");
        datas.add("柱状图");
        datas.add("Dagger");
        datas.add("treelist");
        datas.add("PicCanvas");
        datas.add("toPop");
        datas.add("登录");
        datas.add("注册");
        datas.add("Netease");
        datas.add("Jetpack");
        datas.add("手写动画");
        datas.add("手写recycleview");
        datas.add("Slide");
        datas.add("BigView");
    }

}