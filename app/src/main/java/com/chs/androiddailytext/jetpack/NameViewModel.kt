package com.chs.androiddailytext.jetpack

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

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

    fun getCurrentName() : LiveData<String> {
        return currentName
    }

    private fun loadData() {
       val handler = Handler()
        handler.postDelayed({
            currentName.postValue("Lily")
        },1000)
    }

}