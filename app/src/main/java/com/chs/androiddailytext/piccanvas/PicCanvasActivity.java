package com.chs.androiddailytext.piccanvas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chs.androiddailytext.R;

import java.io.File;
import java.util.UUID;

import me.kareluo.imaging.IMGEditActivity;
import me.kareluo.imaging.gallery.model.IMGImageInfo;
import me.kareluo.imaging.gallery.model.IMGImageViewModel;

public class PicCanvasActivity extends AppCompatActivity {
    public static final int REQ_IMAGE_EDIT = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_canvas);
        ImageView imageView = findViewById(R.id.imageview);

        final String path = "/storage/emulated/0/DCIM/Screenshots/Screenshot_2018-04-16-09-32-44-974_com.hsm.bxt.png";
        final File  mImageFile = new File(getCacheDir(), UUID.randomUUID().toString() + ".jpg");

        Glide.with(this).load(new File(path)).into(imageView);

        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                IMGImageViewModel model = new IMGImageViewModel(Uri.fromFile(new File(path)));
                IMGImageInfo imageInfo = new IMGImageInfo(model);

                Intent intent = new Intent(PicCanvasActivity.this,IMGEditActivity.class);
                intent.putExtra(IMGEditActivity.EXTRA_IMAGE_URI,imageInfo.getUri());
                intent.putExtra(IMGEditActivity.EXTRA_IMAGE_SAVE_PATH,mImageFile);
                startActivityForResult(intent,REQ_IMAGE_EDIT);
                overridePendingTransition(0,0);
                return false;
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
