package com.chs.androiddailytext.netease;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

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
     *  移动的状态
     */
    private static final int BUBBLE_STATE_MOVE = 0X03;
    /**
     * 爆炸消失的状态
     */
    private static final int BUBBLE_STATE_BLASH = 0X04;
    /**
     * 小球的画笔 文本的画笔 爆炸效果的画笔
     */
    private Paint mBubblePaint,mTextPaint,mbBlastPaint;
    /**
     * 移动圆的半径
     */
    private float mMoveRadius = 20;
    /**
     * 静止圆的半径
     */
    private float mQuitRadius = 20;
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
    private int OFFSET = 5;
    /**
     * 手指点击的坐标和静止圆的原点之间的距离
     */
    private double mDist;

    public QQBubbleView(Context context) {
        this(context,null);
    }

    public QQBubbleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
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
        mTextPaint.setTextSize(16);
        mTextPaint.setColor(Color.WHITE);

        mTextRect = new Rect();

        mbBlastPaint = new Paint();
        mbBlastPaint.setAntiAlias(true);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mQuitPoint.set(w/2,h/2);
        mMovePoint.set(w/2,h/2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
            //默认情况下绘制圆和字
            canvas.drawCircle(mMovePoint.x,mMovePoint.y,mMoveRadius,mBubblePaint);
            mTextPaint.getTextBounds(mText,0,mText.length(),mTextRect);
            canvas.drawText(mText,mMovePoint.x-mTextRect.width()/2,mMovePoint.y+mTextRect.height()/2,mTextPaint);
        if(mState == BUBBLE_STATE_MOVE){
            //绘制静止的圆
            canvas.drawCircle(mQuitPoint.x,mQuitPoint.y,mQuitRadius,mBubblePaint);

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mDist = Math.hypot(x-mQuitPoint.x, y-mQuitPoint.y);
                if(mState == BUBBLE_STATE_DEFAULT){
                  if(mDist-mQuitPoint.x<mMoveRadius+OFFSET){
                      mState = BUBBLE_STATE_CLICK;
                  }else {
                      mState = BUBBLE_STATE_DEFAULT;
                  }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(mState != BUBBLE_STATE_DEFAULT){
                    mState = BUBBLE_STATE_MOVE;
                    mDist = Math.hypot(x-mQuitPoint.x, y-mQuitPoint.y);
                    mMovePoint.x = event.getX();
                    mMovePoint.y = event.getY();
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                break;
                default:
        }
        return true;
    }
}
