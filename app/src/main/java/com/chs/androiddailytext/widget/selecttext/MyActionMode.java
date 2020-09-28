package com.chs.androiddailytext.widget.selecttext;

import android.os.Build;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.blankj.utilcode.util.Utils;
import com.chs.androiddailytext.R;

/**
 * @author: chs
 * @date: Create in 2020/9/28
 * @description  长按textview 弹框中添加自定义按钮  可惜 小米这个破系统不支持
 */
@RequiresApi(api = Build.VERSION_CODES.M)
public class MyActionMode extends ActionMode.Callback2 {
    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        MenuInflater inflater = mode.getMenuInflater();
        inflater.inflate(R.menu.text_selection,menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        if(item.getItemId() == R.id.menu_custom){
            Toast.makeText(Utils.getApp(),"哈哈哈",Toast.LENGTH_SHORT).show();
            mode.finish();
        }
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {

    }
}
