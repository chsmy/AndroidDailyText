package com.chs.androiddailytext.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.chs.androiddailytext.R;

/**
 * 作者：chs on 2017-10-26 17:56
 * 邮箱：657083984@qq.com
 */

public class MyView extends View {
    private Paint mPaint;
    private Paint mPaint1;
    private Paint mPaint2;
    private Paint mPaint3;
    private Paint mPaint4;
    private Paint mPaint5;
    private Paint mTextPaint;
    private Path mPath;
    private Path mPath1;

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint1 = new Paint();
        mPaint2 = new Paint();
        mPaint3 = new Paint();
        mPaint4 = new Paint();
        mPaint5 = new Paint();
        mTextPaint = new Paint();
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        mPaint.setStrokeWidth(20);
        mPaint.setStyle(Paint.Style.STROKE); //
        mPaint.setStrokeCap(Paint.Cap.SQUARE);

        mPaint1.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        mPaint1.setStyle(Paint.Style.FILL); // 填充模式
        mPaint1.setStrokeCap(Paint.Cap.SQUARE);

        mTextPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        mTextPaint.setStyle(Paint.Style.FILL); // 填充模式
        mTextPaint.setTextSize(60);
        mTextPaint.setStrokeCap(Paint.Cap.SQUARE);

        mPath = new Path();
        mPath1 = new Path();

        //线性渐变
        Shader shader = new LinearGradient(600,300,1000,700, Color.RED,
                Color.BLUE, Shader.TileMode.CLAMP);
        mPaint2.setShader(shader);

        //辐射渐变
        Shader shader1 = new RadialGradient(800, 800, 200, Color.RED,
                Color.BLUE, Shader.TileMode.CLAMP);
        mPaint3.setShader(shader1);

        //扫描渐变
        Shader shader2 = new SweepGradient(800, 1100, Color.RED,
                Color.BLUE);
        mPaint4.setShader(shader2);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.shader);
        Shader shader3 = new BitmapShader(bitmap, Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);//CLAMP 拉伸 REPEAT 重复 MIRROR 镜像
        mPaint5.setShader(shader3);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPoint(300,300,mPaint);


        canvas.drawArc(200, 100, 800, 500, -110, 100, true, mPaint); // 绘制扇

        //addArc() 只是一个直接使用了 forceMoveTo = true 的简化版 arcTo() 。
        // forceMoveTo 参数的意思是，绘制是要「抬一下笔移动过去」，还是「直接拖着笔过去」，区别在于是否留下移动的痕迹。
        mPath.addArc(200, 200, 400, 400, -225, 225);
        mPath.arcTo(400, 200, 600, 400, -180, 225, false);
        mPath.lineTo(400, 542);
        mPath.close();
        canvas.drawPath(mPath,mPaint);

        mPath1.addArc(200,600,400,800,-225,225);
        mPath1.arcTo(400, 600, 600, 800, -180, 225, false);
        mPath1.lineTo(400, 942);
        canvas.drawPath(mPath1,mPaint1);

        canvas.drawCircle(800, 500, 200, mPaint2);
        canvas.drawCircle(800, 800, 200, mPaint3);
        canvas.drawCircle(800, 1100, 200, mPaint4);
        canvas.drawCircle(100, 100, 100, mPaint5);
        canvas.drawCircle(400, 1100, 100, mPaint5);

        // text 是文字内容，x 和 y 是文字的坐标。但需要注意：这个坐标并不是文字的左上角，而是一个与左下角比较接近的位置
        //drawText() 参数中的 y ，指的是文字的基线（ baseline ） 的位置
        canvas.drawText("hello world",200,1200,mTextPaint);
    }

}
