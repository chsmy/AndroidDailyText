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
import android.view.ScaleGestureDetector;
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
public class MyBigView extends View implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener,
        ScaleGestureDetector.OnScaleGestureListener{

    /**
     * 图片的宽和高
     */
    private float mImageWidth,mImageHeight;
    /**
     * 当前View的宽和高
     */
    private float mViewWidth,mViewHeight;
    /**
     * 图片的缩放比
     */
    private float mScale = 1;
    private float mCurrentScale = 1;
    /**
     * 放大几倍
     */
    private int mMultiple = 3;
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
    private ScaleGestureDetector mScaleGestureDetector;

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
        //滑动器
        mScroller = new Scroller(getContext());
        //所放器
        mMatrix = new Matrix();
        //手势识别
        mGestureDetector = new GestureDetector(getContext(),this);
        mScaleGestureDetector = new ScaleGestureDetector(getContext(),this);
    }

    public void setImage(InputStream is){
        mOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(is,null,mOptions);
        mImageWidth = mOptions.outWidth;
        mImageHeight = mOptions.outHeight;
        mOptions.inPreferredConfig = Bitmap.Config.RGB_565;
        mOptions.inJustDecodeBounds = false;
        try {
            //区域解码器
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
        mRect.top = 0;
        mRect.left = 0;
        mRect.right = (int) mViewWidth;
        mRect.bottom = (int) mViewHeight;
        mScale = mViewWidth/mImageWidth;
        mCurrentScale = mScale;
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
        mMatrix.setScale(mCurrentScale,mCurrentScale);
        canvas.drawBitmap(mBitmap,mMatrix,null);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);

        mScaleGestureDetector.onTouchEvent(event);
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
            mRect.right = (int) (mViewWidth/mCurrentScale);
        }
        if(mRect.right>mImageWidth){
            mRect.right = (int) mImageWidth;
            mRect.left = (int) (mImageWidth-mViewWidth/mCurrentScale);
        }
        if(mRect.top<0){
            mRect.top = 0;
            mRect.bottom = (int) (mViewHeight/mCurrentScale);
        }
        if(mRect.bottom>mImageHeight){
            mRect.bottom = (int) mImageHeight;
            mRect.top = (int) (mImageHeight-mViewHeight/mCurrentScale);
        }
        invalidate();
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(!mScroller.isFinished()&&mScroller.computeScrollOffset()){
            if(mRect.top+mViewHeight/mCurrentScale<mImageHeight){
                mRect.top = mScroller.getCurrY();
                mRect.bottom = (int) (mRect.top + mViewHeight/mCurrentScale);
            }
            if(mRect.bottom>mImageHeight) {
                mRect.top = (int) (mImageHeight - mViewHeight/mCurrentScale);
                mRect.bottom = (int) mImageHeight;
            }
            invalidate();
        }
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        mScroller.fling(mRect.left,mRect.top,-(int)velocityX,-(int)velocityY,0,(int)mImageWidth
                ,0,(int)mImageHeight);
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        //处理双击事件
        if(mCurrentScale>mScale){
            mCurrentScale = mScale;
        }else {
            mCurrentScale = mScale*mMultiple;
        }
        mRect.right = mRect.left+(int)(mViewWidth/mCurrentScale);
        mRect.bottom = mRect.top+(int)(mViewHeight/mCurrentScale);
        invalidate();
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        //处理手指缩放事件
        //获取与上次事件相比，得到的比例因子
        float scaleFactor = detector.getScaleFactor();
//        mCurrentScale+=scaleFactor-1;
        mCurrentScale*=scaleFactor;
        if(mCurrentScale>mScale*mMultiple){
            mCurrentScale = mScale*mMultiple;
        }else if(mCurrentScale<=mScale){
            mCurrentScale = mScale;
        }
        mRect.right = mRect.left+(int)(mViewWidth/mCurrentScale);
        mRect.bottom = mRect.top+(int)(mViewHeight/mCurrentScale);
        invalidate();
        return true;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        //当 >= 2 个手指碰触屏幕时调用，若返回 false 则忽略改事件调用
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {

    }
}
