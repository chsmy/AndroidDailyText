package com.chs.androiddailytext.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.chs.androiddailytext.R;
import com.chs.androiddailytext.module.ChartEntity;
import com.chs.androiddailytext.utils.CalculateUtil;
import com.chs.androiddailytext.utils.DensityUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：chs on 2018-04-09 15:39
 * 邮箱：657083984@qq.com
 */
public class BarChart_ extends View {
    private Context mContext;
    /**
     * 视图的宽和高  刻度区域的最大值
     */
    private int mTotalWidth, mTotalHeight,maxHeight;
    /**
     * 画笔 轴 刻度
     */
    private Paint axisPaint,textPaint,barPaint;
    private List<ChartEntity> mData;//数据集合
    /**
     * item中的Y轴最大值
     */
    private float maxYValue;
    /**
     * Y轴最大的刻度值
     */
    private float maxYDivisionValue;
    /**
     * 距离底部的距离
     */
    private int paddingBottom;
    /**
     * 柱子的矩形
     */
    private Rect mBarRect;
    /**
     * 每一个bar的宽度
     */
    private int barWidth;
    /**
     * 每个bar之间的距离
     */
    private int barSpace;
    /**
     * 柱形图左边的x轴坐标 和右边的x轴坐标
     */
    private List<Integer> mBarLeftXPoints = new ArrayList<>();
    private List<Integer> mBarRightXPoints = new ArrayList<>();
    public BarChart_(Context context) {
        super(context);
        init(context);
    }

    public BarChart_(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BarChart_(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        barWidth = DensityUtil.dip2px(getContext(), 20);
        barSpace = DensityUtil.dip2px(getContext(), 20);

        axisPaint = new Paint();
        axisPaint.setColor(ContextCompat.getColor(mContext, R.color.axis));
        axisPaint.setStrokeWidth(1);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(DensityUtil.dip2px(getContext(), 10));

        barPaint = new Paint();
        barPaint.setColor(Color.parseColor("#6FC5F4"));

        mBarRect = new Rect(0, 0, 0, 0);
    }
    public void setData(List<ChartEntity> list) {
        this.mData = list;
        maxYValue = calculateMax(list);
        getRange(maxYValue);
    }

    /**
     * 计算出Y轴最大值
     * @return
     */
    private float calculateMax(List<ChartEntity> list){
        float start = list.get(0).getyValue();
        for (ChartEntity entity : list) {
            if (entity.getyValue() > start) {
                start = entity.getyValue();
            }
        }
        return start;
    }

    /**
     *  得到柱状图的最大和最小的分度值
     */
    private void getRange(float maxYValue) {
        int scale = CalculateUtil.getScale(maxYValue);//获取这个最大数 数总共有几位
        float unScaleValue = (float) (maxYValue / Math.pow(10, scale));//最大值除以位数之后剩下的值  比如1200/1000 后剩下1.2
        maxYDivisionValue = (float) (CalculateUtil.getRangeTop(unScaleValue) * Math.pow(10, scale));//获取Y轴的最大的分度值

    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTotalWidth = w - getPaddingLeft() - getPaddingRight();
        mTotalHeight = h;
        maxHeight = h - getPaddingTop() - getPaddingBottom();
        paddingBottom = getPaddingBottom();
        Log.i("padding",h+"----"+getPaddingBottom()+"----"+getPaddingTop());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制刻度线 和 刻度
        drawScaleLine(canvas);
        //绘制柱子
        drawBar(canvas);
        //绘制X轴的text
        drawXAxisText(canvas);
    }

    private void drawXAxisText(Canvas canvas) {
        float distance = 0;
        float startX = textPaint.measureText(String.valueOf(maxYDivisionValue));
        for (int i = 0; i < mData.size(); i++) {
            distance = startX + barWidth * i + barSpace * (i + 1);
            String text = mData.get(i).getxLabel();
            //当在可见的范围内才绘制
            if ((startX + distance) >= startX && (startX + distance) < (mTotalWidth)) {
                canvas.drawText(text, mBarLeftXPoints.get(i) - (textPaint.measureText(text) - barWidth) / 2, mTotalHeight - paddingBottom + DensityUtil.dip2px(getContext(), 10), textPaint);
            }
        }
    }

    private void drawBar(Canvas canvas) {
        mBarRect.bottom = mTotalHeight - paddingBottom;
        float startX = textPaint.measureText(String.valueOf(maxYDivisionValue));
        for (int i = 0; i < mData.size(); i++) {
            mBarRect.left = (int) (startX + barWidth * i + barSpace * (i + 1));
            mBarRect.top = (int) maxHeight + paddingBottom - (int)((maxHeight * (mData.get(i).getyValue() / maxYDivisionValue)));
            mBarRect.right = mBarRect.left + barWidth;
            mBarLeftXPoints.add(mBarRect.left);
            mBarRightXPoints.add(mBarRect.right);
            canvas.drawRect(mBarRect, barPaint);
        }
    }

    /**
     *  Y轴上的text (1)当最大值大于1 的时候 将其分成5份 计算每个部分的高度  分成几份可以自己定
     * （2）当最大值大于0小于1的时候  也是将最大值分成5份
     * （3）当为0的时候使用默认的值
     */
    private void drawScaleLine(Canvas canvas) {
        float eachHeight = (maxHeight / 5f);
        long textValue = 0;
        float startX = textPaint.measureText(String.valueOf(maxYDivisionValue));
        if (maxYValue > 1) {
            for (int i = 0; i <= 5; i++) {
                float startY = mTotalHeight-paddingBottom-eachHeight * i;
                BigDecimal maxValue = new BigDecimal(maxYDivisionValue);
                    BigDecimal fen = new BigDecimal(0.2 * i);
                    textValue = maxValue.multiply(fen).longValue();
                String text = String.valueOf(textValue);
                canvas.drawText(text, startX-textPaint.measureText(text) -5, startY + textPaint.measureText("0")/2, textPaint);
                canvas.drawLine(startX, startY, mTotalWidth, startY, axisPaint);
            }
        } else if (maxYValue > 0 && maxYValue <= 1) {
            for (int i = 0; i <= 5; i++) {
                float startY = mTotalHeight-paddingBottom-eachHeight * i;
                    textValue = (long) CalculateUtil.numMathMul(maxYDivisionValue, (float) (0.2 * i));
                String text = String.valueOf(textValue);
                canvas.drawText(text,  textPaint.measureText(text) - 5, startY + textPaint.measureText("0")/2, textPaint);
                canvas.drawLine(startX, startY, mTotalWidth, startY, axisPaint);
            }
        } else {
            for (int i = 1; i <= 5; i++) {
                float startY = mTotalHeight-paddingBottom-eachHeight * i;
                String text = String.valueOf(10 * i);
                canvas.drawText(text,textPaint.measureText(text) - 5, startY + textPaint.measureText("0")/2, textPaint);
                canvas.drawLine(startX, startY, mTotalWidth, startY, axisPaint);
            }
        }
    }
}
