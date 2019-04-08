package com.chs.androiddailytext.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.chs.androiddailytext.R;
import com.chs.androiddailytext.utils.DensityUtil;

/**
 * 作者：chs
 * 时间：2018-04-19 17:05
 * 描述：分割线
 */
public class PathDivision extends View {
    private Paint mPaint;
    private Path mPath;
    private int mWidth;
    public PathDivision(Context context) {
        super(context);
        init(context);
    }

    public PathDivision(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PathDivision(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
    }

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(ContextCompat.getColor(context,R.color.statistic_hand));
        mPaint.setStrokeWidth(DensityUtil.dip2px(context, 1));
        mPaint.setStyle(Paint.Style.STROKE);

        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.moveTo(0,20);
        mPath.lineTo(mWidth/2-30,20);
        mPath.lineTo(mWidth/2,0);
        mPath.lineTo(mWidth/2+30,20);
        mPath.lineTo(mWidth,20);
        canvas.drawPath(mPath,mPaint);
    }
}
