package com.chs.androiddailytext.jetpack;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author：chs date：2019/4/15
 * des：
 */
public class NameViewModel1 extends ViewModel {

    private MutableLiveData<String> currentName;

    public MutableLiveData<String> getCurrentName() {
        if (currentName == null) {
            currentName = new MutableLiveData<String>();
        }
        loadData();
        return currentName;
    }

    private void loadData() {
        OkGo.<String>get("http://gank.io/api/xiandu/categories")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        Catefories catefories = gson.fromJson(response.body(), Catefories.class);
                        currentName.postValue(catefories.getResults().get(0).getName());
                    }
                });
    }
}
