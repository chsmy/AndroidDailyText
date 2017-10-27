package com.chs.androiddailytext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 作者：chs on 2017-10-26 17:56
 * 邮箱：657083984@qq.com
 */

public class MyView extends View {
    private Paint mPaint;
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
        mPaint.setColor(ContextCompat.getColor(getContext(),R.color.colorAccent));
        mPaint.setStrokeWidth(20);
        mPaint.setStyle(Paint.Style.STROKE); // 填充模式
        mPaint.setStrokeCap(Paint.Cap.SQUARE);

        mPath = new Path();
        mPath1 = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i("tagg","canvas");
        canvas.drawPoint(300,300,mPaint);


        canvas.drawArc(200, 100, 800, 500, -110, 100, true, mPaint); // 绘制扇

        mPath.addArc(200, 200, 400, 400, -225, 225);
        mPath.arcTo(400, 200, 600, 400, -180, 225, false);
        mPath.lineTo(400, 542);
        mPath.close();
        canvas.drawPath(mPath,mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPath1.addArc(200,600,400,800,-225,225);
        mPath1.arcTo(400, 600, 600, 800, -180, 225, false);
        mPath1.lineTo(400, 942);
        canvas.drawPath(mPath1,mPaint);
    }

}
