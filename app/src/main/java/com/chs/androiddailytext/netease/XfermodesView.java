package com.chs.androiddailytext.netease;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者：83734
 * 时间：2019/3/24
 * 描述：
 */
public class XfermodesView extends View {

    static Bitmap makeDst(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        p.setColor(0xFFFFCC44);
        c.drawOval(new RectF(0, 0, w*3/4, h*3/4), p);
        return bm;
    }

    // create a bitmap with a rect, used for the "src" image
    static Bitmap makeSrc(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        p.setColor(0xFF66AAFF);
        c.drawRect(w/3, h/3, w*19/20, h*19/20, p);
        return bm;
    }


    private static final int W = 64;
    private static final int H = 64;
    private static final int ROW_MAX = 4;   // number of samples per row

    private Bitmap mSrcB;
    private Bitmap mDstB;
    private Shader mBG;     // background checker-board pattern

    private static final Xfermode[] sModes = {
            //所绘制的不会提交到画布上
            new PorterDuffXfermode(PorterDuff.Mode.CLEAR),
            //显示上层绘制的图片
            new PorterDuffXfermode(PorterDuff.Mode.SRC),
            //显示下层绘制的图片
            new PorterDuffXfermode(PorterDuff.Mode.DST),
            //上下层都显示，上层层居上
            new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER),
            //上下层都显示，下层居上
            new PorterDuffXfermode(PorterDuff.Mode.DST_OVER),
            //取两层绘制的交集，显示上层
            new PorterDuffXfermode(PorterDuff.Mode.SRC_IN),
            //取两层绘制的交集，显示下层
            new PorterDuffXfermode(PorterDuff.Mode.DST_IN),
            //取上层绘制的非交集部分，交集部分变成透明
            new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT),
            //取下层绘制的非交集部分，交集部分变成透明
            new PorterDuffXfermode(PorterDuff.Mode.DST_OUT),
            //取上层的交集部分和下层的非交集部分
            new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP),
            //取下层交集部分和上层非交集部分
            new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP),
            //除去两层的交集部分
            new PorterDuffXfermode(PorterDuff.Mode.XOR),
            //取全部区域，交集部分颜色加深
            new PorterDuffXfermode(PorterDuff.Mode.DARKEN),
            //取两图层全部区域，交集部分颜色点亮
            new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN),
            //取两层交集部分，颜色加深
            new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY),
            //取两图层全部区域，交集部分变为透明色
            new PorterDuffXfermode(PorterDuff.Mode.SCREEN),
    };

    private static final String[] sLabels = {
            "Clear", "Src", "Dst", "SrcOver",
            "DstOver", "SrcIn", "DstIn", "SrcOut",
            "DstOut", "SrcATop", "DstATop", "Xor",
            "Darken", "Lighten", "Multiply", "Screen"
    };

    public XfermodesView(Context context) {
        super(context);
        init();
    }

    public XfermodesView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public XfermodesView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mSrcB = makeSrc(W, H);
        mDstB = makeDst(W, H);

        // make a ckeckerboard pattern
        Bitmap bm = Bitmap.createBitmap(new int[] { 0xFFFFFFFF, 0xFFCCCCCC,
                        0xFFCCCCCC, 0xFFFFFFFF }, 2, 2,
                Bitmap.Config.RGB_565);
        mBG = new BitmapShader(bm,
                Shader.TileMode.REPEAT,
                Shader.TileMode.REPEAT);
        Matrix m = new Matrix();
        m.setScale(6, 6);
        mBG.setLocalMatrix(m);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);

        Paint labelP = new Paint(Paint.ANTI_ALIAS_FLAG);
        labelP.setTextAlign(Paint.Align.CENTER);

        Paint paint = new Paint();
        paint.setFilterBitmap(false);

        canvas.translate(15, 35);
        //禁止硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE,paint);
        int x = 0;
        int y = 0;
        for (int i = 0; i < sModes.length; i++) {
            // draw the border
            paint.setStyle(Paint.Style.STROKE);
            paint.setShader(null);
            canvas.drawRect(x - 0.5f, y - 0.5f,
                    x + W + 0.5f, y + H + 0.5f, paint);

            // draw the checker-board pattern
            paint.setStyle(Paint.Style.FILL);
            paint.setShader(mBG);
            canvas.drawRect(x, y, x + W, y + H, paint);

            // 图层保存
            int sc = canvas.saveLayer(x, y, x + W, y + H, null,
                    Canvas.MATRIX_SAVE_FLAG |
                    Canvas.CLIP_SAVE_FLAG |
                    Canvas.HAS_ALPHA_LAYER_SAVE_FLAG |
                    Canvas.FULL_COLOR_LAYER_SAVE_FLAG |
                    Canvas.CLIP_TO_LAYER_SAVE_FLAG);
            canvas.translate(x, y);
            canvas.drawBitmap(mDstB, 0, 0, paint);
            paint.setXfermode(sModes[i]);
            canvas.drawBitmap(mSrcB, 0, 0, paint);
            paint.setXfermode(null);
            //图层恢复
            canvas.restoreToCount(sc);

            // draw the label
            canvas.drawText(sLabels[i],
                    x + W/2, y - labelP.getTextSize()/2, labelP);

            x += W + 10;

            // wrap around when we've drawn enough for one row
            if ((i % ROW_MAX) == ROW_MAX - 1) {
                x = 0;
                y += H + 30;
            }
        }
    }
}
