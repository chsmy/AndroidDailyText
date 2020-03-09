package com.chs.app_test;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ContactsActivity extends AppCompatActivity {

    static final String KEY_PHONE_NUMBER = "key_phone_number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        final Intent resultData = new Intent();
        resultData.putExtra(KEY_PHONE_NUMBER, "123456789");
        setResult(RESULT_OK,resultData);
        finish();
    }
}
