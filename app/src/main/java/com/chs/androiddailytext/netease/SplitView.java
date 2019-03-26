package com.chs.androiddailytext.netease;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
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
import java.util.Random;

/**
 * 作者：83734
 * 时间：2019/3/25
 * 描述：
 */
public class SplitView  extends View {
    private Paint mPaint;
    private Bitmap mBitmap;
    /**
     * //粒子的直径
     */
    private int d= 4;
    private List<Ball> mBalls = new ArrayList<>();
    //是否绘制原图
    private boolean isShowBitmap = true;
    private ValueAnimator splitAnimator;
    //抖动动画
    private ValueAnimator shakeAnimator;
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
        mBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.shader);
        for (int i = 0; i < mBitmap.getWidth()/d; i+=2) {
            for (int j = 0; j < mBitmap.getHeight()/d; j+=2) {
                Ball ball = new Ball();
                ball.color = mBitmap.getPixel(i,j);
                ball.x = i*d+d/2;
                ball.y = j*d+d/2;
                ball.r = d/2;

                //速度(-20,20)
                ball.vX = (float) (Math.pow(-1, Math.ceil(Math.random() * 100)) * 20 * Math.random());
                ball.vY = rangInt(-15, 35);
                //加速度
                ball.aX = 0;
                ball.aY = 0.98f;

                mBalls.add(ball);
            }
        }

        //震动动画
        shakeAnimator = ValueAnimator.ofFloat(0f, 1f).setDuration(150);
        shakeAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            Random random = new Random();
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setTranslationX((random.nextFloat() - 0.5f) * getWidth() * 0.03f);
                setTranslationY((random.nextFloat() - 0.5f) * getHeight() * 0.03f);
            }
        });
        shakeAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                splitAnimator.start();
            }
        });

        splitAnimator = ValueAnimator.ofFloat(0,1);
        splitAnimator.setRepeatCount(-1);
        splitAnimator.setDuration(2000);
        splitAnimator.setInterpolator(new LinearInterpolator());
        splitAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
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
        canvas.translate(getWidth()/2-mBitmap.getWidth()/2,getHeight()/2-mBitmap.getHeight()/2);
        if(isShowBitmap){
            canvas.drawBitmap(mBitmap,0,0,null);
        }else {
            for (Ball ball : mBalls) {
                mPaint.setColor(ball.color);
                canvas.drawCircle(ball.x,ball.y,ball.r,mPaint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            isShowBitmap = false;
            //执行动画
            shakeAnimator.start();
        }
        return super.onTouchEvent(event);
    }
}
