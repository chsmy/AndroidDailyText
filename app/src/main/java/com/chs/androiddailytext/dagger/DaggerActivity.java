package com.chs.androiddailytext.dagger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.chs.androiddailytext.R;

import javax.inject.Inject;

public class DaggerActivity extends AppCompatActivity {
    @Inject
    User mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);
//        AppLog.i("mUser",mUser.getName()+"----"+mUser.getAge());
    }
}
