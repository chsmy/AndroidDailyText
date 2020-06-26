package com.chs.app_jetpack.net

import com.chs.module_wan.model.HomeEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * author：chs
 * date：2020/6/26
 * des：
 */
interface WanService {

    companion object{
        const val WAN_BASE_URL = "https://www.wanandroid.com/"
    }

    /**
     * 首页
     */
    @GET("article/list/{page}/json")
    suspend fun getHomeList(@Path("page")page:Int) : WanBaseResponse<HomeEntity>

}