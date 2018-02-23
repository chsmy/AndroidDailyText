package com.chs.androiddailytext.pattern.strategy;

import android.util.Log;

/**
 * 作者：chs on 2018-02-23 16:49
 * 邮箱：657083984@qq.com
 */

public class AnimationUtil {
    void animate(int type){
        if(type == 1){
            animate1();
        }else if(type == 2){
            animate2();
        }else if(type == 3){
            animate3();
        }
    }
    private void animate1(){
        //一堆逻辑处理
        Log.i("AnimationUtil","先快后慢");
    }
    private void animate2(){
        //一堆逻辑处理
        Log.i("AnimationUtil","先转圈后加速");
    }
    private void animate3(){
        //一堆逻辑处理
        Log.i("AnimationUtil","先后退在前进");
    }
}
