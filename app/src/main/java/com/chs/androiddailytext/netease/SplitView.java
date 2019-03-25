package com.chs.androiddailytext.netease;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.chs.androiddailytext.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：83734
 * 时间：2019/3/25
 * 描述：
 */
public class SplitView  extends View {
    private Paint mPaint;
    private Bitmap mBitmap;
    private int d= 2;//粒子的直径
    private List<Ball> mBalls = new ArrayList<>();

    private ValueAnimator mAnimator;
    public SplitView(Context context) {
        this(context,null);
    }

    public SplitView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SplitView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        //创建一个bitmap,遍历它的宽高，获取像素值，封装成一个一个的小球
        mBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.katong);
        for (int i = 0; i < mBitmap.getWidth(); i++) {
            for (int j = 0; j < mBitmap.getHeight(); j++) {
                Ball ball = new Ball();
                ball.color = mBitmap.getPixel(i,j);
                ball.x = i*d+d/2;
                ball.y = j*d+d/2;
                ball.r = d/2;

                //速度(-20,20)
                ball.vX = (float) (Math.pow(-1, Math.ceil(Math.random() * 1000)) * 20 * Math.random());
                ball.vY = rangInt(-15, 35);
                //加速度
                ball.aX = 0;
                ball.aY = 0.98f;

                mBalls.add(ball);
            }
        }

        mAnimator = ValueAnimator.ofFloat(0,1);
        mAnimator.setRepeatCount(-1);
        mAnimator.setDuration(2000);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                updateBall();
                invalidate();
            }
        });
    }
    private int rangInt(int i, int j) {
        int max = Math.max(i, j);
        int min = Math.min(i, j) - 1;
        //在0到(max - min)范围内变化，取大于x的最小整数 再随机
        return (int) (min + Math.ceil(Math.random() * (max - min)));
    }
    private void updateBall() {
        for (Ball ball : mBalls) {
            ball.x += ball.vX;
            ball.y += ball.vY;

            ball.vX += ball.aX;
            ball.vY += ball.aY;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Ball ball : mBalls) {
            mPaint.setColor(ball.color);
            canvas.drawCircle(ball.x,ball.y,ball.r,mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            //执行动画
            mAnimator.start();
        }
        return super.onTouchEvent(event);
    }
}
