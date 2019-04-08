package com.chs.androiddailytext.piccanvas;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.chs.androiddailytext.R;

import java.io.File;
import java.util.UUID;

import androidx.appcompat.app.AppCompatActivity;


public class PicCanvasActivity extends AppCompatActivity {
    public static final int REQ_IMAGE_EDIT = 1;
    ImageView imageView;
    File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_canvas);
        imageView = findViewById(R.id.imageview);

        final String path = "/storage/emulated/0/com/kingdee/neighbour/cache/1520911588609.jpg";
//        file:///storage/emulated/0/DCIM/Screenshots/Screenshot_2018-04-16-09-32-38-777_com.hsm.bxt.png
        final File  mImageFile = new File(getCacheDir(), UUID.randomUUID().toString() + ".jpg");
         file = new File(path);
//        Glide.with(this).load(file).into(imageView);

//        imageView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                IMGImageViewModel model = new IMGImageViewModel(Uri.fromFile(new File(path)));
//                IMGImageInfo imageInfo = new IMGImageInfo(model);
//
//                Intent intent = new Intent(PicCanvasActivity.this,IMGEditActivity.class);
//                intent.putExtra(IMGEditActivity.EXTRA_IMAGE_URI,imageInfo.getUri());
//                intent.putExtra(IMGEditActivity.EXTRA_IMAGE_SAVE_PATH,path);
//                startActivityForResult(intent,REQ_IMAGE_EDIT);
//                overridePendingTransition(0,0);
//                return false;
//            }
//        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        Glide.with(this).load(file).into(imageView);
        imageView.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
    }
}
