package com.chs.androiddailytext.jetpack;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.blankj.utilcode.util.ToastUtils;
import com.chs.androiddailytext.R;

/**
 * @author：chs date：2019/4/29
 * des：
 */
public class LiveDataBusThirdActivity extends AppCompatActivity {
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
    }
}
