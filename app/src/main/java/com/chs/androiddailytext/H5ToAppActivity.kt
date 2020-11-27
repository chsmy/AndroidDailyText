package com.chs.androiddailytext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_h5_to_app.*

class H5ToAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_h5_to_app)

        val url = "file:///android_asset/jump.html"

        val settings = webview.settings
        settings.javaScriptEnabled = true
//        webview.webViewClient = object :WebViewClient(){
//            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
//                return super.shouldOverrideUrlLoading(view, request)
//            }
//        }
        webview.webChromeClient = WebChromeClient()
        webview.loadUrl(url);
    }
}