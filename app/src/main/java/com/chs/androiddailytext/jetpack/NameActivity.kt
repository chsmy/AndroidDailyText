package com.chs.androiddailytext.jetpack

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.chs.androiddailytext.R
import kotlinx.android.synthetic.main.activity_name.*

/**
 *  @author chs
 *  date：2019-04-08 18:00
 *  des：
 */
class NameActivity : AppCompatActivity() {

    private lateinit var model: NameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)
        // 获取ViewModel.
        model = ViewModelProviders.of(this).get(NameViewModel::class.java)

        // 创建observer
        val nameObserver = Observer<String> { newName ->
            tv_name.text = newName
        }

        // 观察LiveData,传入当前的LifecycleOwner和观察者
        model.currentName.observe(this, nameObserver)
    }
}