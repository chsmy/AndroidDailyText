package com.chs.androiddailytext.jetpack

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback

/**
 *  @author chs
 *  date：2019-04-08 17:45
 *  des：
 */
class NameViewModel : ViewModel() {

    // 创建一个String类型的LiveData
    val currentName: MutableLiveData<String> by lazy {
        MutableLiveData<String>().also {
            loadData()
        }
    }
//    fun getCurrentName() : LiveData<String> {
//        return currentName
//    }
    private fun loadData() {
        OkGo.get<String>("http://gank.io/api/xiandu/categories")
                .execute(object : StringCallback(){
                    override fun onSuccess(response: com.lzy.okgo.model.Response<String>?) {
                        val gson = Gson()
                        var res = gson.fromJson<Catefories>(response!!.body(), Catefories::class.java)
                        currentName.postValue(res.results.get(0).name)
                    }
                    override fun onError(response: com.lzy.okgo.model.Response<String>?) {
                        super.onError(response)
                    }
                })
    }
}