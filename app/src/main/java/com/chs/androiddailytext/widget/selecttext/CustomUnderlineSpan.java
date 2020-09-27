package com.chs.androiddailytext.widget.selecttext;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.ReplacementSpan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.Utils;
import com.chs.androiddailytext.R;

/**
 * @author: chs
 * @date: Create in 2020/9/10
 * @description 下划线
 */
public class CustomUnderlineSpan extends ReplacementSpan {

    private int mWidth = -1;
    private Paint mPaint;
    private Paint mTextPaint;
    private boolean isDrawableText;

    public CustomUnderlineSpan() {
        //默认为填空题 只有线没有文字
        this(R.color.black,0,3,false);
    }

    public CustomUnderlineSpan(int lineColorId, int textColorId,float lineHeight,boolean isDrawableText) {
        mPaint = new Paint();
        mPaint.setColor(ContextCompat.getColor(Utils.getApp(), lineColorId));
        mPaint.setStrokeWidth(lineHeight);
        if(isDrawableText){
            this.isDrawableText = true;
            mTextPaint = new Paint();
            mTextPaint.setAntiAlias(true);
            mTextPaint.setColor(ContextCompat.getColor(Utils.getApp(), textColorId));
            mTextPaint.setTextSize(SizeUtils.sp2px(19));
            mTextPaint.setStrokeWidth(lineHeight);
        }
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
         if(isDrawableText){
             canvas.drawText(text,start,end,x,y,mTextPaint);
         }
    }
}
