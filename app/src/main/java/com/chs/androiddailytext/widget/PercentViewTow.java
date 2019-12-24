package com.chs.androiddailytext.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.chs.androiddailytext.R;
import com.chs.androiddailytext.utils.DensityUtil;

/**
 * @author chs
 * 百分比
 */
public class PercentViewTow extends View {

    private Paint mPaint;
    private float mWidth = 0f;
    private float mHeight = 0f;
    private RectF mRectAll;
    private RectF mRectPercent;
    private float mPercent;
    public PercentViewTow(Context context) {
        this(context, null);
    }

    public PercentViewTow(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PercentViewTow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);

        mRectAll = new RectF(0,0,0, mHeight);
        mRectPercent = new RectF(0,0,0, mHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mHeight = h;
        this.mWidth = w;
        mRectAll.bottom = h;
        mRectPercent.bottom = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mRectAll.right = mWidth;
        mRectPercent.right = mWidth * mPercent;
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.axis));
        canvas.drawRoundRect(mRectAll, DensityUtil.dip2px(getContext(),2.5f),DensityUtil.dip2px(getContext(),2.5f),mPaint);
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.tab_color_blue));
        canvas.drawRoundRect(mRectPercent, DensityUtil.dip2px(getContext(),2.5f),DensityUtil.dip2px(getContext(),2.5f),mPaint);
    }

    /**
     * 给数据赋值
     */
    public void setScales(float percent){
        mPercent = percent;
        invalidate();
    }

}