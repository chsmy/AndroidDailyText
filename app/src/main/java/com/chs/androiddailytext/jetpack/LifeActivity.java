package com.chs.androiddailytext.jetpack;

import android.os.Bundle;
import android.widget.TextView;

import com.chs.androiddailytext.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

/**
 * @author chs
 * date：2019-04-15 14:07
 * des：
 */
public class LifeActivity extends AppCompatActivity {
    private NameViewModel1 mViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
//        getLifecycle().addObserver(new MyListener());

        final TextView textView = findViewById(R.id.tv_name);
        mViewModel = ViewModelProviders.of(this).get(NameViewModel1.class);

        LiveData<String> userNameMap = Transformations.map(mViewModel.getCurrentName(), new Function<String, String>() {
            @Override
            public String apply(String input) {
                return input + "哈哈哈";
            }
        });

        userNameMap.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);
            }
        });
    }
}
