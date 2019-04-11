package com.chs.androiddailytext.myanimator;

import android.view.View;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author chs
 * date：2019-04-11 15:59
 * des：
 */
public class MyFloatPropertyValuesHolder {

    String mPropertyName;
    Class mValueType;
    MyKeyframeSet mKeyframes;
    Method mSetter = null;

    public MyFloatPropertyValuesHolder(String propertyName, float... values) {
        mPropertyName = propertyName;
        mValueType = float.class;
        mKeyframes = MyKeyframeSet.ofFloat(values);
    }

    /**
     * 执行View 的相关的set 方法
     * @param target view
     */
    public void setupSetter(WeakReference<View> target) {
        //第一个字符大写 比如传过来的 translationX
        char firstLetter = Character.toUpperCase(mPropertyName.charAt(0));
        String theRest = mPropertyName.substring(1);
        //拼成 setTranslationX 方法
        String methodName = "set"+firstLetter+theRest;
        try {
            //通过反射拿到这个View的setTranslationX方法
            mSetter = View.class.getMethod(methodName, float.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     *  设置动画的值 执行setTranslationX方法
     * @param target view
     * @param fraction 百分比
     */
    public void setAnimatedValue(View target, float fraction) {
        Object value = mKeyframes.getValue(fraction);
        try {
            mSetter.invoke(target,value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
