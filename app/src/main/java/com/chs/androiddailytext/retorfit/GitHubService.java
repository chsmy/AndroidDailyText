package com.chs.androiddailytext.retorfit;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * 作者：chs on 2018-03-22 15:05
 * 邮箱：657083984@qq.com
 */

public interface GitHubService {
    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);

    @Multipart
    @PUT("user/photo")
    Call<Repo> updateUser(@Part("photo") RequestBody photo, @Part("description") RequestBody description);

    @POST("/")
    Call<ResponseBody> sendNormal(@Body Repo repo);
}
