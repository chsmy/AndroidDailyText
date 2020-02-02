package com.chs.androiddailytext.jetpack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.chs.androiddailytext.R;
import com.chs.androiddailytext.jetpack.paging.PagingActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.lifecycle.Observer;

/**
 * @author chs
 * date：2019-04-26 14:53
 * des：
 */
public class LiveDataSecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data_bus);
        LiveDataBus.get().with("text",String.class).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                ToastUtils.showShort(s);
            }
        });
    }
    public void sendMessage(View view) {
        LiveDataBus.get().with("text").setValue("我是第二个");
    }

    public void Jump(View view) {
        Intent intent = new Intent(this,LiveDataBusThirdActivity.class);
        startActivity(intent);
    }

    public void Paging(View view) {
        Intent intent = new Intent(this, PagingActivity.class);
        startActivity(intent);
    }
}
