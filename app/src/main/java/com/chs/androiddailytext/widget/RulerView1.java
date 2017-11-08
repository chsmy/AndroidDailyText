package com.chs.androiddailytext.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.OverScroller;

import com.chs.androiddailytext.utils.AppLog;

/**
 * 作者：chs on 2017-11-07 16:28
 * 邮箱：657083984@qq.com
 */

public class RulerView1 extends View {
    private Paint mBoundPaint;
    private Paint mScalePaint;
    private Paint mMiddlePaint;
    private Paint mTextPaint;
    private int screenWidth;
    private int screenHeight;
    private int mMaxVelocity;
    //    private Scroller mScroller;
    private OverScroller mScroller;
    private VelocityTracker mVelocityTracker;
    private int each = 10;

    private int max = 1000;
    public RulerView1(Context context) {
        super(context);
        init(context);
    }

    public RulerView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RulerView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        mScroller = new OverScroller(context);
        mVelocityTracker = VelocityTracker.obtain();
        mMaxVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();

        mBoundPaint = new Paint();
        mBoundPaint.setAntiAlias(true);
        mBoundPaint.setColor(Color.BLACK);

        mScalePaint = new Paint();
        mScalePaint.setAntiAlias(true);
        mScalePaint.setColor(Color.GRAY);
        mScalePaint.setStrokeWidth(5);

        mMiddlePaint = new Paint();
        mMiddlePaint.setAntiAlias(true);
        mMiddlePaint.setColor(Color.GREEN);
        mMiddlePaint.setStrokeWidth(5);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.GREEN);
        mTextPaint.setTextSize(30);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        AppLog.i("onDraw");
        //绘制上下边界
        canvas.drawLine(10,10,screenWidth-10,10,mBoundPaint);
        canvas.drawLine(10,200,screenWidth-10,200,mBoundPaint);

        //绘制刻度
        int startX = 10;
        for (int i = 0; i <= max; i++) {
            if(i%10 == 0){
                canvas.drawLine(startX,10,startX,90,mScalePaint);
                canvas.drawText(String.valueOf(i/10),startX - mTextPaint.measureText(String.valueOf(i/10))/2,120,mTextPaint);
            }else {
                canvas.drawLine(startX,10,startX,70,mScalePaint);
            }
            startX+=15;
//            AppLog.i("i----"+i+"startX---"+startX);
        }
        canvas.drawLine(screenWidth/2,10,screenWidth/2,90,mMiddlePaint);
//        int xxx = (int) ((screenWidth/2-10)/15 + leftMoving/15);

//        AppLog.i("mun="+xxx);
    }

    private float lastPointX;
    private float lastPointY;

    float scrolledX = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mVelocityTracker.addMovement(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastPointX = event.getRawX();
                lastPointY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getRawX();
                scrolledX = lastPointX - moveX;
                lastPointX = moveX;
                AppLog.i("mun="+scrolledX);
                scrollBy((int)scrolledX,0);
                break;
            case MotionEvent.ACTION_UP:
                mVelocityTracker.computeCurrentVelocity(1000,mMaxVelocity);
                int velocityX = (int) mVelocityTracker.getXVelocity();
                fling(-velocityX);
                break;
            case MotionEvent.ACTION_CANCEL:
                if (mVelocityTracker != null) {
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }
                break;
        }
        return true;
    }
    private void fling(int vX){
        mScroller.fling(getScrollX(), 0, vX, 0, 0, 1000*15, 0, 0);
        invalidate();
    }
    @Override
    public void computeScroll() {
        //先判断mScroller滚动是否完成
        if (mScroller.computeScrollOffset()) {

            //这里调用View的scrollTo()完成实际的滚动
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());

            //必须调用该方法，否则不一定能看到滚动效果
            postInvalidate();
        }
        super.computeScroll();
    }
}
