package com.chs.androiddailytext.netease;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * @author chs
 * date：2019-03-29 16:19
 * des：
 */
public class MyPointFEvaluator implements TypeEvaluator<PointF> {
    private PointF mPoint;

    public MyPointFEvaluator() {
    }

    public MyPointFEvaluator(PointF reuse) {
        mPoint = reuse;
    }
    @Override
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
        float x = startValue.x + (fraction * (endValue.x - startValue.x));
        float y = startValue.y + (fraction * (endValue.y - startValue.y));

        if (mPoint != null) {
            mPoint.set(x, y);
            return mPoint;
        } else {
            return new PointF(x, y);
        }
    }
}
