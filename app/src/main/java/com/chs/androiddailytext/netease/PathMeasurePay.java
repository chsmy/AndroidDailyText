package com.chs.androiddailytext.netease;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.chs.androiddailytext.R;


/**
 * @author：chs date：2019/4/30
 * des：支付宝支付动画
 */
public class PathMeasurePay extends View {
    private Paint mPaint = new Paint();
    private Path mPath = new Path();
    private Path mCircleDst = new Path();
    private Path mLineDst1 = new Path();
    private Path mLineDst2 = new Path();
    private Path mLineDst = new Path();
    private PathMeasure pathMeasure;
    private int mCircleRadius;
    /**
     *  圆的中心点
     */
    private float circleX,circleY;
    /**
     * 绘制类型 1是对号  2是叉号
     */
    private int mType;
    /**
     * 动画控制的圆取值范围0-1
     */
    private float mCircleValue;
    /**
     * 动画控制的直线取值范围0-1
     * mType==1的时候代表对号的线
     * mType==2的时候代表叉号的第一条线
     */
    private float mLineValue;
    /**
     * 动画控制的叉号第二条线的取值范围0-1
     */
    private float mLineValue2;
    /**
     * mType==1的时候代表是否绘制对号
     * mType==2的时候代表是否绘制叉号的第一条线
     */
    private boolean isDrawLine = false;
    /**
     * 是否绘制叉号的第二条线
     */
    private boolean isDrawLine2 = false;
    private ValueAnimator animatorCircle;
    private ValueAnimator animatorLine;
    private ValueAnimator animatorLine2;
    private int strockWidth = 6;
    public PathMeasurePay(Context context) {
        this(context,null);
    }

    public PathMeasurePay(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PathMeasurePay(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        circleX = w/2f;
        circleY = h/2f;
        mCircleRadius = Math.min(w/2-strockWidth,h/2-strockWidth);
    }

    private void init(Context context,AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PathMeasurePay);
        int paintColor = a.getColor(R.styleable.PathMeasurePay_color, Color.BLACK);
        mType = a.getInt(R.styleable.PathMeasurePay_type,1);
        a.recycle();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(paintColor);
        mPaint.setStrokeWidth(strockWidth);

        pathMeasure = new PathMeasure();
        animatorCircle = ValueAnimator.ofFloat(0,1);
        animatorCircle.setDuration(1000);

        animatorLine = ValueAnimator.ofFloat(0,1);
        animatorLine.setDuration(mType==1?1000:500);

        animatorLine2 = ValueAnimator.ofFloat(0,1);
        animatorLine2.setDuration(500);

        animatorCircle.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mCircleValue = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        animatorCircle.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isDrawLine = true;
                animatorLine.start();
            }
        });

        animatorLine.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mLineValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animatorLine.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isDrawLine2 = true;
                if(mType == 2){
                    animatorLine2.start();
                }
            }
        });

        animatorLine2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mLineValue2 = (float) animation.getAnimatedValue();
                invalidate();
            }
        });

        animatorCircle.start();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

            mPath.reset();
            mPath.addCircle(getWidth()/2f,getHeight()/2f,mCircleRadius,Path.Direction.CW);
            mCircleDst.reset();
            pathMeasure.setPath(mPath,false);
            float distance = pathMeasure.getLength() * mCircleValue;
            pathMeasure.getSegment(0, distance , mCircleDst, true);
            canvas.drawPath(mCircleDst, mPaint);

            if(mType == 1){
                if(isDrawLine){
                    mPath.reset();
                    mLineDst.reset();
                    mPath.moveTo(circleX-mCircleRadius/2f,circleY);
                    mPath.lineTo(circleX-mCircleRadius/10f,circleY+mCircleRadius/2f);
                    mPath.lineTo(circleX+mCircleRadius/2f,circleY-mCircleRadius/4f);
                    pathMeasure.setPath(mPath,false);
                    float dis = pathMeasure.getLength()*mLineValue;
                    pathMeasure.getSegment(0,dis,mLineDst,true);
                    canvas.drawPath(mLineDst,mPaint);
                }
            }else {
                if(isDrawLine){
                    mPath.reset();
                    mLineDst1.reset();
                    mPath.moveTo(circleX - mCircleRadius/2f,circleY - mCircleRadius/2f);
                    mPath.lineTo(circleX+mCircleRadius/2f,circleY + mCircleRadius/2f);
                    pathMeasure.setPath(mPath,false);
                    float dis = pathMeasure.getLength()*mLineValue;
                    pathMeasure.getSegment(0,dis,mLineDst1,true);
                    canvas.drawPath(mLineDst1,mPaint);
                }
                if(isDrawLine2){
                    mPath.reset();
                    mLineDst2.reset();
                    mPath.moveTo(circleX+mCircleRadius/2f,circleY - mCircleRadius/2f);
                    mPath.lineTo(circleX - mCircleRadius/2f,circleY +mCircleRadius/2f);
                    pathMeasure.setPath(mPath,false);
                    float dis = pathMeasure.getLength()*mLineValue2;
                    pathMeasure.getSegment(0,dis,mLineDst2,true);
                    canvas.drawPath(mLineDst2,mPaint);
                }
            }
    }

    public void reset(){
        isDrawLine = false;
        isDrawLine2 = false;
        animatorCircle.start();
    }
}
