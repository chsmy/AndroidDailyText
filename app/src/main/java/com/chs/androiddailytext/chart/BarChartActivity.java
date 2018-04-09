package com.chs.androiddailytext.chart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.chs.androiddailytext.R;
import com.chs.androiddailytext.module.ChartEntity;
import com.chs.androiddailytext.widget.BarChart_;

import java.util.ArrayList;
import java.util.List;

public class BarChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        BarChart_ barChart = findViewById(R.id.bar_chart);
        List<ChartEntity> data = new ArrayList<>();
        for(int i =0;i<20;i++){
            data.add(new ChartEntity(String.valueOf(i), (float) (Math.random()*1000)));
        }
        barChart.setData(data);
    }
}
