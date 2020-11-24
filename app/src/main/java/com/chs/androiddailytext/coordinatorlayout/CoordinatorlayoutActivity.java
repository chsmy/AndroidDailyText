package com.chs.androiddailytext.coordinatorlayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.chs.androiddailytext.R;


/**
 * @author chs
 */
public class CoordinatorlayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinatorlayout);
    }
    public void first(View view) {
        Intent intent = new Intent(this,FirstActivity.class);
        startActivity(intent);
    }

    public void second(View view) {
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
    }

    public void third(View view) {
        Intent intent = new Intent(this,ThirdActivity.class);
        startActivity(intent);
    }

    public void four(View view) {
        Intent intent = new Intent(this,FourActivity.class);
        startActivity(intent);
    }

    public void five(View view) {
        Intent intent = new Intent(this,FiveActivity.class);
        startActivity(intent);
    }

    public void six(View view) {
        Intent intent = new Intent(this,SixActivity.class);
        startActivity(intent);
    }
}
