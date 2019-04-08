package com.chs.androiddailytext.retorfit;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.chs.androiddailytext.R;
import com.chs.androiddailytext.http.RestClient;

public class RetrofitTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_text);

        RestClient.builder().url("/v2/movie/top250")
               .build().get();
        ;



//        Retrofit retrofit = new Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .baseUrl("https://api.github.com/")
//                .build();
//
//        GitHubService service = retrofit.create(GitHubService.class);
//        Call<List<Repo>> call = service.listRepos("octocat");
//        try {
//            Response<List<Repo>> execute = call.execute();
//            List<Repo> body = execute.body();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        call.enqueue(new Callback<List<Repo>>() {
//            @Override
//            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
//                Log.i("response",response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<List<Repo>> call, Throwable t) {
//                Log.i("onFailure",t.toString());
//            }
//        });
    }
}
