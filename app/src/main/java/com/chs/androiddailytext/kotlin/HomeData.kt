package com.chs.androiddailytext.kotlin
import com.google.gson.annotations.SerializedName


/**
 *  @author chs
 *  date: 2019-12-16 15:06
 *  des:
 */

data class Data(
    @SerializedName("curPage")
    val curPage: Int = 0,
    @SerializedName("datas")
    val datas: List<DataX> = listOf(),
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

data class DataX(
    @SerializedName("apkLink")
    val apkLink: String = "",
    @SerializedName("audit")
    val audit: Int = 0,
    @SerializedName("author")
    val author: String = "",
    @SerializedName("chapterId")
    val chapterId: Int = 0,
    @SerializedName("chapterName")
    val chapterName: String = "",
    @SerializedName("collect")
    val collect: Boolean = false,
    @SerializedName("courseId")
    val courseId: Int = 0,
    @SerializedName("desc")
    val desc: String = "",
    @SerializedName("envelopePic")
    val envelopePic: String = "",
    @SerializedName("fresh")
    val fresh: Boolean = false,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("link")
    val link: String = "",
    @SerializedName("niceDate")
    val niceDate: String = "",
    @SerializedName("niceShareDate")
    val niceShareDate: String = "",
    @SerializedName("origin")
    val origin: String = "",
    @SerializedName("prefix")
    val prefix: String = "",
    @SerializedName("projectLink")
    val projectLink: String = "",
    @SerializedName("publishTime")
    val publishTime: Long = 0,
    @SerializedName("selfVisible")
    val selfVisible: Int = 0,
    @SerializedName("shareDate")
    val shareDate: Long = 0,
    @SerializedName("shareUser")
    val shareUser: String = "",
    @SerializedName("superChapterId")
    val superChapterId: Int = 0,
    @SerializedName("superChapterName")
    val superChapterName: String = "",
    @SerializedName("tags")
    val tags: List<Any> = listOf(),
    @SerializedName("title")
    val title: String = "",
    @SerializedName("type")
    val type: Int = 0,
    @SerializedName("userId")
    val userId: Int = 0,
    @SerializedName("visible")
    val visible: Int = 0,
    @SerializedName("zan")
    val zan: Int = 0
)

data class Data1(
    val children: List<Any>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)