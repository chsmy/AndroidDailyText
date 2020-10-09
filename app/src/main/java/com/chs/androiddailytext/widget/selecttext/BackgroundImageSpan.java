package com.chs.androiddailytext.widget.selecttext;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.style.ReplacementSpan;
import android.util.Log;

public class BackgroundImageSpan extends ReplacementSpan {
    private static final String TAG = "BackgroundImageSpan";
    private Drawable mDrawable;
    private int mWidth = -1;
    private WordBoundaryListener mListener;


    public BackgroundImageSpan(Drawable drawable,WordBoundaryListener listener) {
        mDrawable = drawable;
        mListener = listener;
    }


    public void draw(Canvas canvas, int width, float x, int top, int y, int bottom, Paint paint) {
        if (mDrawable == null) {
            Log.e(TAG, "mDrawable is null draw()");
            return;
        }
        Drawable drawable = mDrawable;
        canvas.save();
        canvas.translate(x, top); // translate to the left top point
        mDrawable.setBounds(0, 0, width, (bottom - top));
        drawable.draw(canvas);
        canvas.restore();
    }

    @Override
    public void updateDrawState(TextPaint tp) {
    }

    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        // draw image
        draw(canvas, mWidth, x, top, y, bottom, paint);
        // draw text
        // the paint is already updated
        canvas.drawText(text, start, end, x, y, paint);
        if(mListener!=null){
            Log.i("event",top+">>>>>>>======");
            mListener.boundary(top,bottom);
        }
    }

    public int getSize(Paint paint, CharSequence text, int start, int end,
                       Paint.FontMetricsInt fm) {
        float size = paint.measureText(text, start, end);
        if (fm != null && paint != null) {
            paint.getFontMetricsInt(fm);
        }
        mWidth = (int) size;
        return mWidth;
    }
}