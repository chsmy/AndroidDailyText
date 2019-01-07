package com.chs.androiddailytext.datastructure;

/**
 * 作者：chs
 * 时间：2019-01-07 11:19
 * 描述：
 */
public class Text {

    public int main(){
       int a = 1;
       int res = 0;
       int ret = 0;
       ret = add(5,6);
       res = a + ret;
       System.out.println("res"+res);
       return  res;
    }

    public int add(int x ,int y){
        int sum = 0;
        sum = x + y;
        return sum;
    }

}
