package com.chs.androiddailytext.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author chs
 * date：2019-11-08 16:01
 * des： 加载大图
 */
public class MyBigView extends View implements GestureDetector.OnGestureListener {

    /**
     * 图片的宽和高
     */
    private int mImageWidth,mImageHeight;
    /**
     * 当前View的宽和高
     */
    private int mViewWidth,mViewHeight;
    /**
     * 当前View的宽高和图片宽高的缩放比
     */
    private float mScale;
    /**
     * 绘制区域
     */
    private final Rect mRect = new Rect();
    /**
     * 分区域加载器
     */
    private BitmapRegionDecoder mRegionDecoder;
    private BitmapFactory.Options mOptions;
    private Bitmap mBitmap;
    /**
     * 滑动器
     */
    private Scroller mScroller;
    /**
     * 缩放矩阵
     */
    private Matrix mMatrix;
    /**
     * 手势识别器
     */
    private GestureDetector mGestureDetector;
    public MyBigView(Context context) {
        super(context);
        init();
    }

    public MyBigView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyBigView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mOptions = new BitmapFactory.Options();
        mScroller = new Scroller(getContext());
        mMatrix = new Matrix();
        mGestureDetector = new GestureDetector(getContext(),this);
    }

    public void setImage(InputStream is){
        mOptions.inJustDecodeBounds = false;
        BitmapFactory.decodeStream(is,null,mOptions);
        mImageWidth = mOptions.outWidth;
        mImageHeight = mOptions.outHeight;
        mOptions.inPreferredConfig = Bitmap.Config.RGB_565;
        mOptions.inJustDecodeBounds = true;
        try {
            mRegionDecoder = BitmapRegionDecoder.newInstance(is,false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        requestLayout();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
        mScale = mImageWidth/mViewWidth;
        mRect.top = 0;
        mRect.left = 0;
        mRect.right = Math.min(mImageWidth,mViewWidth);
        mRect.bottom = Math.min(mImageHeight,mViewHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mRegionDecoder == null){
            return;
        }
        //复用内存
        mOptions.inBitmap = mBitmap;
        mBitmap = mRegionDecoder.decodeRegion(mRect,mOptions);
        mMatrix.setScale(mViewWidth/(float)mRect.width(),mViewWidth/(float)mRect.width());
        canvas.drawBitmap(mBitmap,mMatrix,null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        //如果正在滑动，先停止
        if(!mScroller.isFinished()){
            mScroller.forceFinished(true);
        }
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        //滑动的时候，改变mRect显示区域的位置
        mRect.offset((int)distanceX,(int)distanceY);
        //处理上下左右的边界
        if(mRect.left<0){
            mRect.left = 0;
            mRect.right = mViewWidth;
        }
        if(mRect.right>mImageWidth){
            mRect.right = mImageWidth;
            mRect.left = mImageWidth-(int)(mViewWidth/mScale);
        }
        if(mRect.top<0){
            mRect.top = 0;
        }
        if(mRect.bottom>mImageHeight){
            mRect.bottom = mImageHeight;
        }
        invalidate();
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
