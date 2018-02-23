package com.chs.androiddailytext.pattern.strategy;

/**
 * 作者：chs on 2018-02-23 17:19
 * 邮箱：657083984@qq.com
 */

public class AnimateController {
    private IAnimate mAnimate;

    public void setAnimate(IAnimate animate) {
        mAnimate = animate;
    }
    public void animate(){
        mAnimate.animate();
    }
}
