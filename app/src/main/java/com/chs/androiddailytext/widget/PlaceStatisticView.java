package com.chs.androiddailytext.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.chs.androiddailytext.R;
import com.chs.androiddailytext.utils.DensityUtil;

/**
 * 作者：chs
 * 时间：2018-05-23 10:38
 * 描述：
 */
public class PlaceStatisticView extends View {
    private RectF mRect;
    private int mWidth;
    private Paint mPaint;
    public PlaceStatisticView(Context context) {
        super(context);
        init(context);
    }

    public PlaceStatisticView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PlaceStatisticView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setWillNotDraw(false);
        int height = DensityUtil.dip2px(context, 10);

        mRect = new RectF(0,0,0, height);

        mPaint = new Paint();
        mPaint.setColor(ContextCompat.getColor(context,R.color.blue));
        mPaint.setAntiAlias(true);
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mRect.right = mWidth;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRoundRect(mRect,15,15,mPaint);
    }
}
