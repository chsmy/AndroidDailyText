package com.chs.androiddailytext;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.chs.androiddailytext.module.ChartEntity;
import com.chs.androiddailytext.permission.PermissionActivity;
import com.chs.androiddailytext.widget.BarChart;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BarChart barChart = (BarChart) findViewById(R.id.chart);
        List<ChartEntity> data = new ArrayList<>();
        for(int i =0;i<20;i++){
            data.add(new ChartEntity(String.valueOf(i), (float) (Math.random()*1000)));
        }
        barChart.setData(data);
        barChart.setOnItemBarClickListener(new BarChart.OnItemBarClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(MainActivity.this,"点击了："+position,Toast.LENGTH_SHORT).show();
            }
        });
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
}
