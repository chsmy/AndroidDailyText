package com.chs.app_jetpack.camera;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.SizeUtils;
import com.chs.app_jetpack.R;


/**
 * author：chs
 * date：2020/5/18
 * des： 录制按钮
 */
public class RecordView extends View implements View.OnLongClickListener, View.OnClickListener {
    private static final int PROGRESS_INTERVAL = 100;
    private int mBgColor;
    private int mStrokeColor;
    private int mStrokeWidth;
    private int mDuration;
    private int mWidth;
    private int mHeight;
    private int mRadius;
    private int mProgressValue;
    private boolean isRecording;
    private RectF mArcRectF;
    private Paint mBgPaint, mProgressPaint;
    private OnRecordListener mOnRecordListener;
    private long mStartRecordTime;

    public RecordView(Context context) {
        this(context, null);
    }

    public RecordView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecordView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RecordView);
        mBgColor = typedArray.getColor(R.styleable.RecordView_bg_color, Color.WHITE);
        mBgColor = typedArray.getColor(R.styleable.RecordView_stroke_color, Color.RED);
        mStrokeWidth = typedArray.getDimensionPixelOffset(R.styleable.RecordView_stroke_width, SizeUtils.dp2px(5));
        mDuration = typedArray.getInteger(R.styleable.RecordView_duration, 10);
        mRadius = typedArray.getDimensionPixelOffset(R.styleable.RecordView_radius, SizeUtils.dp2px(40));
        typedArray.recycle();

        mBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBgPaint.setStyle(Paint.Style.FILL);
        mBgPaint.setColor(mBgColor);

        mProgressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mProgressPaint.setStyle(Paint.Style.STROKE);
        mProgressPaint.setColor(mStrokeColor);
        mProgressPaint.setStrokeWidth(mStrokeWidth);

        mArcRectF = new RectF(mStrokeWidth / 2f, mStrokeWidth / 2f,
                mWidth - mStrokeWidth / 2f, mHeight - mStrokeWidth / 2f);
        setEvent();
    }

    private void setEvent() {
        Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                mProgressValue++;
                postInvalidate();
                if (mProgressValue <= mDuration) {
                    sendEmptyMessageDelayed(0, PROGRESS_INTERVAL);
                } else {
                    finishRecord();
                }
            }
        };
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    isRecording = true;
                    mStartRecordTime = System.currentTimeMillis();
                    handler.sendEmptyMessage(0);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    long duration = System.currentTimeMillis() - mStartRecordTime;
                    if(duration/1000>mDuration){
                        finishRecord();
                    }
                    handler.removeCallbacksAndMessages(null);
                    isRecording = false;
                    mStartRecordTime = 0;
                    mProgressValue = 0;
                    postInvalidate();
                }
                return false;
            }
        });
        setOnClickListener(this);
        setOnLongClickListener(this);
    }

    private void finishRecord() {
         if(mOnRecordListener!=null){
             mOnRecordListener.onFinish();
         }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = w;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(mWidth / 2f, mHeight / 2f, mRadius, mBgPaint);

        if (isRecording) {
            float sweepAngle = 360f * mProgressValue / mDuration;
            canvas.drawArc(mArcRectF, -90, sweepAngle, false, mProgressPaint);
        }

    }

    @Override
    public boolean onLongClick(View v) {
        if(mOnRecordListener!=null){
            mOnRecordListener.onLongClick();
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        if(mOnRecordListener!=null){
            mOnRecordListener.onClick();
        }
    }

    public interface OnRecordListener {
        void onClick();

        void onLongClick();

        void onFinish();
    }

}
