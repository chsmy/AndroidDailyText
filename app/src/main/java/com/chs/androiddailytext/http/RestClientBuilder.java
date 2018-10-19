package com.chs.androiddailytext.http;

import android.content.Context;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.RequestBody;

/**
 * 作者：chs
 * 时间：2018-10-19 18:07
 * 描述：
 */
public class RestClientBuilder {
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private String mUrl = null;
    private RequestBody mBody = null;
    private Context mContext = null;
    private File mFile = null;

    RestClientBuilder() {
    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    public final RestClient build() {
        return new RestClient(mUrl, PARAMS,
                 mBody, mFile, mContext);
    }
}
