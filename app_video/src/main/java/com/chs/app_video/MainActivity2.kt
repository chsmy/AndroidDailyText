package com.chs.app_video

import android.app.Application
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceHolder
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.database.ExoDatabaseProvider
import com.google.android.exoplayer2.offline.DownloadRequest
import com.google.android.exoplayer2.offline.DownloadService
import com.google.android.exoplayer2.upstream.cache.CacheDataSource
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor
import com.google.android.exoplayer2.upstream.cache.SimpleCache
import kotlinx.android.synthetic.main.activity_main.*
import tv.danmaku.ijk.media.player.IjkMediaPlayer

class MainActivity2 : AppCompatActivity() , SurfaceHolder.Callback{

    private val exoPlayer by lazy { SimpleExoPlayer.Builder(this).build() }

    companion object{
        private val app: Application = AppUtil.getApp()
        //创建视频缓存
        val cache = SimpleCache(app.cacheDir, LeastRecentlyUsedCacheEvictor(1024 * 1024 * 200), ExoDatabaseProvider(app))
        //将cache和cache工厂关联，用来对cache进行读写
        val cacheDataSinkFactory = CacheDataSource.Factory().setCache(cache)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        player_view.player = exoPlayer
//        control_view.player = exoPlayer

        //创建媒体资源
        val url = "http://baobab.kaiyanapp.com/api/v1/playUrl?vid=124421&resourceType=video&editionType=low&source=ucloud&playUrlType=url_oss";
        val url2 = "http://baobab.kaiyanapp.com/api/v1/playUrl?vid=216312&resourceType=video&editionType=default&source=aliyun&playUrlType=url_oss"
        val url3 = "http://baobab.kaiyanapp.com/api/v1/playUrl?vid=216311&resourceType=video&editionType=default&source=aliyun&playUrlType=url_oss"
        val mediaItem: MediaItem = MediaItem.fromUri(url)
//        exoPlayer.setMediaItem(mediaItem)

        val subtitle = MediaItem.Subtitle(Uri.parse("https://api.apiopen.top/singlePoetry"),
                "text/plain", null)
        val m1: MediaItem = MediaItem.Builder()
                .setUri(url)
                .setSubtitles(listOf(subtitle))
                .build()
        exoPlayer.addMediaItem(m1)
        exoPlayer.addMediaItem(MediaItem.fromUri(url2))
        exoPlayer.addMediaItem(MediaItem.fromUri(url3))
        exoPlayer.prepare()
        exoPlayer.play()

        play.setOnClickListener {
            mediaPlayer.start()
        }
        pause.setOnClickListener {
            mediaPlayer.pause()
        }
        val urla = "https://mv-cdn1.ylyk.com/Fs3MOFiBwuF1XUZxRsP4yAbii9B9";
        downloadVideo(urla);
    }

    private fun downloadVideo(url: String) {
        val uri = Uri.parse(url)
        val downloadRequest = DownloadRequest.Builder(url,uri).build()
        DownloadService.sendAddDownload(applicationContext,MediaDownloadService::class.java
        ,downloadRequest,false)
    }

    private val mediaPlayer: IjkMediaPlayer by lazy { IjkMediaPlayer() }

    private fun useIjkplayer(url:String) {
        surface_view.holder.addCallback(this)
        mediaPlayer.dataSource = url
        mediaPlayer.prepareAsync()
        mediaPlayer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        exoPlayer.release()
        mediaPlayer.release()
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        mediaPlayer.setDisplay(holder)
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {

    }

}