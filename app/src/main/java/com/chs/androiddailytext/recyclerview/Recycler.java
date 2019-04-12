package com.chs.androiddailytext.recyclerview;

import android.view.View;

import java.util.Stack;

/**
 * @author chs
 * date：2019-04-12 16:11
 * des：缓存池
 */
public class Recycler {

    private Stack<View>[] mViews;

    /**
     *
     * @param typeNum 有几种类型
     */
    public Recycler(int typeNum){

        mViews = new Stack[typeNum];

        //RecyclerView中可能有不同的布局类型，不同的type分开缓存
        for (int i = 0; i < typeNum; i++) {
            mViews[i] = new Stack<>();
        }
    }

    public void put(View view,int type){
        mViews[type].push(view);
    }

    public View get(int type){
        try {
            return mViews[type].pop();
        }catch (Exception e){
            return null;
        }

    }

}
