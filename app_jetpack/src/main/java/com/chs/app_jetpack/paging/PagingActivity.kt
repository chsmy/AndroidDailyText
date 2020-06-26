package com.chs.app_jetpack.paging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.example.paging.pagingwithnetwork.reddit.ui.PostsLoadStateAdapter
import com.chs.app_jetpack.R
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest

class PagingActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProvider(this).get(PagingViewModel::class.java) }

    private val adapter: ArticleAdapter by lazy { ArticleAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging)

        val refreshView:SmartRefreshLayout = findViewById(R.id.refreshView)
        val recyclerView :RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter.withLoadStateFooter(PostsLoadStateAdapter(adapter))

        viewModel.getArticleData().observe(this, Observer {
            lifecycleScope.launchWhenCreated {
                adapter.submitData(it)
            }
        })
        lifecycleScope.launchWhenCreated {
            @OptIn(ExperimentalCoroutinesApi::class)
            adapter.loadStateFlow.collectLatest {
                if(it.refresh !is LoadState.Loading){
                    refreshView.finishRefresh()
                }
            }
        }
        refreshView.setOnRefreshListener {
            adapter.refresh()
        }
    }
}