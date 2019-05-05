package com.chs.androiddailytext.tinker;

import android.os.Bundle;

import com.chs.androiddailytext.R;
import com.chs.androiddailytext.base.BaseActivity;

import java.io.File;

/**
 * @author chs
 * date：2019-05-05 17:50
 * des：
 */
public class TinkerActivity extends BaseActivity {
    private static final String FILE_END = ".apk";
    private String mPatchDir = "";
    @Override
    public int getContentView(Bundle savedInstanceState) {
        return R.layout.activity_tinker;
    }

    @Override
    public void initView() {
        mPatchDir = getExternalCacheDir().getAbsolutePath()+"/tpatch/";
        File file = new File(mPatchDir);
        if(file.exists()){
            file.mkdirs();
        }
    }

    public void loadPatch(){
        TinkerManager.loadPath(getPatchName());
    }

    private String getPatchName(){
        return mPatchDir.concat("chs").concat(FILE_END);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }
}
