package com.chs.module_wan.model
import com.google.gson.annotations.SerializedName


/**
 *  @author chs
 *  date: 2019-12-16 15:06
 *  des:
 */

data class HomeEntity(
    @SerializedName("curPage")
    val curPage: Int = 0,
    @SerializedName("datas")
    val datas: List<Article> = mutableListOf(),
    @SerializedName("offset")
    val offset: Int = 0,
    @SerializedName("over")
    val over: Boolean = false,
    @SerializedName("pageCount")
    val pageCount: Int = 0,
    @SerializedName("size")
    val size: Int = 0,
    @SerializedName("total")
    val total: Int = 0
)
