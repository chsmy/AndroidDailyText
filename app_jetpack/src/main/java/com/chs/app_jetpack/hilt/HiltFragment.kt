package com.chs.app_jetpack.hilt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.chs.app_jetpack.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_hilt_test.*
import javax.inject.Inject

/**
 * @author: chs
 * @date: Create in 2020/10/27
 * @description HiltFragment例子 如果fragment使用了 @AndroidEntryPoint 注解
 * 那么它所依赖的activity也需要添加该注解 负责就会出错
 */
@AndroidEntryPoint
class HiltFragment : Fragment() {

    @Inject
    lateinit var mHiltSimple: HiltSimple

    private val mHiltViewModel:HiltViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hilt_test,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mHiltSimple.doSomething()

        mHiltViewModel.mHiltLiveData.observe(this, Observer {
            tv_text.text = it
        })
    }
}