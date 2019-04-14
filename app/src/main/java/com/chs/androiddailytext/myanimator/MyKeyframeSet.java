package com.chs.androiddailytext.myanimator;

import android.animation.FloatEvaluator;
import android.animation.TypeEvaluator;
import android.util.Log;

import java.util.Arrays;
import java.util.List;

/**
 * @author chs
 * date：2019-04-11 16:02
 * des：
 */
public class MyKeyframeSet {
    /**
     * 类型估值器
     */
    TypeEvaluator mEvaluator;
    /**
     * 第一帧
     */
    MyFloatKeyframe mFirstKeyframe;
    /**
     * 帧的集合
     */
    List<MyFloatKeyframe> mKeyframes;

    private MyKeyframeSet(MyFloatKeyframe ... keyframes){
        mKeyframes = Arrays.asList(keyframes);
        mEvaluator = new FloatEvaluator();
        mFirstKeyframe = keyframes[0];
    }

    public static MyKeyframeSet ofFloat(float[] values) {
        //开始组装每一帧
        int numKeyframes = values.length;
        MyFloatKeyframe keyframes[] = new MyFloatKeyframe[numKeyframes];
        //先放入第一帧
        keyframes[0] = new MyFloatKeyframe(0, values[0]);
        for (int i = 1; i < numKeyframes; i++) {
            keyframes[i] = new MyFloatKeyframe((float)i/(numKeyframes-1),values[i]);
        }
        return new MyKeyframeSet(keyframes);
    }

    /**
     * 根据当前百分比获取响应的值
     * @param fraction 百分比
     * @return
     */
    public Object getValue(float fraction){
        Log.i("fraction",fraction+"");
        MyFloatKeyframe preKeyFrame = mFirstKeyframe;
        for (int i = 1; i < mKeyframes.size(); ++i) {
            MyFloatKeyframe nextKeyFrame = mKeyframes.get(i);
            if(fraction<nextKeyFrame.getFraction()){
                return mEvaluator.evaluate(fraction,preKeyFrame.getValue(),nextKeyFrame.getValue());
            }
            preKeyFrame = nextKeyFrame;
        }
        return null;
    }
}
