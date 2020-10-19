package com.chs.androiddailytext.changeSkin;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.chs.androiddailytext.R;

/**
 * @author: chs
 * @date: Create in 2020/10/19
 * @description
 */
public class TestView extends View {
    private Paint mPaint1;
    private Path mPath1;
    public TestView(Context context) {
        this(context,null);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint1 = new Paint();
        int color = ThemeUtil.getThemeType()==1?
                ContextCompat.getColor(getContext(), R.color.colorAccent):ContextCompat.getColor(getContext(), R.color.green);
        mPaint1.setColor(color);
        mPaint1.setStyle(Paint.Style.FILL); // 填充模式
        mPaint1.setStrokeCap(Paint.Cap.SQUARE);

        mPath1 = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath1.addArc(200,600,400,800,-225,225);
        mPath1.arcTo(400, 600, 600, 800, -180, 225, false);
        mPath1.lineTo(400, 942);
        canvas.drawPath(mPath1,mPaint1);
    }
}
