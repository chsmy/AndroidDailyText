package com.chs.androiddailytext.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.chs.androiddailytext.R;

/**
 * 作者：chs on 2018-04-04 14:00
 * 邮箱：657083984@qq.com
 */
public class WorkStatisticView extends View {
    private Paint mPaint;
    private int sum,yes,no,hand;
    private int mWidth,mHeight;
    private RectF mRectF;
    private Context mContext;
    public WorkStatisticView(Context context) {
        super(context);
        init(context);
    }

    public WorkStatisticView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WorkStatisticView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);

        mRectF = new RectF();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w - getPaddingLeft() - getPaddingRight();
        mHeight = h - getPaddingTop() - getPaddingBottom();
        Log.i("zhixing","onSizeChanged");
    }
    public void setData(int yes,int no,int hand,int sum){
        this.yes = yes;
        this.no = no;
        this.hand = hand;
        this.sum = sum;
        Log.i("zhixing","setData");
    }
    @Override
    protected void onDraw(Canvas canvas) {
        Log.i("zhixing","onDraw");

        mRectF.left = 0; mRectF.top = 0; mRectF.right = ((float)yes/sum)* mWidth; mRectF.bottom = mHeight;
        mPaint.setColor(ContextCompat.getColor(mContext, R.color.statistic_yes));
        canvas.drawRect(mRectF,mPaint);

        mRectF.left = mRectF.right; mRectF.top = 0; mRectF.right = mRectF.right+((float)no/sum)*mWidth; mRectF.bottom = mHeight;
        mPaint.setColor(ContextCompat.getColor(mContext, R.color.statistic_no));
        canvas.drawRect(mRectF,mPaint);

        mRectF.left = mRectF.right; mRectF.top = 0; mRectF.right = mRectF.right+((float)hand/sum)*mWidth; mRectF.bottom = mHeight;
        mPaint.setColor(ContextCompat.getColor(mContext, R.color.statistic_hand));
        canvas.drawRect(mRectF,mPaint);
    }
}
