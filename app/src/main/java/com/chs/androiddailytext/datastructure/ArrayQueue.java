package com.chs.androiddailytext.datastructure;

/**
 * 作者：chs
 * 时间：2019-01-07 18:21
 * 描述：
 */
public class ArrayQueue {
    private String[] items;
    private int n = 0;//总大小
    private int head = 0;
    private int tail = 0;

    //申请一个大小为n的数组
    public ArrayQueue(int n) {
        this.items = new String[n];
        this.n = n;
    }

    //入队
    public boolean enqueue(String item){
        //如果队列满了返回false
        if(tail == n) return false;
        items[tail] = item;
        ++tail;
        return true;
    }

    //出队
    public String dequeue(){
        //head == tail 表示队列为空
        if(head == tail) return  null;
        String res = items[head];
        ++head;
        return res;
    }

}
