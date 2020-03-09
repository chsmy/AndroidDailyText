package com.chs.app_test;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

public class WebViewActivity extends AppCompatActivity {
    public static final String KEY_URL_TO_LOAD = "KEY_URL_TO_LOAD";

    @VisibleForTesting
    protected static final String WEB_FORM_URL = "file:///android_asset/web_form.html";
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        mWebView = findViewById(R.id.web_view);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(urlFromIntent(getIntent()));
        mWebView.requestFocus();
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
    }
    private static String urlFromIntent(@NonNull Intent intent) {
        String url = intent.getStringExtra(KEY_URL_TO_LOAD);
        return !TextUtils.isEmpty(url) ? url : WEB_FORM_URL;
    }
}
