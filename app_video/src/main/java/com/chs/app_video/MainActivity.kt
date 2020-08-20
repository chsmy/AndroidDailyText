package com.chs.app_video

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.database.ExoDatabaseProvider
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.upstream.FileDataSource
import com.google.android.exoplayer2.upstream.cache.*
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val exoPlayer by lazy { SimpleExoPlayer.Builder(this).build() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        player_view.player = exoPlayer
        val dataSourceFactory = DefaultHttpDataSourceFactory(Util.getUserAgent(this,packageName))
        //创建视频缓存
        val cache = SimpleCache(cacheDir, LeastRecentlyUsedCacheEvictor(1024 * 1024 * 200),ExoDatabaseProvider(this))
        //将cache和cache工厂关联，用来对cache进行读写
        val cacheDataSinkFactory = CacheDataSinkFactory(cache, Long.MAX_VALUE)
        //创建一个可以边播放 边缓存到本地的工厂
        val cacheDataSourceFactory = CacheDataSourceFactory(cache,dataSourceFactory,FileDataSource.Factory(),
         cacheDataSinkFactory, CacheDataSource.FLAG_BLOCK_ON_CACHE,null)
        //创建媒体资源
        val url = "http://baobab.kaiyanapp.com/api/v1/playUrl?vid=124421&resourceType=video&editionType=low&source=ucloud&playUrlType=url_oss";
        val videoSource = ProgressiveMediaSource.Factory(cacheDataSourceFactory).createMediaSource(Uri.parse(url))
        exoPlayer.prepare(videoSource)
        exoPlayer.playWhenReady = true
    }

    override fun onDestroy() {
        super.onDestroy()
        exoPlayer.release()
    }
}
