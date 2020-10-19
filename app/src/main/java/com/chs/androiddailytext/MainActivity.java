package com.chs.androiddailytext;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.navigation.NavDeepLinkBuilder;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chs.androiddailytext.base.BaseActivity;
import com.chs.androiddailytext.changeSkin.ChangeSkinActivity;
import com.chs.androiddailytext.changeSkin.ThemeSkinActivity;
import com.chs.androiddailytext.chart.BarChartActivity;
import com.chs.androiddailytext.chart.PercentActivity;
import com.chs.androiddailytext.coordinatorlayout.CoordinatorlayoutActivity;
import com.chs.androiddailytext.custom_views.WorkLoadStatisticActivity;
import com.chs.androiddailytext.dagger.DaggerActivity;
import com.chs.androiddailytext.glide.GlideActivity;
import com.chs.androiddailytext.jetpack.LiveDataFirstActivity;
import com.chs.androiddailytext.kotlin.CoroutinesActivity;
import com.chs.androiddailytext.list.ListActivity;
import com.chs.androiddailytext.myanimator.AnimActivity;
import com.chs.androiddailytext.netease.NeteaseActivity;
import com.chs.androiddailytext.nomal.NightActivity;
import com.chs.androiddailytext.pattern.strategy.StrategyActivity;
import com.chs.androiddailytext.permission.PermissionActivity;
import com.chs.androiddailytext.piccanvas.BigViewActivity;
import com.chs.androiddailytext.piccanvas.PicCanvasActivity;
import com.chs.androiddailytext.popwindow.PopActivity;
import com.chs.androiddailytext.recyclerview.RecyclerActivity;
import com.chs.androiddailytext.retorfit.OkhttpTextActivity;
import com.chs.androiddailytext.retorfit.RetrofitTextActivity;
import com.chs.androiddailytext.slideview.SlideActivity;
import com.chs.androiddailytext.tabscroll.TabRecyclerActivity;
import com.chs.androiddailytext.tabscroll.TabStickyActivity;
import com.chs.androiddailytext.widget.selecttext.SelectTextActivity;

import java.util.ArrayList;
import java.util.List;

import login.LoginActivity;
import login.RegisterActivity;

public class MainActivity extends BaseActivity {
    List<MainInfo> datas = new ArrayList<>();
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
        adapter = new BaseQuickAdapter<MainInfo, BaseViewHolder>(R.layout.item_main,datas) {
            @Override
            protected void convert(BaseViewHolder helper, MainInfo item) {
                helper.setText(R.id.tv_name,item.name);
            }
        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initListener() {
        adapter.setOnItemClickListener((adapter, view, position) -> {
            MainInfo mainInfo = datas.get(position);
            if(mainInfo.toWhere!=null){
                startActivity(new Intent(getApplicationContext(),mainInfo.toWhere));
            }else {
                Bundle bundle = new Bundle();
                bundle.putString("userName","大海");
                PendingIntent pendingIntent = new NavDeepLinkBuilder(MainActivity.this)
                        .setGraph(R.navigation.nav_graph)
                        .setDestination(R.id.thirdFragment)
                        .setArguments(bundle)
                        .createPendingIntent();
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    notificationManager.createNotificationChannel(new NotificationChannel(
                            "deepLink","deepLinkName", NotificationManager.IMPORTANCE_HIGH
                    ));
                }
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "deepLink")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("测试deepLink")
                        .setContentText("哈哈哈")
                        .setContentIntent(pendingIntent)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                notificationManager.notify(10,builder.build());
            }
        });
    }
    @Override
    public void initData() {
        datas.add(new MainInfo("权限",PermissionActivity.class));
        datas.add(new MainInfo("View",ViewActivity.class));
        datas.add(new MainInfo("动画",AnimatorActivity.class));
        datas.add(new MainInfo("刷新",PullRefreshActivity.class));
        datas.add(new MainInfo("图表",ChartActivity.class));
        datas.add(new MainInfo("策略模式",StrategyActivity.class));
        datas.add(new MainInfo("插件换肤",ChangeSkinActivity.class));
        datas.add(new MainInfo("okhttp",OkhttpTextActivity.class));
        datas.add(new MainInfo("retrofit",RetrofitTextActivity.class));
        datas.add(new MainInfo("glide",GlideActivity.class));
        datas.add(new MainInfo("统计",WorkLoadStatisticActivity.class));
        datas.add(new MainInfo("柱状图",BarChartActivity.class));
        datas.add(new MainInfo("Dagger",DaggerActivity.class));
        datas.add(new MainInfo("treelist",ListActivity.class));
        datas.add(new MainInfo("PicCanvas",PicCanvasActivity.class));
        datas.add(new MainInfo("toPop",PopActivity.class));
        datas.add(new MainInfo("登录",LoginActivity.class));
        datas.add(new MainInfo("注册",RegisterActivity.class));
        datas.add(new MainInfo("Netease",NeteaseActivity.class));
        datas.add(new MainInfo("Jetpack",LiveDataFirstActivity.class));
        datas.add(new MainInfo("手写动画",AnimActivity.class));
        datas.add(new MainInfo("手写recycleview",RecyclerActivity.class));
        datas.add(new MainInfo("Slide",SlideActivity.class));
        datas.add(new MainInfo("BigView",BigViewActivity.class));
        datas.add(new MainInfo("percent",PercentActivity.class));
        datas.add(new MainInfo("tabRecycler",TabRecyclerActivity.class));
        datas.add(new MainInfo("tabSticky",TabStickyActivity.class));
        datas.add(new MainInfo("Coordinator",CoordinatorlayoutActivity.class));
        datas.add(new MainInfo("协程",CoroutinesActivity.class));
        datas.add(new MainInfo("deepLink",null));
        datas.add(new MainInfo("selectText",SelectTextActivity.class));
        datas.add(new MainInfo("夜间模式", NightActivity.class));
        datas.add(new MainInfo("Theme换肤", ThemeSkinActivity.class));
    }

}