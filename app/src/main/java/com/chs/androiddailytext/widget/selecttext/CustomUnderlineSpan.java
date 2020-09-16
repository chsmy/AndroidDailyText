package com.chs.androiddailytext.widget.selecttext;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.ReplacementSpan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.Utils;
import com.chs.androiddailytext.R;

/**
 * @author: chs
 * @date: Create in 2020/9/10
 * @description
 */
public class CustomUnderlineSpan extends ReplacementSpan {

    private int mWidth = -1;
    private Paint mPaint;

    public CustomUnderlineSpan() {
        mPaint = new Paint();
        mPaint.setColor(ContextCompat.getColor(Utils.getApp(), R.color.black));
        mPaint.setStrokeWidth(3);
    }

    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, @Nullable Paint.FontMetricsInt fm) {
        float size = paint.measureText(text, start, end);
        if (fm != null && paint != null) {
            paint.getFontMetricsInt(fm);
        }
        mWidth = (int) size;
        return mWidth;
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
         canvas.drawLine(x,bottom,x+mWidth,bottom,mPaint);
    }
}
