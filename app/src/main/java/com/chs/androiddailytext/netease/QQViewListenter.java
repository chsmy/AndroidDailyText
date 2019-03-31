package com.chs.androiddailytext.netease;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.widget.TextView;

import static com.chs.androiddailytext.netease.QQBubbleView.EXECUTE_STATE_BACK;

/**
 * author：chs
 * date：2019/3/30
 * des：
 */
public class QQViewListenter implements View.OnTouchListener , QQBubbleView.OnExecuteFinishListener {

    private Context mContext;

    private ViewGroup mViewGroup;
    private QQBubbleView mQQBubbleView;
    private View currentClickView;
    public QQViewListenter(Context context) {
        mContext = context;
        Window window = ((Activity) context).getWindow();
        mViewGroup = (ViewGroup) window.getDecorView();
        mQQBubbleView = new QQBubbleView(context);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        currentClickView = v;
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if(mViewGroup!=null){
                mViewGroup.addView(mQQBubbleView);
            }
            ViewParent parent = v.getParent();
            if (parent == null) {
                return false;
            }
            if(v instanceof TextView){
                String text = ((TextView) v).getText().toString();
                mQQBubbleView.setText(text);
            }
            //防止父容器消费事件
            parent.requestDisallowInterceptTouchEvent(true);
            int width = v.getWidth();
            mQQBubbleView.setCenter(event.getRawX(),event.getRawY(),width/2);
            mQQBubbleView.setOnDismissListener(this);
            currentClickView.setVisibility(View.INVISIBLE);
        }
        mQQBubbleView.onTouchEvent(event);
        return true;
    }

    @Override
    public void onFinish(int type) {
        if(mViewGroup!=null&&mQQBubbleView!=null){
            mViewGroup.removeView(mQQBubbleView);
        }
        if(type == EXECUTE_STATE_BACK){
            currentClickView.setVisibility(View.VISIBLE);
        }else {
            currentClickView.setVisibility(View.GONE);
        }
    }
}
