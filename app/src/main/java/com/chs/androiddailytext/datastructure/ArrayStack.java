package com.chs.androiddailytext.datastructure;

/**
 * 作者：chs
 * 时间：2019-01-07 10:49
 * 描述：数组栈
 */
public class ArrayStack {
    private String[] items;
    private int count;//栈中的元素的个数
    private int n;//栈的总大小

    public ArrayStack(int n) {
        this.n = n;
        this.items = new String[n];
        this.count = 0;
    }

    //入栈操作
    public boolean push(String item){
        //如果栈满了，直接返回false
        if(count == n) return false;
        items[count] = item;
        ++count;
        return true;
    }

    //出栈操作
    public String pop(){
        //如果栈为空 返回null
        if(count == 0) return null;
        String tmp = items[count-1];
        --count;
        return tmp;
    }

}
