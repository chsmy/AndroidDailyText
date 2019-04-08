package com.chs.androiddailytext.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.chs.androiddailytext.utils.AppLog;

/**
 * 作者：chs on 2017-11-07 16:28
 * 邮箱：657083984@qq.com
 */

public class RulerView extends View {
    private Paint mBoundPaint;
    private Paint mScalePaint;
    private Paint mMiddlePaint;
    private Paint mTextPaint;
    private int screenWidth;
    private int screenHeight;

    private int each = 10;

    private int max = 1000;
    public RulerView(Context context) {
        super(context);
        init(context);
    }

    public RulerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RulerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setWillNotDraw(false);
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
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
            float startXNow = startX - leftMoving;
            if(i%10 == 0){
                canvas.drawLine(startXNow,10,startXNow,90,mScalePaint);
                canvas.drawText(String.valueOf(i/10),startX - mTextPaint.measureText(String.valueOf(i/10))/2- leftMoving,120,mTextPaint);
            }else {
                canvas.drawLine(startXNow,10,startXNow,70,mScalePaint);
            }
            startX+=15;
//            AppLog.i("i----"+i+"startX---"+startX);
        }
        canvas.drawLine(screenWidth/2,10,screenWidth/2,90,mMiddlePaint);
        int xxx = (int) ((screenWidth/2-10)/15 + leftMoving/15);

        AppLog.i("mun="+xxx);
    }

    private float lastPointX;
    private float lastPointY;
    /**
     * 向右边滑动的距离
     */
    private float leftMoving;

    float scrolledX = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastPointX = event.getRawX();
                lastPointY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getRawX();
                scrolledX = lastPointX - moveX;
                leftMoving = leftMoving+scrolledX;
                lastPointX = moveX;
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                new Thread(new SmoothScrollThread(scrolledX)).start();
                break;
        }
        return true;
    }
    /**
     * 左右滑动的时候 当手指抬起的时候  使滑动慢慢停止 不会立刻停止
     */
    private class SmoothScrollThread implements Runnable {
        float lastMoving;
        boolean scrolling = true;

        private SmoothScrollThread(float lastMoving) {
            this.lastMoving = lastMoving;
            scrolling = true;
        }

        @Override
        public void run() {
            while (scrolling) {
                long start = System.currentTimeMillis();
                lastMoving = (int) (0.9f * lastMoving);
                leftMoving += lastMoving;

                postInvalidate();

                if (Math.abs(lastMoving) < 5) {
                    scrolling = false;
                }

                long end = System.currentTimeMillis();
                if (end - start < 20) {
                    try {
                        Thread.sleep(20 - (end - start));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
