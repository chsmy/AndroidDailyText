package com.chs.androiddailytext.pattern.strategy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.chs.androiddailytext.R;

public class StrategyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strategy);
//        AnimationUtil util = new AnimationUtil();
//        util.animate(1);
        AnimateController controller = new AnimateController();
        controller.setAnimate(new Animate1());
        controller.animate();
    }
}
