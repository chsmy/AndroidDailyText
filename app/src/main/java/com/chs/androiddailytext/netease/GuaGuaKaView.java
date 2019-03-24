package com.chs.androiddailytext.netease;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.chs.androiddailytext.R;

/**
 * 作者：83734
 * 时间：2019/3/24
 * 描述：
 */
public class GuaGuaKaView extends View {
    private Paint mPaint;
    private Bitmap mBitmapRes,mDstBitmap,mSrcBitmap;
    private Path mPath = new Path();
    private float mEventX,mEventY;
    public GuaGuaKaView(Context context) {
        this(context,null);
    }

    public GuaGuaKaView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public GuaGuaKaView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(30);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        //禁止硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE,null);

        mBitmapRes = BitmapFactory.decodeResource(getResources(), R.mipmap.guagua);
        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.gua);
        mDstBitmap = Bitmap.createBitmap(mSrcBitmap.getWidth(),mSrcBitmap.getHeight(), Bitmap.Config.ARGB_8888);
//        mDstBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.gua);
//        mSrcBitmap = Bitmap.createBitmap(mDstBitmap.getWidth(),mDstBitmap.getHeight(), Bitmap.Config.ARGB_8888);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float width = getWidth()/2-mBitmapRes.getWidth()/2;
        float height = getHeight()/2-mBitmapRes.getHeight()/2;
        //绘制结果图片
        canvas.drawBitmap(mBitmapRes,width,height,mPaint);

        int layerId = canvas.saveLayer(0,0,getWidth(),getHeight(),mPaint,Canvas.ALL_SAVE_FLAG);

        //遮罩层绘制在path上面
        //绘制路径
        Canvas dstCanvas = new Canvas(mDstBitmap);
        dstCanvas.drawPath(mPath,mPaint);
        //绘制目标图像
        canvas.drawBitmap(mDstBitmap,width,height,mPaint);

        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));

        canvas.drawBitmap(mSrcBitmap,width,height,mPaint);
//

//        //遮罩层绘制在path下面
//        canvas.drawBitmap(mDstBitmap,width,height,mPaint);
//        Canvas srcCanvas = new Canvas(mSrcBitmap);
//        srcCanvas.drawPath(mPath,mPaint);
//        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
//        canvas.drawBitmap(mSrcBitmap,width,height,mPaint);


        mPaint.setXfermode(null);

        canvas.restoreToCount(layerId);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mEventX = event.getX();
                mEventY = event.getY();
                mPath.moveTo(mEventX,mEventY);
                Log.e("TAG","mEventX"+mEventX+"--mEventY"+mEventY);
                break;
            case MotionEvent.ACTION_MOVE:
                float endX = (mEventX+event.getX())/2;
                float endY = (mEventY+event.getY())/2;
                mPath.quadTo(mEventX,mEventY,endX,endY);
//                mPath.lineTo(event.getX(),event.getY());
                mEventX = event.getX();
                mEventY = event.getY();
                invalidate();
                break;
        }
        return true;
    }
}
