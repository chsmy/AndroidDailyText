package com.chs.androiddailytext;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.chs.androiddailytext.permission.PermissionActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void permission(View view) {
        Intent intent = new Intent(this, PermissionActivity.class);
        startActivity(intent);
    }

    public void ViewActivity(View view) {
        Intent intent = new Intent(this, ViewActivity.class);
        startActivity(intent);
    }

    public void AnimatorActivity(View view) {
        Intent intent = new Intent(this, AnimatorActivity.class);
        startActivity(intent);
    }
    public void pullRefreshActivity(View view) {
        Intent intent = new Intent(this, PullRefreshActivity.class);
        startActivity(intent);
    }

    public void toChart(View view) {
        Intent intent = new Intent(this, ChartActivity.class);
        startActivity(intent);
    }
}
