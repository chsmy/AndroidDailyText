package com.chs.app_jetpack.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig

/**
 * author：chs
 * date：2020/6/26
 * des：
 */
class ArticleRepository {

    fun getArticleData() = Pager(PagingConfig(pageSize = 20)){
        ArticleDataSource()
    }.flow

}