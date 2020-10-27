package com.chs.app_jetpack.hilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chs.app_jetpack.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HiltSimpleActivity : AppCompatActivity() {

    /**
     * @AndroidEntryPoint注解会创建一个依赖容器，该容器遵循Android类的生命周期
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hilt_simple)

        supportFragmentManager.beginTransaction()
                .add(R.id.container,HiltFragment(),"HiltFragment")
                .commit()

    }
}