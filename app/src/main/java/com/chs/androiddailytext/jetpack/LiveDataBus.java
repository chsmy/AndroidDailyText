package com.chs.androiddailytext.jetpack;

/**
 * author：chs
 * date：2020/2/21
 * des：
 */
public class LiveDataBus {

    public static <T>LiveDataEvent.BusLiveData <T>get(String key){
        return (LiveDataEvent.BusLiveData<T>) LiveDataEvent.get().with(key);
    }

}
