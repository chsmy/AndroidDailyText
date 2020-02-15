package com.chs.androiddailytext.jetpack.navigation;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * author：chs
 * date：2020/2/14
 * des：
 */
public class NavViewModel extends ViewModel {

    private MutableLiveData<String> params;

    public MutableLiveData<String> getParams() {
        if(params == null){
            params = new MutableLiveData<>();
            params.setValue("Lily");
        }
        return params;
    }
}
