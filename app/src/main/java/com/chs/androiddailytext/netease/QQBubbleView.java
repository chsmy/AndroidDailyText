package com.chs.androiddailytext.netease;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

import com.chs.androiddailytext.R;

/**
 * @author chs
 * date：2019-03-28 09:55
 * des：
 */
public class QQBubbleView extends View {

    /**
     * 默认状态
     */
    private static final int BUBBLE_STATE_DEFAULT = 0X01;
    /**
     * 手指点击到圆上的状态
     */
    private static final int BUBBLE_STATE_CLICK = 0X02;
    /**
     * 移动的状态 运动球跟静止球分离
     */
    private static final int BUBBLE_STATE_BREAK = 0X03;
    /**
     * 爆炸消失的状态
     */
    private static final int BUBBLE_STATE_BLAST = 0X04;
    /**
     * 小球的画笔 文本的画笔 爆炸效果的画笔
     */
    private Paint mBubblePaint, mTextPaint, mBlastPaint;
    /**
     * 移动圆的半径
     */
    private float mMoveRadius = 40;
    /**
     * 静止圆的半径
     */
    private float mQuitRadius = 40;
    /**
     * 静止圆的圆心点
     */
    private PointF mQuitPoint;
    /**
     * 移动的圆的圆心点
     */
    private PointF mMovePoint;
    private String mText = "22";
    private Rect mTextRect;
    /**
     * 当前的状态
     */
    private int mState = BUBBLE_STATE_DEFAULT;

    /**
     * 手指点击到圆附近的偏移
     */
    private float MOVE_OFFSET = mMoveRadius / 4;
    /**
     * 最大移动距离 静止球消失
     */
    private float mMaxDist = 8 * mMoveRadius;
    /**
     * 手指点击的坐标和静止圆的原点之间的距离
     */
    private float mDist;

    private Path mBezierPath;

    /**
     * 气泡爆炸图片
     */
    private int[] mBlastDrawablesArray = {R.mipmap.burst_1, R.mipmap.burst_2, R.mipmap.burst_3, R.mipmap.burst_4, R.mipmap.burst_5};
    /**
     * 气泡爆炸的bitmap数组
     */
    private Bitmap[] mBlastBitmapsArray;
    /**
     * 爆炸图片的位置
     */
    private int mBlastIndex;
    /**
     * 爆炸的区域
     */
    private RectF mBlastRect;



    public QQBubbleView(Context context) {
        this(context, null);
    }

    public QQBubbleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QQBubbleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mBubblePaint = new Paint();
        mBubblePaint.setAntiAlias(true);
        mBubblePaint.setStyle(Paint.Style.FILL);
        mBubblePaint.setColor(Color.RED);

        mMovePoint = new PointF();
        mQuitPoint = new PointF();

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.STROKE);
        mTextPaint.setTextSize(18);
        mTextPaint.setColor(Color.WHITE);

        mTextRect = new Rect();

        mBlastPaint = new Paint();
        mBlastPaint.setAntiAlias(true);
        mBlastPaint.setFilterBitmap(true);

        mBezierPath = new Path();
        mBlastBitmapsArray = new Bitmap[5];
        for (int i = 0; i < mBlastDrawablesArray.length; i++) {
            mBlastBitmapsArray[i] = BitmapFactory.decodeResource(getResources(), mBlastDrawablesArray[i]);
        }
        mBlastRect = new RectF();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mQuitPoint.set(w / 2, h / 2);
        mMovePoint.set(w / 2, h / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //只要不是爆炸的情况都要绘制圆和字
        if (mState != BUBBLE_STATE_BLAST) {
            canvas.drawCircle(mMovePoint.x, mMovePoint.y, mMoveRadius, mBubblePaint);
            mTextPaint.getTextBounds(mText, 0, mText.length(), mTextRect);
            canvas.drawText(mText, mMovePoint.x - mTextRect.width() / 2, mMovePoint.y + mTextRect.height() / 2, mTextPaint);
        }
        //链接状态绘制静止的圆和赛贝尔曲线
        if (mState == BUBBLE_STATE_CLICK) {
            //绘制静止的圆
            canvas.drawCircle(mQuitPoint.x, mQuitPoint.y, mQuitRadius, mBubblePaint);
            //绘制贝塞尔曲线
            //找到控制点
            float controlX = (mMovePoint.x + mQuitPoint.x) / 2;
            float controlY = (mMovePoint.y + mQuitPoint.y) / 2;
            //计算角度 在直角三角形中,非直角的sin值等于对边长比斜边长.使用勾股定理计算即可
            //sinA=对边/斜边  cosB=邻边/斜边   tanA=对边/邻边
            float sinThet = (mMovePoint.y - mQuitPoint.y) / mDist;
            float cosThet = (mMovePoint.x - mQuitPoint.x) / mDist;

            //A点
            float ax = mQuitPoint.x - mQuitRadius * sinThet;
            float ay = mQuitPoint.y + mQuitRadius * cosThet;
            //B点
            float bx = mMovePoint.x - mMoveRadius * sinThet;
            float by = mMovePoint.y + mMoveRadius * cosThet;
            //C点
            float cx = mMovePoint.x + mMoveRadius * sinThet;
            float cy = mMovePoint.y - mMoveRadius * cosThet;
            //D点
            float dx = mQuitPoint.x + mQuitRadius * sinThet;
            float dy = mQuitPoint.y - mQuitRadius * cosThet;

            //设置path的路径
            mBezierPath.reset();
            mBezierPath.moveTo(ax, ay);
            mBezierPath.quadTo(controlX, controlY, bx, by);

            mBezierPath.lineTo(cx, cy);
            mBezierPath.quadTo(controlX, controlY, dx, dy);
            mBezierPath.close();
            canvas.drawPath(mBezierPath, mBubblePaint);
        }
        //爆炸状态绘制爆炸图片
        if (mState == BUBBLE_STATE_BLAST && mBlastIndex < mBlastDrawablesArray.length) {
            mBlastRect.left = mMovePoint.x - mMoveRadius;
            mBlastRect.top = mMovePoint.y - mMoveRadius;
            mBlastRect.right = mMovePoint.x + mMoveRadius;
            mBlastRect.bottom = mMovePoint.y + mMoveRadius;
            canvas.drawBitmap(mBlastBitmapsArray[mBlastIndex], null, mBlastRect, mBlastPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //勾股定理算出点击位置和静止圆的圆心距离
                mDist = (float) Math.hypot(x - mQuitPoint.x, y - mQuitPoint.y);
                if (mState == BUBBLE_STATE_DEFAULT) {
                    if (mDist < mMoveRadius + MOVE_OFFSET) {
                        mState = BUBBLE_STATE_CLICK;
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mState != BUBBLE_STATE_DEFAULT) {
                    mDist = (float) Math.hypot(x - mQuitPoint.x, y - mQuitPoint.y);
                    mMovePoint.x = event.getX();
                    mMovePoint.y = event.getY();
                    if (mState == BUBBLE_STATE_CLICK) {
                        if (mDist < mMaxDist - MOVE_OFFSET) {
                            mQuitRadius = (mMoveRadius - mDist / 8);
                        } else {
                            mState = BUBBLE_STATE_BREAK;
                        }
                    }
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                //如果还没断开直接返回原状
                if (mState == BUBBLE_STATE_CLICK) {
                    //执行回弹动画
                    startBackAnim();
                }
                //断开了
                else if (mState == BUBBLE_STATE_BREAK) {
                    //如果断开了，小球的位置移动到距离2倍移动小球的距离以内也返回原状
                    if (mDist < mMoveRadius * 2) {
                        //执行回弹动画
                        startBackAnim();
                    } else {
                        mState = BUBBLE_STATE_BLAST;
                        //执行爆炸动画
                        startBlastAnim();
                    }
                }
                break;
            default:
        }
        return true;
    }

    private void startBlastAnim() {
        ValueAnimator animator = ValueAnimator.ofInt(0, 5);
        animator.setDuration(500);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mBlastIndex = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
    }

    private void startBackAnim() {
        PointF start = new PointF(mMovePoint.x, mMovePoint.y);
        PointF end = new PointF(mQuitPoint.x, mQuitPoint.y);
        //系统的PointFEvaluator只能支持21以上的,编译不通过。所以自己弄了一个把它代码抄过来就行啦
        ValueAnimator animator = ValueAnimator.ofObject(new MyPointFEvaluator(), start, end);
        animator.setDuration(200);
        animator.setInterpolator(new OvershootInterpolator(5f));
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mMovePoint = (PointF) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mState = BUBBLE_STATE_DEFAULT;
            }
        });
        animator.start();
    }
}
