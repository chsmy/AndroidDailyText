package com.chs.androiddailytext.jetpack


/**
 *  作者：chs
 *  时间：2019-01-29 14:24
 *  描述：
 */
data class Catefories(
    val error: Boolean = false,
    val results: List<Result> = mutableListOf()
)

data class Result(
    val id: String = "",
    val enName: String = "",
    val name: String = "",
    val rank: Int = 0
)