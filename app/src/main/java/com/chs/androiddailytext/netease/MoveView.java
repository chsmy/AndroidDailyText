package com.chs.androiddailytext.netease;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

/**
 * @author：chs date：2019/5/3
 * des：
 */
public class MoveView extends View {

    private float lastRawX;
    private float lastRawY;

    public MoveView(Context context) {
        super(context);
    }

    public MoveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MoveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //记录手指点击的坐标
                lastRawX = event.getRawX();
                lastRawY = event.getRawY();
                Toast.makeText(getContext(),"点击到我啦",Toast.LENGTH_SHORT).show();
                break;
            case MotionEvent.ACTION_MOVE:
                //相对坐标
                int dx = (int) (event.getRawX() - lastRawX);
                //相对坐标
                int dy = (int) (event.getRawY() - lastRawY);
                //设置按钮位置
                layout(getLeft() + dx, getTop() + dy, getRight() + dx, getBottom() + dy);
                lastRawX = event.getRawX();
                lastRawY = event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                break;
                default:
        }
        return true;
    }
}
