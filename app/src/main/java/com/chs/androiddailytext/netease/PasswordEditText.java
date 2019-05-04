package com.chs.androiddailytext.netease;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.appcompat.widget.AppCompatEditText;

/**
 * @author：chs date：2019/5/3
 * des：
 */
public class PasswordEditText extends AppCompatEditText {
    // 画笔
    private Paint mPaint;
    // 一个密码所占的宽度
    private int mPasswordItemWidth;
    // 密码的个数默认为6位数
    private int mPasswordNumber = 6;
    // 背景边框颜色
    private int mBgColor = Color.parseColor("#d1d2d6");
    // 背景边框大小
    private int mBgSize = 1;
    // 背景边框圆角大小
    private int mBgCorner = 0;
    // 分割线的颜色
    private int mDivisionLineColor = mBgColor;
    // 分割线的大小
    private int mDivisionLineSize = 1;
    // 密码圆点的颜色
    private int mPasswordColor = mDivisionLineColor;
    // 密码圆点的半径大小
    private int mPasswordRadius = 4;
    private PasswordFullListener mListener;

    public PasswordEditText(Context context) {
        this(context, null);
    }

    public PasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        initAttributeSet(context, attrs);
        // 设置输入模式是密码
        setInputType(EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
        // 不显示光标
        setCursorVisible(false);
        setBackground(new ColorDrawable(Color.TRANSPARENT));
    }

    /**
     * 初始化属性
     */
    private void initAttributeSet(Context context, AttributeSet attrs) {
//        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.PasswordEditText);
//        // 获取大小
//        mDivisionLineSize = (int) array.getDimension(R.styleable.PasswordEditText_divisionLineSize, dip2px(mDivisionLineSize));
//        mPasswordRadius = (int) array.getDimension(R.styleable.PasswordEditText_passwordRadius, dip2px(mPasswordRadius));
//        mBgSize = (int) array.getDimension(R.styleable.PasswordEditText_bgSize, dip2px(mBgSize));
//        mBgCorner = (int) array.getDimension(R.styleable.PasswordEditText_bgCorner, 0);
//        // 获取颜色
//        mBgColor = array.getColor(R.styleable.PasswordEditText_bgColor, mBgColor);
//        mDivisionLineColor = array.getColor(R.styleable.PasswordEditText_divisionLineColor, mDivisionLineColor);
//        mPasswordColor = array.getColor(R.styleable.PasswordEditText_passwordColor, mDivisionLineColor);
//        array.recycle();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    }

    /**
     * dip 转 px
     */
    private int dip2px(int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dip, getResources().getDisplayMetrics());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int passwordWidth = getWidth() - (mPasswordNumber - 1) * mDivisionLineSize;
        mPasswordItemWidth = passwordWidth / mPasswordNumber;
        // 绘制背景
        drawBg(canvas);
        // 绘制分割线
        drawDivisionLine(canvas);
        // 绘制密码
        drawHidePassword(canvas);
    }

    /**
     * 绘制背景
     */
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
     * 绘制隐藏的密码
     */
    private void drawHidePassword(Canvas canvas) {
        int passwordLength = getText().length();
        mPaint.setColor(mPasswordColor);
        // 设置画笔为实心
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLACK);
        for (int i = 0; i < passwordLength; i++) {
            int cx = i * mDivisionLineSize + i * mPasswordItemWidth + mPasswordItemWidth / 2 + mBgSize;
            canvas.drawCircle(cx, getHeight() / 2f, mPasswordRadius, mPaint);
        }
        // 判断密码是否填充完毕
        if (passwordLength >= mPasswordNumber) {
            // 代表密码已经填充满了
            if (mListener != null) {
                mListener.passwordFull(getText().toString().trim());
            }
        }
    }

    /**
     * 绘制分割线
     */
    private void drawDivisionLine(Canvas canvas) {
        mPaint.setStrokeWidth(mDivisionLineSize);
        mPaint.setColor(mDivisionLineColor);
        for (int i = 0; i < mPasswordNumber - 1; i++) {
            int startX = (i + 1) * mDivisionLineSize + (i + 1) * mPasswordItemWidth + mBgSize;
            canvas.drawLine(startX, mBgSize, startX, getHeight() - mBgSize, mPaint);
        }
    }

    /**
     * 添加密码
     */
    public void addPassword(String number) {
        number = getText().toString().trim() + number;
        if (number.length() > mPasswordNumber) {
            return;
        }
        setText(number);
    }

    /**
     * 删除最后一位密码
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
    public void setOnPasswordFullListener(PasswordFullListener listener) {
        this.mListener = listener;
    }

    /**
     * 密码已经全部填满
     */
    public interface PasswordFullListener {
        public void passwordFull(String password);
    }
}
