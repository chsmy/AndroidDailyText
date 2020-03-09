package com.chs.app_test;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.chs.app_test.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_PICK = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;
    private String phoneNum;
    private ImageView mImageView;
    private ActivityMainBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mImageView =  findViewById(R.id.iv_take_photo);
        mBinding.btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.tvText.setText("Hello Espresso!");
            }
        });
        mBinding.btnToRecyclerview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RecyclerViewActivity.class));
            }
        });
        mBinding.btnToContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getApplicationContext(), ContactsActivity.class), REQUEST_CODE_PICK);
            }
        });
        mBinding.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(phoneNum)){
                    callPhone();
                }else {
                    Toast.makeText(getApplicationContext(),"先获取号码",Toast.LENGTH_SHORT).show();
                }
            }
        });
        mBinding.btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 takePhoto();
            }
        });
        mBinding.btnGoToWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 goToWebView();
            }
        });
    }

    private void goToWebView() {
        Intent intent = new Intent(this,WebViewActivity.class);
//        intent.putExtra(WebViewActivity.KEY_URL_TO_LOAD,"https://www.wanandroid.com/");
        startActivity(intent);
    }

    private void takePhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void callPhone() {
        final Intent intentToCall = new Intent(Intent.ACTION_CALL);
        intentToCall.setData(Uri.parse("tel:" + phoneNum));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(intentToCall);
        }else {
            Toast.makeText(getApplicationContext(),"没有打电话的权限，请去设置中打开",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PICK&&resultCode == RESULT_OK) {
            phoneNum = data.getExtras().getString(ContactsActivity.KEY_PHONE_NUMBER);
        }
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras != null || extras.containsKey("data")) {
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                mImageView.setImageBitmap(imageBitmap);
            }
        }
    }
}
