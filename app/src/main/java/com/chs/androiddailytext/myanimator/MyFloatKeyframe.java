package com.chs.androiddailytext.myanimator;

/**
 * @author chs
 * date：2019-04-11 16:03
 * des：关键帧类 保存某一时刻的状态值
 */
public class MyFloatKeyframe {
    float mFraction;
    Class mValueType;
    float mValue;

    public MyFloatKeyframe(float fraction, float value) {
        mFraction = fraction;
        mValueType = float.class;
        mValue = value;
    }

    public float getValue() {
        return mValue;
    }

    public void setValue(float value) {
        mValue = value;
    }

    public float getFraction() {
        return mFraction;
    }
}
