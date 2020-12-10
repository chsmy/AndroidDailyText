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
import com.chs.app_jetpack.databinding.ActivityPagingBinding
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest

class PagingActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProvider(this).get(PagingViewModel::class.java) }

    private val adapter: ArticleAdapter by lazy { ArticleAdapter() }

    private lateinit var binding: ActivityPagingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter.withLoadStateFooter(PostsLoadStateAdapter(adapter))

        viewModel.getArticleData().observe(this, Observer {
            lifecycleScope.launchWhenCreated {
                adapter.submitData(it)
            }
        })
        lifecycleScope.launchWhenCreated {
            @OptIn(ExperimentalCoroutinesApi::class)
            adapter.loadStateFlow.collectLatest {
                if (it.refresh !is LoadState.Loading) {
                    binding.refreshView.finishRefresh()
                }
            }
        }
        binding.refreshView.setOnRefreshListener {
            adapter.refresh()
        }
    }
}