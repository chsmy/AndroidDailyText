package com.chs.app_video

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.webkit.DownloadListener
import androidx.core.app.NotificationCompat
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ThreadUtils
import com.blankj.utilcode.util.Utils
import com.google.android.exoplayer2.database.ExoDatabaseProvider
import com.google.android.exoplayer2.offline.Download
import com.google.android.exoplayer2.offline.DownloadManager
import com.google.android.exoplayer2.offline.DownloadService
import com.google.android.exoplayer2.scheduler.PlatformScheduler
import com.google.android.exoplayer2.scheduler.Scheduler
import com.google.android.exoplayer2.upstream.DataSink
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.upstream.cache.CacheDataSink
import com.google.android.exoplayer2.upstream.cache.CacheDataSourceFactory
import com.google.android.exoplayer2.upstream.cache.NoOpCacheEvictor
import com.google.android.exoplayer2.upstream.cache.SimpleCache
import com.google.android.exoplayer2.upstream.crypto.AesCipherDataSink
import com.google.android.exoplayer2.upstream.crypto.AesCipherDataSource

/**
 * @author: chs
 * @date: Create in 2020/10/29
 * @description 媒体下载service
 */
class MediaDownloadService() :
        DownloadService(1){

    override fun getDownloadManager(): DownloadManager {
        val provider = ExoDatabaseProvider(Utils.getApp())
        val downloadCache = SimpleCache(getDir("ylyk_down", MODE_PRIVATE), NoOpCacheEvictor(), provider)
        val dataSourceFactory = DefaultHttpDataSourceFactory()
        val readFactory = DataSource.Factory { AesCipherDataSource("cf#yu*dfcf#yu*df".toByteArray(), dataSourceFactory.createDataSource()) }

        val writeFactory = DataSink.Factory { AesCipherDataSink("cf#yu*dfcf#yu*df".toByteArray(), CacheDataSink(downloadCache, 512 * 1024 * 1024), ByteArray(1024 * 1024)) }
        val cacheFactory = CacheDataSourceFactory(downloadCache, dataSourceFactory,readFactory,writeFactory,0,null)
        val downloadManager = DownloadManager(Utils.getApp(), provider, downloadCache, cacheFactory, ThreadUtils.getIoPool())
        downloadManager.maxParallelDownloads = 3
        downloadManager.addListener(object : DownloadListener, DownloadManager.Listener {
            override fun onDownloadStart(url: String?, userAgent: String?, contentDisposition: String?, mimetype: String?, contentLength: Long) {
                LogUtils.i("开始下载》。。。。。。")
            }

            override fun onDownloadChanged(downloadManager: DownloadManager, download: Download, finalException: Exception?) {
                super.onDownloadChanged(downloadManager, download, finalException)
            }
        })
        return downloadManager
    }

    override fun getScheduler(): Scheduler? {
        return PlatformScheduler(Utils.getApp(), R.id.exo_artwork)
    }

    override fun getForegroundNotification(downloads: MutableList<Download>): Notification {
        val intent = Intent(Utils.getApp(), MainActivity2::class.java)
        val pi = PendingIntent.getActivity(Utils.getApp(), 0, intent, 0)
        return getNotification()
    }


    fun getNotification():Notification{
        val CHANNEL_ONE_ID = "CHANNEL_ONE_ID"
        val CHANNEL_ONE_NAME = "CHANNEL_ONE_ID"
        var notificationChannel: NotificationChannel? = null
       //进行8.0的判断
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(CHANNEL_ONE_ID,
                    CHANNEL_ONE_NAME, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.setShowBadge(true)
            notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            val manager: NotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(notificationChannel)
        }
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.jianshu.com/p/14ba95c6c3e2"))

        return NotificationCompat.Builder(this, CHANNEL_ONE_ID)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(PendingIntent.getActivity(this, 0, intent, 0))
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setTicker("正在播放")
                .setOngoing(true)
                .build()
    }

}