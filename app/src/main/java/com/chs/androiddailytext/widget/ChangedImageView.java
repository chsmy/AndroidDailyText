package com.chs.androiddailytext.widget;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;

import com.chs.androiddailytext.utils.AppLog;

/**
 * 作者：chs on 2017-11-06 10:35
 * 邮箱：657083984@qq.com
 */

public class ChangedImageView extends androidx.appcompat.widget.AppCompatImageView {

    private int cha;

    public ChangedImageView(Context context) {
        super(context);
    }

    public ChangedImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ChangedImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        AppLog.i("onMeasure");
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        height+=cha;
        width+=cha;

        setMeasuredDimension(width,height);
    }

    public void setCha(int cha) {
        this.cha = cha;
        requestLayout();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}
