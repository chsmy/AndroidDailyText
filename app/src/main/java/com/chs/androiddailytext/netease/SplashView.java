package com.chs.androiddailytext.netease;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.chs.androiddailytext.R;

/**
 * author：chs
 * 时间：2019/3/26
 * 描述：
 */
public class SplashView extends View {

    private Paint mPaint;
    private int mCenterX;
    private int mCenterY;
    private int[] mCircleColors;
    private int mRedius = 90;
    public SplashView(Context context) {
        this(context,null);
    }

    public SplashView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SplashView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mCircleColors = context.getResources().getIntArray(R.array.splash_circle_colors);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = getWidth()/2;
        mCenterY = getHeight()/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircle(canvas);
    }

    private void drawCircle(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        float angleEach = (float) (2*Math.PI/mCircleColors.length);
        for (int i = 0; i < mCircleColors.length; i++) {
            float angle = i*angleEach;
            float x = (float) (Math.sin(angle)*mRedius*mCenterX);
            float y = (float) (Math.cos(angle)*mRedius*mCenterY);
            mPaint.setColor(mCircleColors[i]);
            canvas.drawCircle(x,y,18,mPaint);
        }
    }
}
