package com.chs.app_jetpack.net


/**
 *  @author chs
 *  date: 2020-01-03 16:45
 *  des:
 */
object WanRetrofitClient : RetrofitClient(){

    val service by lazy {
        getService(WanService::class.java, WanService.WAN_BASE_URL)
    }

}