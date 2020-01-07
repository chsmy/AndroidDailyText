package com.chs.androiddailytext.coordinatorlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.chs.androiddailytext.widget.RoundImageView;
import com.google.android.material.appbar.AppBarLayout;


/**
 * 作者：chs on 2017/2/13 15:08
 * 邮箱：657083984@qq.com
 */

public class ImageBehavior extends CoordinatorLayout.Behavior<RoundImageView> {
    private int width, height, top, left;
    public ImageBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, RoundImageView child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, RoundImageView child, View dependency) {
        if (dependency.getY() == 0) {
            width = child.getWidth();
            height = child.getHeight();
            top = child.getTop();
            left = child.getLeft();
        }
        float percent = Math.abs(dependency.getY()) / FourActivity.scrollRange;
        Log.i("percent",parent+"**");
        float yPercent = (float) (percent * 0.85);
        child.setY(top * (1 - yPercent));
        child.setX(left + 300 * percent);
        CoordinatorLayout.LayoutParams layoutParams =
                (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        layoutParams.width = (int) (width * (1 - percent * 3 / 4));
        layoutParams.height = (int) (height * (1 - percent * 3 / 4));
        child.setLayoutParams(layoutParams);
        return true;
    }
}
