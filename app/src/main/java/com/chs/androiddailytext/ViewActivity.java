package com.chs.androiddailytext;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSeekBar;
import android.util.Log;
import android.widget.SeekBar;

import com.chs.androiddailytext.widget.ChangedImageView;

public class ViewActivity extends AppCompatActivity {
    private AppCompatSeekBar mSeekBar;
    private ChangedImageView mChangedImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        mChangedImageView = findViewById(R.id.imageview);
        mSeekBar = findViewById(R.id.seekbar);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mChangedImageView.setCha(progress);
                Log.i("progress",progress+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
