package com.chs.androiddailytext.widget.selecttext;

import android.os.Build;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.lang.reflect.Field;

/**
 * @author: chs
 * @date: Create in 2020/9/28
 * @description
 */
public class ReflectTextView {

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void reflect(TextView textView){
        try {
            Field mEditor = TextView.class.getDeclaredField("mEditor");
            mEditor.setAccessible(true);
            Object mEditorObj = mEditor.get(textView);
            Class editorClass=Class.forName("android.widget.Editor");
            Field mCustomSelectionActionModeCallbackField = editorClass.getDeclaredField("mCustomSelectionActionModeCallback");
            mCustomSelectionActionModeCallbackField.setAccessible(true);
            mCustomSelectionActionModeCallbackField.set(mEditorObj,new MyActionMode());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
