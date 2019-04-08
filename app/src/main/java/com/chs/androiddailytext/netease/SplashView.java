package com.chs.androiddailytext.netease;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

import com.chs.androiddailytext.R;

/**
 * @author chs
 * 时间：2019/3/26
 * 描述：
 */
public class SplashView extends View {

    /**
     * 绘制旋转圆的画笔和绘制水波纹的画笔
     */
    private Paint mPaint,mHollowPaint;
    /**
     * 旋转圆的中心坐标
     */
    private int mCenterX;
    private int mCenterY;
    /**
     * 小球的颜色数组
     */
    private int[] mCircleColors;
    /**
     * 大圆的半径
     */
    private float mRotateRadius = 90;
    /**
     * 小圆的半径
     */
    private float mCircleRadius = 18;
    /**
     * 水波纹的圆圈半径
     */
    private float mHollowRadius = 0;
    /**
     * 屏幕对角线的一般，水波纹动画的圆的半径
     */
    private float mDistance;
    /**
     * 属性动画旋转的角度
     */
    private float mCurrentAngle;
    /**
     * 是否绘制水波纹效果
     */
    private boolean isRipple = false;
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
        mCircleColors = context.getResources().getIntArray(R.array.splash_circle_colors);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);

        mHollowPaint = new Paint();
        mHollowPaint.setAntiAlias(true);
        mHollowPaint.setColor(Color.WHITE);
        mHollowPaint.setStyle(Paint.Style.STROKE);

        initRotateAnimator();
    }

    /**
     * 水波纹动画
     */
    private void initRippleAnimator() {
        ValueAnimator rippleAnimator = ValueAnimator.ofFloat(mCircleRadius, mDistance);
        rippleAnimator.setDuration(1200);
        rippleAnimator.setInterpolator(new LinearInterpolator());
        rippleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                isRipple = true;
                mHollowRadius = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        rippleAnimator.start();
    }

    /**
     * 聚合动画
     */
    private void initAggregationAnimator() {
        ValueAnimator aggregationAnimator = ValueAnimator.ofFloat(mCircleRadius, mRotateRadius);
        aggregationAnimator.setDuration(500);
        aggregationAnimator.setInterpolator(new OvershootInterpolator(10f));
        aggregationAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mRotateRadius = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        aggregationAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                initRippleAnimator();
            }
        });
        aggregationAnimator.reverse();
    }
    /**
     * 旋转动画
     */
    private void initRotateAnimator() {
        ValueAnimator rotateAnimator = ValueAnimator.ofFloat(0, (float) (Math.PI * 2));
        rotateAnimator.setDuration(1000);
        rotateAnimator.setRepeatCount(2);
        rotateAnimator.setInterpolator(new LinearInterpolator());
        rotateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurrentAngle = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        rotateAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                initAggregationAnimator();
            }
        });
        rotateAnimator.start();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = getWidth()/2;
        mCenterY = getHeight()/2;
        //Math.hypot(x,y)返回 sqrt(x2 +y2)
        mDistance = (float) (Math.hypot(w,h)/2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(isRipple){
            float strokeWidth = (mDistance - mHollowRadius);
            mHollowPaint.setStrokeWidth(strokeWidth);
            float radius = strokeWidth / 2 + mHollowRadius;
            canvas.drawCircle(mCenterX,mCenterY,radius,mHollowPaint);
        }else {
            drawSmallCircle(canvas);
        }
    }

    /**
     *  绘制6个小圆
     * @param canvas
     */
    private void drawSmallCircle(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        //一个完整的圆的弧度是2π，计算每个小圆之间的弧度
        float angleEach = (float) (2*Math.PI/mCircleColors.length);
        //循环绘制6个小圆
        for (int i = 0; i < mCircleColors.length; i++) {
            float angle = i*angleEach+mCurrentAngle;
            float x = (float) (Math.cos(angle)*mRotateRadius+mCenterX);
            float y = (float) (Math.sin(angle)*mRotateRadius+mCenterY);
            mPaint.setColor(mCircleColors[i]);
            canvas.drawCircle(x,y,mCircleRadius,mPaint);
        }
    }
}
