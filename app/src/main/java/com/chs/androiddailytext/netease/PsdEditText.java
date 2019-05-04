package com.chs.androiddailytext.netease;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;

/**
 * @author：chs date：2019/5/3
 * des：密碼輸入框
 */
public class PsdEditText extends AppCompatEditText {

    private Paint mPaint;
    private int mBgColor = Color.parseColor("#d1d2d6");
    /**
     * 背景边框的大小
     */
    private int mBgSize;
    /**
     * 背景圆角的大小
     */
    private float mBgCorner;

    /**
     * 分割线的颜色
     */
    private int mDivisionLineColor = mBgColor;
    /**
     * 分割线的宽度
     */
    private int mDivisionLineSize;

    /**
     * 密码的个数默认为6位数
     */
    private int mPasswordNumber = 6;
    /**
     * 一个密码所占的宽度
     */
    private int mPasswordItemWidth;
    /**
     * 圆点的半径
     */
    private int mPasswordRadius;
    /**
     * 密码填充完毕
     */
    private PasswordEditText.PasswordFullListener mListener;
    public PsdEditText(Context context) {
        this(context,null);
    }

    public PsdEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * dip 转 px
     */
    private int dip2px(int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dip, getResources().getDisplayMetrics());
    }
    private void init(Context context){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);

        mBgSize = dip2px(1);
        mBgCorner = dip2px(2);
        mDivisionLineSize = dip2px(1);
        mPasswordRadius = dip2px(4);

        // 设置输入模式是密码
        setInputType(EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
        // 不显示光标
        setCursorVisible(false);
        setBackground(new ColorDrawable(Color.TRANSPARENT));

        setKeyListener(new NumberKeyListener() {
            @NonNull
            @Override
            protected char[] getAcceptedChars() {
                return new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'X'};
            }
            /**
             * @return 0：无键盘,键盘弹不出来
             *          1：英文键盘
             *          2：模拟键盘
             *          3：数字键盘
             */
            @Override
            public int getInputType() {
                return 3;
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int passwordWidth = w - (mPasswordNumber - 1) * mDivisionLineSize;
        mPasswordItemWidth = passwordWidth / mPasswordNumber;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制背景
        drawBg(canvas);
        //绘制分割线
        drawLine(canvas);
        //绘制小圆点
        drawCircle(canvas);
    }

    private void drawCircle(Canvas canvas) {
        int passwordLength = getText().length();
        mPaint.setColor(Color.BLACK);
        // 设置画笔为实心
        mPaint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < passwordLength; i++) {
            int cx = i * mDivisionLineSize + i * mPasswordItemWidth + mPasswordItemWidth / 2 + mBgSize;
            canvas.drawCircle(cx, getHeight() / 2f, mPasswordRadius, mPaint);
        }
        // 判断密码是否填充完毕
        if (passwordLength >= mPasswordNumber) {
            //可以执行填完的回调
            Toast.makeText(getContext(),getText().toString(),Toast.LENGTH_SHORT).show();
        }
    }

    private void drawLine(Canvas canvas) {
        mPaint.setStrokeWidth(mDivisionLineSize);
        mPaint.setColor(mDivisionLineColor);
        for (int i = 0; i < mPasswordNumber - 1; i++) {
            int startX = (i + 1) * mDivisionLineSize + (i + 1) * mPasswordItemWidth + mBgSize;
            canvas.drawLine(startX, mBgSize, startX, getHeight() - mBgSize, mPaint);
        }
    }

    private void drawBg(Canvas canvas) {
        mPaint.setColor(mBgColor);
        // 设置画笔为空心
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mBgSize);
        RectF rectF = new RectF(mBgSize, mBgSize, getWidth() - mBgSize, getHeight() - mBgSize);
        // 如果没有设置圆角，就画矩形
        if (mBgCorner == 0) {
            canvas.drawRect(rectF, mPaint);
        } else {
            // 如果有设置圆角就画圆矩形
            canvas.drawRoundRect(rectF, mBgCorner, mBgCorner, mPaint);
        }
    }
    /**
     * 添加密码(自定义键盘用到)
     */
    public void addPassword(String number) {
        number = getText().toString().trim() + number;
        if (number.length() > mPasswordNumber) {
            return;
        }
        setText(number);
    }

    /**
     * 删除最后一位密码(自定义键盘用到)
     */
    public void deleteLastPassword() {
        String currentText = getText().toString().trim();
        if (TextUtils.isEmpty(currentText)) {
            return;
        }
        currentText = currentText.substring(0, currentText.length() - 1);
        setText(currentText);
    }

    /**
     * 设置密码填充满的监听
     */
    public void setOnPasswordFullListener(PasswordEditText.PasswordFullListener listener) {
        this.mListener = listener;
    }

    /**
     * 密码已经全部填满
     */
    public interface PasswordFullListener {
        public void passwordFull(String password);
    }
}
