package com.chs.androiddailytext.netease;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.chs.androiddailytext.R;
import com.chs.androiddailytext.base.BaseActivity;
import com.chs.androiddailytext.widget.ArrowDrawable;

/**
 * @author：chs date：2019/5/3
 * des：
 */
public class AnimateActivity extends BaseActivity {
    private ImageView mImageView;
    private ImageView iv_gong;
    @Override
    public int getContentView(Bundle savedInstanceState) {
        return R.layout.activity_animate;
    }

    @Override
    public void initView() {
        mImageView = findViewById(R.id.imageview);
        iv_gong = findViewById(R.id.iv_gong);
        iv_gong.setImageDrawable(new ArrowDrawable(300,300));
    }

    public void translation(View view) {
//        使用ValueAnimator
//        ValueAnimator animator = ValueAnimator.ofFloat(0,500);
//        animator.setDuration(1000);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float animatedValue = (float) animation.getAnimatedValue();
//                mImageView.setTranslationX(animatedValue);
//            }
//        });
//        animator.start();
//     使用ObjectAnimator
        ObjectAnimator animator = ObjectAnimator.ofFloat(mImageView, "translationX", 0, 500f,0);
        animator.setDuration(1000);
        animator.start();

    }

    public void rotate(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mImageView, "rotation", 0f, 360f);
        animator.setDuration(1000);
        animator.start();
    }

    public void scale(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mImageView, "scaleY",  1f, 3f, 1f);
        animator.setDuration(1000);
        animator.start();
    }
    public void group(View view) {
        ObjectAnimator translationX = ObjectAnimator.ofFloat(mImageView, "translationX", 0, 500f,0);

        ObjectAnimator rotation = ObjectAnimator.ofFloat(mImageView, "rotation", 0f, 360f);

        ObjectAnimator scaleY = ObjectAnimator.ofFloat(mImageView, "scaleY",  1f, 3f, 1f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(1000);
        animatorSet.playTogether(translationX,rotation,scaleY);
        animatorSet.start();
    }
    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

}
