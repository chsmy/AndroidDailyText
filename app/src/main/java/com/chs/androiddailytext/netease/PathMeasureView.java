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
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.chs.androiddailytext.R;

/**
 * author：chs
 * date：2019/3/30
 * des：
 */
public class PathMeasureView extends View {

    private Paint mPaint = new Paint();
    private Bitmap mBitmap;
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
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(getWidth() / 2, getHeight() / 2);

        mPath.reset();
//        mPath.addCircle(0,0,200, Path.Direction.CW);

        //路径
        mPath.lineTo(200, 200);
        mPath.lineTo(300, 200);
        mPath.lineTo(300, 500);
        mPath.lineTo(0, -500);

        canvas.drawPath(mPath, mPaint);

        mFloat += 0.01;
        if (mFloat >= 1){
            mFloat = 0;
        }

        PathMeasure pathMeasure = new PathMeasure(mPath, false);
        //将pos信息和tan信息保存在mMatrix中
        pathMeasure.getMatrix(pathMeasure.getLength() * mFloat, mMatrix,
                PathMeasure.POSITION_MATRIX_FLAG | PathMeasure.TANGENT_MATRIX_FLAG);
        //将图片的旋转坐标调整到图片中心位置
        mMatrix.preTranslate(-mBitmap.getWidth() / 2, -mBitmap.getHeight() / 2);

        canvas.drawBitmap(mBitmap,mMatrix, mPaint);

        invalidate();

    }
    private Matrix mMatrix = new Matrix();
    private Path mPath = new Path();
    private float mFloat;

}
