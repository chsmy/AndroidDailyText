package com.chs.androiddailytext.kotlin

/**
 *  @author chs
 *  date: 2019-12-19 15:51
 *  des:
 */
data class WanBaseResponse<out T>(val errorCode:Int,val errorMsg:String,val data:T)