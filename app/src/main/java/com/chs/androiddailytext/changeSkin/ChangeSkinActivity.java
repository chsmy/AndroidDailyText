package com.chs.androiddailytext.changeSkin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.app.SkinAppCompatDelegateImpl;

import android.view.View;


import com.blankj.utilcode.util.ToastUtils;
import com.chs.androiddailytext.R;
import com.facebook.stetho.common.LogUtil;

import skin.support.SkinCompatManager;

public class ChangeSkinActivity extends AppCompatActivity {

    private boolean isChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_skin);
    }

    public void change(View view) {
//        SkinCompatManager.getInstance().loadSkin("app-skin.skin", CustomSDCardLoader.SKIN_LOADER_STRATEGY_SDCARD);
        isChange=!isChange;
        changeSkin(isChange);
    }


    @NonNull
    @Override
    public AppCompatDelegate getDelegate() {
        return SkinAppCompatDelegateImpl.get(this, this);
    }

    private void changeSkin(boolean isOpen){
        if(isOpen){
            ToastUtils.showShort("App换肤");
            //加载新皮肤
            SkinCompatManager.getInstance().loadSkin("night", new SkinCompatManager.SkinLoaderListener() {
                @Override
                public void onStart() {
                    LogUtil.i("=====开始换肤=====");
                }
                @Override
                public void onSuccess() {
                    LogUtil.i("=====换肤成功=====");
                }
                @Override
                public void onFailed(String errMsg) {
                    LogUtil.i("=====换肤失败： "+errMsg);
                }
            }, SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN);//后缀加载
        }else{
            ToastUtils.showShort("App皮肤还原");
            // 恢复应用默认皮肤
            SkinCompatManager.getInstance().restoreDefaultTheme();
        }
    }


}
