package com.chs.androiddailytext.changeSkin;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.chs.androiddailytext.R;

import skin.support.SkinCompatManager;

public class ChangeSkinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_skin);
    }

    public void change(View view) {
        SkinCompatManager.getInstance().loadSkin("app-skin.skin", CustomSDCardLoader.SKIN_LOADER_STRATEGY_SDCARD);
    }
}
