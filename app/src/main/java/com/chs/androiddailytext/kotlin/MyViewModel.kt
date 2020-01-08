package com.chs.androiddailytext.kotlin

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author：chs
 * date：2020/1/8
 * des：
 */
class MyViewModel : ViewModel(){

    val mHomeData = MutableLiveData<Data>()

    fun launchOnUI(block:suspend CoroutineScope.()->Unit){
        viewModelScope.launch {
           withContext(Dispatchers.IO){ block()}
        }
    }
}