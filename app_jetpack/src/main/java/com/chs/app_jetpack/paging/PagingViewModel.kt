package com.chs.app_jetpack.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig

/**
 * author：chs
 * date：2020/6/26
 * des：
 */
class PagingViewModel:ViewModel() {

    private val repository:ArticleRepository by lazy { ArticleRepository() }
    /**
     * Pager 分页入口 每个PagingData代表一页数据 最后调用asLiveData将结果转化为一个可监听的LiveData
     */
    fun getArticleData() = repository.getArticleData().asLiveData()

}