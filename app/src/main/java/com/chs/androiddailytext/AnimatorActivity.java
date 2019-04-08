package com.chs.androiddailytext;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.chs.androiddailytext.widget.ProgressView;

public class AnimatorActivity extends AppCompatActivity {
    ImageView imageView;
    ProgressView mProgressView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);
         imageView = findViewById(R.id.iv);
        mProgressView = findViewById(R.id.progress);
    }

    public void action(View view) {
//        imageView.animate().translationX(500);

//        imageView.animate().translationX(500)
//                .setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator animator = ObjectAnimator.ofFloat(mProgressView,"progress",0,180);
        animator.start();
    }
}
