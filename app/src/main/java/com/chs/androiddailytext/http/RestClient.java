package com.chs.androiddailytext.http;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 作者：chs
 * 时间：2018-10-19 17:33
 * 描述：
 */
public class RestClient {
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final String URL;
    private final RequestBody BODY;
//    private final LoaderStyle LOADER_STYLE;
    private final File FILE;
    private final Context CONTEXT;

     RestClient(String URL, Map<String, Object> params, RequestBody BODY, File FILE, Context CONTEXT) {
        this.URL = URL;
         PARAMS.putAll(params);
        this.BODY = BODY;
        this.FILE = FILE;
        this.CONTEXT = CONTEXT;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod method) {
        final ApiService service = RestCreator.getRestService();
        Observable observable = null;
        switch (method) {
            case GET:
                observable = service.get(URL, PARAMS);
                break;
            case POST:
                observable = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                observable = service.postRaw(URL, BODY);
                break;
            case PUT:
                observable = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                observable = service.putRaw(URL, BODY);
                break;
            case DELETE:
                observable = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                observable = service.upload(URL, body);
                break;
            default:
                break;
        }
        toSubscribe(observable);
    }
    private <T> void toSubscribe(Observable<T> o){
        o.subscribeOn(Schedulers.io())//指定Observable
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//指定observer
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(T t) {
                        Log.i("onNext",t.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        if (BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    public final void put() {
        if (BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

    public final void upload() {
        request(HttpMethod.UPLOAD);
    }
//
//    public final void download() {
//        new DownloadHandler(URL, REQUEST, DOWNLOAD_DIR, EXTENSION, NAME,
//                SUCCESS, FAILURE, ERROR)
//                .handleDownload();
//    }
}
