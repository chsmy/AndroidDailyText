package com.chs.androiddailytext.jetpack;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author chs
 * date: 2020-04-17 14:56
 * des:
 */
public class EmptyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        finish();
    }
}
