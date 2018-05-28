package com.chs.androiddailytext;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chs.androiddailytext.changeSkin.ChangeSkinActivity;
import com.chs.androiddailytext.chart.BarChartActivity;
import com.chs.androiddailytext.custom_views.WorkLoadStatisticActivity;
import com.chs.androiddailytext.dagger.DaggerActivity;
import com.chs.androiddailytext.glide.GlideActivity;
import com.chs.androiddailytext.http.OkhttpTextActivity;
import com.chs.androiddailytext.http.RetrofitTextActivity;
import com.chs.androiddailytext.list.ListActivity;
import com.chs.androiddailytext.module.Content;
import com.chs.androiddailytext.pattern.strategy.StrategyActivity;
import com.chs.androiddailytext.permission.PermissionActivity;
import com.chs.androiddailytext.piccanvas.PicCanvasActivity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String json = "[{\"title\":\"aa\",\"id\":\"1\",\"type\":\"check_radio\",\"is_checked\":\"0\",\"option\":[{\"value\":\"a\",\"is_default\":\"1\",\"key\":\"1\"}]},{\"title\":\"bb\",\"id\":\"2\",\"type\":\"check_checkbox\",\"is_checked\":\"0\",\"option\":[{\"value\":\"\\u9009\\u9879\\u4e00b\",\"is_default\":\"1\",\"key\":\"1\"}]},{\"title\":\"aaaaaaa\",\"id\":\"3\",\"type\":\"check_show\",\"content\":\"1.\\u738d\\u53e4\\n2.\\u6c34\\u7535\\u8d39\\u6c34\\u7535\\u8d39\\n3.SD\\u53d1\\u751f\\u7684\"}]\n";
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<JsonObject>>()
        {}.getType();
        ArrayList<JsonObject> jsonObjects = new Gson().fromJson(json, type);

        ArrayList<Content> arrayList = new ArrayList<>();
        for (JsonObject jsonObject : jsonObjects)
        {
            arrayList.add(new Gson().fromJson(jsonObject, Content.class));
        }
        Log.i("content",arrayList.get(2).getTitle());
        TextView textView = findViewById(R.id.tv_content);
        textView.setText(arrayList.get(2).getContent());



        String json1 = "[{\"title\":\"aa\",\"id\":\"1\",\"type\":\"check_radio\",\"is_checked\":\"0\",\"option\":[{\"value\":\"a\",\"is_default\":\"1\",\"key\":\"1\"}]},{\"title\":\"bb\",\"id\":\"2\",\"type\":\"check_checkbox\",\"is_checked\":\"0\",\"option\":[{\"value\":\"\\u9009\\u9879\\u4e00b\",\"is_default\":\"1\",\"key\":\"1\"}]},{\"title\":\"aaaaaaa\",\"id\":\"3\",\"type\":\"check_show\",\"content\":\"1.\\u738d\\u53e4\\\\n2.\\u6c34\\u7535\\u8d39\\u6c34\\u7535\\u8d39\\\\n3.SD\\u53d1\\u751f\\u7684\"}]";
        ArrayList<JsonObject> jsonObjects1 = new Gson().fromJson(json1, type);

        ArrayList<Content> arrayList1 = new ArrayList<>();
        for (JsonObject jsonObject : jsonObjects1)
        {
            arrayList1.add(new Gson().fromJson(jsonObject, Content.class));
        }
        TextView textView1 = findViewById(R.id.tv_content1);
        textView1.setText(arrayList1.get(2).getContent());
    }

    public void permission(View view) {
        Intent intent = new Intent(this, PermissionActivity.class);
        startActivity(intent);
    }

    public void ViewActivity(View view) {
        Intent intent = new Intent(this, ViewActivity.class);
        startActivity(intent);
    }

    public void AnimatorActivity(View view) {
        Intent intent = new Intent(this, AnimatorActivity.class);
        startActivity(intent);
    }
    public void pullRefreshActivity(View view) {
        Intent intent = new Intent(this, PullRefreshActivity.class);
        startActivity(intent);
    }

    public void toChart(View view) {
        Intent intent = new Intent(this, ChartActivity.class);
        startActivity(intent);
    }

    public void toStrategy(View view) {
        Intent intent = new Intent(this, StrategyActivity.class);
        startActivity(intent);
    }

    public void toChnageSkin(View view) {
        Intent intent = new Intent(this, ChangeSkinActivity.class);
        startActivity(intent);
    }

    public void okHttp(View view) {
        Intent intent = new Intent(this, OkhttpTextActivity.class);
        startActivity(intent);
    }

    public void toRetrofit(View view) {
        Intent intent = new Intent(this, RetrofitTextActivity.class);
        startActivity(intent);
    }

    public void toGlide(View view) {
        Intent intent = new Intent(this, GlideActivity.class);
        startActivity(intent);
    }

    public void toStatistic(View view) {
        Intent intent = new Intent(this, WorkLoadStatisticActivity.class);
        startActivity(intent);
    }

    public void toBarchart(View view) {
        Intent intent = new Intent(this, BarChartActivity.class);
        startActivity(intent);
    }

    public void toDagger(View view) {
        Intent intent = new Intent(this, DaggerActivity.class);
        startActivity(intent);
    }

    public void toList(View view) {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }

    public void toPicCanvas(View view) {
        Intent intent = new Intent(this, PicCanvasActivity.class);
        startActivity(intent);
    }
}