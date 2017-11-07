package com.chs.androiddailytext.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
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
    private Paint mTextPaint;
    private int screenWidth;
    private int screenHeight;

    private int each = 10;

    private int max = 100;
    public RulerView(Context context) {
        super(context);
        init();
    }

    public RulerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RulerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        mBoundPaint = new Paint();
        mBoundPaint.setAntiAlias(true);
        mBoundPaint.setColor(Color.BLACK);

        mScalePaint = new Paint();
        mScalePaint.setAntiAlias(true);
        mScalePaint.setColor(Color.GRAY);
        mScalePaint.setStrokeWidth(5);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.GREEN);
        mTextPaint.setTextSize(20);
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
        for (int i = 0; i < max; i++) {
            if(i%10 == 0){
                canvas.drawLine(startX,10,startX,70,mScalePaint);
                canvas.drawText(String.valueOf(i),startX - mTextPaint.measureText(String.valueOf(i))/2,100,mTextPaint);
            }else {
                canvas.drawLine(startX,10,startX,50,mScalePaint);
            }
            startX+=15;
            AppLog.i("i----"+i+"startX---"+startX);
        }
    }

    private float curX;
    private float curY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                curX = event.getX();
                curY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }
}
