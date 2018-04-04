package com.chs.androiddailytext.custom_views;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chs.androiddailytext.R;
import com.chs.androiddailytext.utils.DensityUtil;
import com.chs.androiddailytext.widget.WorkStatisticView;

public class WorkLoadStatisticActivity extends AppCompatActivity {
    private LinearLayout ll_status;
    private TextView tv_dan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_load_statistic);
        WorkStatisticView view = findViewById(R.id.statistic);
        view.setData(1,1,1,4);

        ll_status = findViewById(R.id.ll_status);
        tv_dan = findViewById(R.id.tv_dan);

        tv_dan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOpen){
                    close(ll_status);
                }else {
                    open(ll_status);
                }
            }
        });
    }
    private boolean isOpen;
    private void open(final View view){
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, DensityUtil.dip2px(this,20));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float value = (float) valueAnimator.getAnimatedValue();
                Log.i("value_open",value+"");
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = (int) value;
                view.setLayoutParams(layoutParams);
            }
        });
        valueAnimator.setDuration(200);
        valueAnimator.start();
        isOpen = true;
    }
    private void close(final View view){
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(DensityUtil.dip2px(this,20),0);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float value = (float) valueAnimator.getAnimatedValue();
                Log.i("value_close",value+"");
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = (int) value;
                view.setLayoutParams(layoutParams);
            }
        });
        valueAnimator.setDuration(200);
        valueAnimator.start();
        isOpen = false;
    }
}
