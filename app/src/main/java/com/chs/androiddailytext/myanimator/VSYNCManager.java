package com.chs.androiddailytext.myanimator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chs
 * date：2019-04-11 16:35
 * des：
 */
public class VSYNCManager {

    AnimatorFrameCallBack mAnimatorFrameCallBack;
    /**
     * 可能会有多个动画同事使用，所以弄个集合
     */
    private List<AnimatorFrameCallBack> list = new ArrayList<>();

    private VSYNCManager(){
        new Thread(mRunnable).start();
    }

    public static VSYNCManager getInstance(){
        return new VSYNCManager();
    }

    public void add(AnimatorFrameCallBack callBack){
        list.add(callBack);
    }

    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
        while (true){
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (AnimatorFrameCallBack callback : list) {
                callback.doAnimator(System.currentTimeMillis());
            }
           }
        }
    };

    interface AnimatorFrameCallBack{
        void doAnimator(long currentTime);
    }

}
