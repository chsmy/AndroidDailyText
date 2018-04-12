package com.chs.androiddailytext.chart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.chs.androiddailytext.R;
import com.chs.androiddailytext.module.BarChartEntity;
import com.chs.androiddailytext.widget.BarChart_;

import java.util.ArrayList;
import java.util.List;

public class BarChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        BarChart_ barChart_ = findViewById(R.id.bar_chart_);
        BarChart_ barChart = findViewById(R.id.bar_chart);
        List<BarChartEntity> data = new ArrayList<>();
        data.add(new BarChartEntity("特种设备组", new Float[]{(float) (Math.random()*1000)}));
        for(int i =0;i<20;i++){
            data.add(new BarChartEntity("第"+String.valueOf(i)+"项", new Float[]{(float) (Math.random()*1000)}));
        }
        barChart.setOnItemBarClickListener(new BarChart_.OnItemBarClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(BarChartActivity.this,"点击了："+position,Toast.LENGTH_SHORT).show();
            }
        });
        barChart_.setData(data,new int[]{Color.parseColor("#6FC5F4")},"分组","数量");
        barChart_.startAnimation();


        List<BarChartEntity> data1 = new ArrayList<>();

        data1.add(new BarChartEntity("特种设备组", new Float[]{1003f, 500f, 600f}));
        data1.add(new BarChartEntity("暖通组", new Float[]{1003f, 500f, 600f}));
        data1.add(new BarChartEntity("暖通组", new Float[]{890f, 456f, 123f}));
        data1.add(new BarChartEntity("宗秀组", new Float[]{456f, 741f, 654f}));
        data1.add(new BarChartEntity("粽球组", new Float[]{258f, 951f, 12f}));
        data1.add(new BarChartEntity("弱点组", new Float[]{863f, 45f, 99f}));
        data1.add(new BarChartEntity("强电组", new Float[]{357f, 235f, 456f}));
        data1.add(new BarChartEntity("电器组", new Float[]{452f, 321f, 55f}));
        data1.add(new BarChartEntity("保洁组", new Float[]{321f, 333f, 222f}));
        data1.add(new BarChartEntity("暖通组", new Float[]{654f, 555f, 666f}));
        data1.add(new BarChartEntity("保安组", new Float[]{846f, 111f, 444f}));

        barChart.setOnItemBarClickListener(new BarChart_.OnItemBarClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(BarChartActivity.this,"点击了："+position,Toast.LENGTH_SHORT).show();
            }
        });
        barChart.setData(data1,
                new int[]{Color.parseColor("#6FC5F4"),Color.parseColor("#78DA9F"),Color.parseColor("#FCAE84")}
                ,"分组","数量");
        barChart.startAnimation();
    }
}
