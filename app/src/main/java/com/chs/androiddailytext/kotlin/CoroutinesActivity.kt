package com.chs.androiddailytext.kotlin

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.chs.androiddailytext.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * 协程
 */
class CoroutinesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)

//        val retrofit = Retrofit.Builder()
//               .addConverterFactory(GsonConverterFactory.create())
//               .baseUrl("https://www.wanandroid.com/")
//               .build()
//        val service =  retrofit.create(AipInterface::class.java)
//        val call = service.getHomeList()
//        call.enqueue(object : Callback<WanBaseResponse<Data>> {
//            override fun onFailure(call: Call<WanBaseResponse<Data>>, t: Throwable) {
//                Log.i("CoroutinesActivity","onFailure")
//            }
//
//            override fun onResponse(call: Call<WanBaseResponse<Data>>, response: Response<WanBaseResponse<Data>>) {
//                Log.i("CoroutinesActivity","onResponse:${response.body()}")
//            }
//        })


        val retrofit = Retrofit.Builder()
               .addConverterFactory(GsonConverterFactory.create())
               .baseUrl("https://www.wanandroid.com/")
               .build()
        val service =  retrofit.create(AipInterface::class.java)
        GlobalScope.launch{
            val result = withContext(Dispatchers.IO){service.getHomeList()}
            Log.i("launch","onResponse:${result.data.datas[0].link}")
        }

        val myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        myViewModel.launchOnUI{
            val result = service.getHomeList()
            Log.i("myViewModel","onResponse:${result.data.datas[0].link}")
        }

    }

}
