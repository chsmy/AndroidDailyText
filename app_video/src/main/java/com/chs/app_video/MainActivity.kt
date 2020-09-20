package com.chs.app_video

import android.app.Application
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.database.ExoDatabaseProvider
import com.google.android.exoplayer2.source.ShuffleOrder
import com.google.android.exoplayer2.upstream.HttpDataSource.HttpDataSourceException
import com.google.android.exoplayer2.upstream.HttpDataSource.InvalidResponseCodeException
import com.google.android.exoplayer2.upstream.cache.CacheDataSink
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor
import com.google.android.exoplayer2.upstream.cache.SimpleCache
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private val exoPlayer by lazy { SimpleExoPlayer.Builder(this).build() }

    companion object{
        private val app: Application = AppUtil.getApp()
        //创建视频缓存
        val cache = SimpleCache(app.cacheDir, LeastRecentlyUsedCacheEvictor(1024 * 1024 * 200), ExoDatabaseProvider(app))
        //将cache和cache工厂关联，用来对cache进行读写
        val cacheDataSinkFactory = CacheDataSink.Factory().setCache(cache)
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
        exoPlayer.addMediaItem(MediaItem.fromUri(url))
        exoPlayer.addMediaItem(MediaItem.fromUri(url2))
        exoPlayer.addMediaItem(MediaItem.fromUri(url3))
        exoPlayer.prepare()
        exoPlayer.play()


        exoPlayer.addListener(object : Player.EventListener {
            override fun onPlaybackStateChanged(state: Int) {
                super.onPlaybackStateChanged(state)
            }

            override fun onPlayWhenReadyChanged(playWhenReady: Boolean, reason: Int) {
                super.onPlayWhenReadyChanged(playWhenReady, reason)
            }

            override fun onIsPlayingChanged(isPlaying: Boolean) {
                super.onIsPlayingChanged(isPlaying)
            }

            override fun onPlayerError(error: ExoPlaybackException) {
                super.onPlayerError(error)
                if (error.type == ExoPlaybackException.TYPE_SOURCE) {
                    val cause: IOException = error.sourceException
                    if (cause is HttpDataSourceException) {
                        // 发生HTTP错误。
                        val httpError = cause
                        // 发生错误的请求。
                        val requestDataSpec = httpError.dataSpec
                        // 通过casting和by可以发现更多关于错误的信息
                        // 查询原因
                        if (httpError is InvalidResponseCodeException) {
                            // 强转到InvalidResponseCodeException并检索响应代码， 消息和标题。
                        } else {
                            //尝试调用httperloader . getcause()来检索底层原因， 尽管注意它可能是空的。
                        }
                    }
                }
            }

            override fun onPositionDiscontinuity(reason: Int) {
                super.onPositionDiscontinuity(reason)
            }

            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                super.onMediaItemTransition(mediaItem, reason)
            }
        })
        exoPlayer
                .createMessage { messageType: Int, payload: Any? ->
                    //在特定位置进行特定的操作
                }
                .setHandler(Handler(Looper.getMainLooper()))
                .setPosition( /* windowIndex= */0,  /* positionMs= */120000)
                .setPayload("")
                .setDeleteAfterDelivery(false)
                .send()
//        exoPlayer.addAnalyticsListener( EventLogger(trackSelector))


        // 在位置1处添加一个新的播放资源
        exoPlayer.addMediaItem(/* index= */ 1, MediaItem.fromUri(url))
        // 把位置3处的资源移动到位置0处
        exoPlayer.moveMediaItem(/* currentIndex= */ 2, /* newIndex= */ 0)
        // 移除第一个资源
        exoPlayer.removeMediaItem(/* index= */ 0)

        // Replaces the playlist with a new one.
        val newItems: List<MediaItem> = listOf(
                MediaItem.fromUri(url),
                MediaItem.fromUri(url2))
        exoPlayer.setMediaItems(newItems,  /* resetPosition= */true)
        // 移除所有的列表
        exoPlayer.clearMediaItems()

        val itemCount = exoPlayer.mediaItemCount
        val mediaItemAt = exoPlayer.getMediaItemAt(1)
        val currentMediaItem = exoPlayer.currentMediaItem

//        val mediaItem: MediaItem = MediaItem.Builder().setUri(url).setMediaId(mediaId).build()
//        val mediaItem: MediaItem = MediaItem.Builder().setUri(url).setTag(metadata).build()
//        exoPlayer.setShuffleOrder(object :ShuffleOrder{
//
//        })
    }

    override fun onDestroy() {
        super.onDestroy()
        exoPlayer.release()
    }
}
