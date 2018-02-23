package com.chs.androiddailytext.pattern.strategy;

import android.util.Log;

/**
 * 作者：chs on 2018-02-23 17:12
 * 邮箱：657083984@qq.com
 */

public class Animate3 implements IAnimate {
    @Override
    public void animate() {
        //一堆逻辑处理
        Log.i("AnimationUtil","先后退在前进");
    }
}
