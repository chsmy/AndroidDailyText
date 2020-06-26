package com.chs.app_jetpack.paging

import androidx.paging.PagingSource
import com.chs.app_jetpack.net.WanRetrofitClient
import com.chs.module_wan.model.Article
import java.lang.Exception

/**
 * author：chs
 * date：2020/6/26
 * des： PagingSource 分页数据的基类，用来处理向前加载还是向后加载，当数据不断增加的时候，PagingSource中的数据也会不断增加
 * 数据使用PagingData来存储
 *  已经加载完的数据不能被修改，想要修改数据，需要重新创建PagingSource
 *  key 数据加载的键，用来表示使用哪种方式加载，
 *  比如分页的页码加载可以使用Int,
 *  比如使用最后一条数据的id来加载后面的数据可以使用String等任意类型数据
 *
 *  value 要加载的数据类型
 */
class ArticleDataSource:PagingSource<Int,Article>() {

    /**
     * 实现这个方法来触发异步加载(例如从数据库或网络)。 这是一个suspend挂起函数，可以很方便的使用协程异步加载
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {

        return try {
            val page = params.key?:0
            val result = WanRetrofitClient.service.getHomeList(page)
            LoadResult.Page(
                    //需要加载的数据
                    data = result.data.datas,
                    //如果可以往上加载更多就设置该参数，否则不设置
                    prevKey = null,
                    //加载下一页的key 如果传null就说明到底了
                    nextKey = if(result.data.curPage==result.data.pageCount) null else page+1
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }

    }
}