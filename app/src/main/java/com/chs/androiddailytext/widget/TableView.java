package com.chs.androiddailytext.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.SizeUtils;
import com.chs.androiddailytext.R;

/**
 * @author: chs
 * @date: Create in 2020/11/5
 * @description
 */
public class TableView extends View {

    private Paint mLinePaint;
    private Paint mTextPaint;
    private Path mPath;
    private int mRadius = SizeUtils.dp2px(20);
    private static int LEFT_TOP = 1;
    private static int RIGHT_TOP = 2;
    private static int LEFT_BOTTOM = 3;
    private static int RIGHT_BOTTOM = 4;
    private static int MIDDLE_TOP = 5;
    private static int MIDDLE_BOTTOM = 6;

    private int mCurrentType = LEFT_TOP;

    public TableView(Context context) {
        this(context,null);
    }

    public TableView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TableView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStrokeWidth(SizeUtils.dp2px(2));
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setColor(ContextCompat.getColor(context, R.color.gray));

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(SizeUtils.dp2px(16));
        mTextPaint.setColor(ContextCompat.getColor(context,R.color.black));
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        mPath = new Path();
        mCurrentType = LEFT_TOP;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mCurrentType == LEFT_TOP){
            mPath.moveTo(mRadius,0);
            mPath.lineTo(getWidth(),0);
            mPath.lineTo(getWidth(),getHeight());
            mPath.lineTo(0,getHeight());
            mPath.lineTo(0,mRadius);
            mPath.quadTo(0,0,mRadius,0);
        }else if(mCurrentType == LEFT_BOTTOM){
            mPath.moveTo(0,0);
            mPath.lineTo(getWidth(),0);
            mPath.lineTo(getWidth(),getHeight());
            mPath.lineTo(mRadius,getHeight());
            mPath.quadTo(0,getHeight(),0,getHeight()-mRadius);
            mPath.lineTo(0,0);
        }else if(mCurrentType == RIGHT_TOP){
            mPath.moveTo(0,0);
            mPath.lineTo(getWidth()-mRadius,0);
            mPath.quadTo(getWidth(),0,getWidth(),mRadius);
            mPath.lineTo(getWidth(),getHeight());
            mPath.lineTo(0,getHeight());
            mPath.lineTo(0,0);
        }else if(mCurrentType == RIGHT_BOTTOM){
            mPath.moveTo(0,0);
            mPath.lineTo(getWidth(),0);
            mPath.lineTo(getWidth(),getHeight()-mRadius);
            mPath.quadTo(getWidth(),getHeight(),getWidth()-mRadius,getHeight());
            mPath.lineTo(0,getHeight());
            mPath.lineTo(0,0);
        }else if(mCurrentType == MIDDLE_TOP){
            mPath.moveTo(0,0);
            mPath.lineTo(getWidth(),0);
            mPath.lineTo(getWidth(),getHeight());
            mPath.lineTo(0,getHeight());
        }else if(mCurrentType == MIDDLE_BOTTOM){
            mPath.moveTo(getWidth(),0);
            mPath.lineTo(getWidth(),getHeight());
            mPath.lineTo(0,getHeight());
        }
        Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
        int baseline = (getHeight() - fontMetrics.bottom - fontMetrics.top) / 2;
        canvas.drawPath(mPath,mLinePaint);
        canvas.drawText("1月", getWidth() >> 1, baseline,mTextPaint);
    }
}
