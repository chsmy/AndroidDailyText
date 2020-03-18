package com.chs.app_test;

/**
 * author：chs
 * date：2020/3/11
 * des：
 */
public class Utils {

    public int calculate(int a,int b){
        return a + b;
    }

    public Integer toNumber(String num){
        if(num == null || num.isEmpty()){
             return null;
        }
        Integer integer;
        try {
            integer = Integer.parseInt(num.trim());
        }catch (Exception e){
            integer = null;
        }
        return integer;
    }
}
