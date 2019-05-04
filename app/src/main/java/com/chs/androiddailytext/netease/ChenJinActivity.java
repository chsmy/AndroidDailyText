package com.chs.androiddailytext.netease;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.core.view.ViewCompat;

import com.chs.androiddailytext.R;
import com.chs.androiddailytext.base.BaseActivity;
import com.chs.androiddailytext.utils.StatusBarUtil;

/**
 * @author：chs date：2019/5/3
 * des：
 */
public class ChenJinActivity extends BaseActivity {
    @Override
    public int getContentView(Bundle savedInstanceState) {
        return R.layout.activity_chenjin;
    }

    @Override
    public void initView() {
        LinearLayout linearLayout = findViewById(R.id.lll);
        StatusBarUtil.setStateBar(this,linearLayout);
//        setTranslucentStatusBar(true);
    }

    private static int getStatusBarHeight(Context context) {
        int result = 0;
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            result = context.getResources().getDimensionPixelOffset(resId);
        }
        return result;
    }
    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }
}
