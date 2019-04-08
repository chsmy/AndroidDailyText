package com.chs.androiddailytext;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

import com.chs.androiddailytext.module.ChartEntity;
import com.chs.androiddailytext.widget.BarChart;
import com.chs.androiddailytext.widget.LineChart;

import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        BarChart barChart = (BarChart) findViewById(R.id.chart);
        List<ChartEntity> data = new ArrayList<>();
        for(int i =0;i<20;i++){
            data.add(new ChartEntity(String.valueOf(i), (float) (Math.random()*1000)));
        }
        barChart.setData(data);
        barChart.setOnItemBarClickListener(new BarChart.OnItemBarClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(ChartActivity.this,"点击了："+position,Toast.LENGTH_SHORT).show();
            }
        });
        barChart.startAnimation();

        LineChart lineChart = (LineChart) findViewById(R.id.chart1);
        List<ChartEntity> data1 = new ArrayList<>();
        for(int i =0;i<20;i++){
            data1.add(new ChartEntity(String.valueOf(i), (float) (Math.random()*1000)));
        }
        lineChart.setData(data1);
        lineChart.startAnimation();
    }
}
