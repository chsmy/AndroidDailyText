package com.chs.app_jetpack.hilt

import com.blankj.utilcode.util.LogUtils
import javax.inject.Inject

/**
 * @author: chs
 * @date: Create in 2020/10/27
 * @description Hilt 例子
 */
class HiltSimple @Inject constructor(){

    fun doSomething(){
        LogUtils.i("Hilt","----doSomething-----")
    }

}