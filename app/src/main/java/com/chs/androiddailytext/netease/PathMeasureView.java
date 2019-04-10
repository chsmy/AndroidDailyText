package com.chs.androiddailytext.netease;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import com.chs.androiddailytext.R;

import androidx.annotation.Nullable;

/**
 * author：chs
 * date：2019/3/30
 * des：
 */
public class PathMeasureView extends View {

    private Paint mPaint = new Paint();
    private Bitmap mBitmap;
    private Matrix mMatrix = new Matrix();

    private Path mPath = new Path();
    private Path mDst = new Path();
    private float mFloat;
    private PathMeasure pathMeasure;
    public PathMeasureView(Context context) {
        this(context,null);
    }

    public PathMeasureView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PathMeasureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(4);

        //缩小图片
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.arrow,options);
        pathMeasure = new PathMeasure();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        mPath.reset();

        mFloat += 0.01;
        if (mFloat >= 1){
            mFloat = 0;
        }
        //路径
//        mPath.lineTo(0, 200);
//        mPath.lineTo(300, 200);
//        mPath.quadTo(450,100,600,200);
//        mPath.lineTo(900, 200);
//        pathMeasure.setPath(mPath,false);
//        //将pos信息和tan信息保存在mMatrix中
//        pathMeasure.getMatrix(pathMeasure.getLength() * mFloat, mMatrix,
//                PathMeasure.POSITION_MATRIX_FLAG | PathMeasure.TANGENT_MATRIX_FLAG);
//        //将图片的旋转坐标调整到图片中心位置
//        mMatrix.preTranslate(-mBitmap.getWidth() / 2, -mBitmap.getHeight() / 2);
//        canvas.drawPath(mPath, mPaint);
//        canvas.drawBitmap(mBitmap,mMatrix, mPaint);

//        mPath.addCircle(getWidth()/2,getHeight()/2,200,Path.Direction.CW);
//        pathMeasure.setPath(mPath,false);
//
//        //用来记录位置
//        float []pos = new float[2];
//        //用来记录切点的位置
//        float []tan = new float[2];
//        float distance = pathMeasure.getLength() * mFloat;
//        pathMeasure.getPosTan(distance,pos,tan);
//        //计算出当前图片要旋转的角度
//        float degree = (float) (Math.atan2(tan[1], tan[0]) * 180 / Math.PI);
//        mMatrix.reset();
//        //设置旋转角度和旋转中心
//        mMatrix.postRotate(degree,mBitmap.getWidth() / 2,mBitmap.getHeight() / 2);
//        //设置绘制的中心点与当前图片中心点重合
//        mMatrix.postTranslate(pos[0]-mBitmap.getWidth() / 2,pos[1]-mBitmap.getHeight()/2);
//        canvas.drawPath(mPath, mPaint);
//        canvas.drawBitmap(mBitmap,mMatrix, mPaint);



        mPath.addCircle(getWidth()/2,getHeight()/2,200,Path.Direction.CW);
        mDst.reset();
        pathMeasure.setPath(mPath,false);
        float distance = pathMeasure.getLength() * mFloat;
        pathMeasure.getSegment(2*distance/3, distance, mDst, true);
        canvas.drawPath(mDst, mPaint);


        invalidate();

    }
}
