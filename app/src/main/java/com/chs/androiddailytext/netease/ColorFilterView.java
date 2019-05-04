package com.chs.androiddailytext.netease;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.chs.androiddailytext.R;

/**
 * @author：chs date：2019/5/3
 * des：
 */
public class ColorFilterView extends View {

    private Paint mPaint;
    private Bitmap mBitmap;
    public ColorFilterView(Context context) {
        this(context,null);
    }

    public ColorFilterView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ColorFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context){
        mPaint = new Paint();
        mBitmap  = BitmapFactory.decodeResource(getResources(), R.mipmap.color_filter);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        ColorMatrix colorMatrix = new ColorMatrix();
        //将饱和度设置为0就是灰色图像
        colorMatrix.setSaturation(0);
        ColorMatrixColorFilter colorMatrixFilter = new ColorMatrixColorFilter(colorMatrix);
        mPaint.setColorFilter(colorMatrixFilter);
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);

    }
}
