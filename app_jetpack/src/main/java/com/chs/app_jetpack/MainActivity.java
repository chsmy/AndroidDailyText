package com.chs.app_jetpack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.chs.lib_navannotation.ActivityDestination;
import com.chs.lib_navannotation.FragmentDestination;

@ActivityDestination(pageUrl = "main/home/MainActivity")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
