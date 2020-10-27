package com.chs.androiddailytext.changeSkin;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.SPUtils;
import com.chs.androiddailytext.R;

import skin.support.widget.SkinCompatBackgroundHelper;
import skin.support.widget.SkinCompatSupportable;
import skin.support.widget.SkinCompatTextHelper;

import static com.chs.androiddailytext.changeSkin.ChangeSkinActivity.STYLE_LIGHT;
import static com.chs.androiddailytext.changeSkin.ChangeSkinActivity.STYLE_TYPE;

/**
 * @author: chs
 * @date: Create in 2020/10/19
 * @description
 */
public class TestView1 extends View implements SkinCompatSupportable {
    private Paint mPaint1;
    private Path mPath1;
    public TestView1(Context context) {
        this(context,null);
    }

    public TestView1(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TestView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint1 = new Paint();
        mPaint1.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
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

    @Override
    public void applySkin() {
        int color = SPUtils.getInstance().getString(STYLE_TYPE).equals(STYLE_LIGHT) ?
                ContextCompat.getColor(getContext(), R.color.colorAccent):ContextCompat.getColor(getContext(), R.color.green);
        mPaint1.setColor(color);
        invalidate();
    }
}
