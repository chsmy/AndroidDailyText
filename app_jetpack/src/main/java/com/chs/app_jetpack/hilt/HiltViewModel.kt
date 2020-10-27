package com.chs.app_jetpack.hilt

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

/**
 * @author: chs
 * @date: Create in 2020/10/27
 * @description
 */
class HiltViewModel @ViewModelInject constructor(): ViewModel() {

    val mHiltLiveData = liveData{
         emit("i am from HiltViewModel")
    }

}