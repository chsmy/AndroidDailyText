package com.chs.androiddailytext.jetpack.paging;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

/**
 * @author：chs
 * date：2020/2/2
 * des：
 */
public class PagingViewModel extends ViewModel {

    private static final String TAG = PagingViewModel.class.getSimpleName();

    private LiveData<PagedList<ArticleResponse.DataBean.DatasBean>> articleRes = null;
    private ArticleDataSource mDataSource;
    //是否有数据
    private MutableLiveData<Boolean> boundaryPageData = new MutableLiveData<>();

    public LiveData<PagedList<ArticleResponse.DataBean.DatasBean>> getArticleLiveData() {
        if (articleRes == null) {
            PagedList.Config config = new PagedList.Config.Builder()
                    .setPageSize(20)
                    .setInitialLoadSizeHint(22)
                    .build();
            articleRes = new LivePagedListBuilder<Integer, ArticleResponse.DataBean.DatasBean>(mFactory, config)
                    .setBoundaryCallback(mBoundaryCallback)
                    .build();
        }
        return articleRes;
    }

    private DataSource.Factory mFactory = new DataSource.Factory() {
        @NonNull
        @Override
        public DataSource create() {
            if (mDataSource == null || mDataSource.isInvalid()) {
                mDataSource = new ArticleDataSource();
            }
            return mDataSource;
        }
    };

    private PagedList.BoundaryCallback mBoundaryCallback = new PagedList.BoundaryCallback<ArticleResponse.DataBean.DatasBean>() {
        @Override
        public void onZeroItemsLoaded() {
            super.onZeroItemsLoaded();
            //初始化数据
            boundaryPageData.postValue(false);
        }

        @Override
        public void onItemAtFrontLoaded(@NonNull ArticleResponse.DataBean.DatasBean itemAtFront) {
            super.onItemAtFrontLoaded(itemAtFront);
            //正在添加数据
            boundaryPageData.postValue(true);
        }

        @Override
        public void onItemAtEndLoaded(@NonNull ArticleResponse.DataBean.DatasBean itemAtEnd) {
            super.onItemAtEndLoaded(itemAtEnd);
            //没有数据加载了
            boundaryPageData.postValue(false);
        }
    };

    public ArticleDataSource getDataSource() {
        return mDataSource;
    }

    public MutableLiveData<Boolean> getBoundaryPageData() {
        return boundaryPageData;
    }

    public void loadData(int currentPage, PageKeyedDataSource.LoadInitialCallback<Integer, ArticleResponse.DataBean.DatasBean> initialCallback
            , PageKeyedDataSource.LoadCallback<Integer, ArticleResponse.DataBean.DatasBean> callback) {
        String url = "https://www.wanandroid.com/article/list/" + currentPage + "/json";
        OkGo.<String>get(url)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        ArticleResponse articleResponse = gson.fromJson(response.body(), ArticleResponse.class);
                        if (initialCallback != null) {
                            initialCallback.onResult(articleResponse.getData().getDatas(), -1, 0);
                        } else {
                            callback.onResult(articleResponse.getData().getDatas(), currentPage);
                        }
                        boundaryPageData.postValue(articleResponse.getData().getDatas().size() <= 0);
                    }
                });
    }

    public class ArticleDataSource extends PageKeyedDataSource<Integer, ArticleResponse.DataBean.DatasBean> {
        @Override
        public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, ArticleResponse.DataBean.DatasBean> callback) {
            //开始加载数据
            loadData(0, callback, null);
            Log.d(TAG, "loadInitial");
        }

        @Override
        public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, ArticleResponse.DataBean.DatasBean> callback) {
            //往前加载数据
        }

        @Override
        public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, ArticleResponse.DataBean.DatasBean> callback) {
            //往后加载数据
            loadData(params.key + 1, null, callback);
            Log.d(TAG, "loadAfter");
        }
    }
}
