package com.chs.androiddailytext.popwindow;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chs.androiddailytext.R;

import java.util.List;

/**
 * 作者：chs
 * 时间：2018-07-09 14:29
 * 描述：
 */
public class MyPopWindow extends PopupWindow {
    private LayoutInflater mInflater;
    public MyPopWindow(Context context , List<PopListBean> listBeans) {
        super(context);
        mInflater = LayoutInflater.from(context);
        View popView = mInflater.inflate(R.layout.pop_list, null);
        ListView lv_rooms =  popView.findViewById(R.id.lv_rooms);
        ListAdapter adapter = new ListAdapter(context,listBeans);
        lv_rooms.setAdapter(adapter);
        RelativeLayout rl_main = popView.findViewById(R.id.rl_main);
        rl_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        setContentView(popView);
        setFocusable(true);
        setOutsideTouchable(true);
        setAnimationStyle(R.style.PopupWindowAnimation);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);

        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });
    }
    public void show(final View anchor, final int xoff, final int yoff) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect visibleFrame = new Rect();
            anchor.getGlobalVisibleRect(visibleFrame);
            int height = anchor.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
            setHeight(height);
            showAsDropDown(anchor, xoff, yoff);
        } else {
            showAsDropDown(anchor, xoff, yoff);
        }
    }
    private class ListAdapter extends MyBaseAdapter<PopListBean ,ListView> {

        ListAdapter(Context ct, List<PopListBean> list) {
            super(ct, list);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_pop_list, null);
                holder = new ViewHolder();
                holder.mTvRoomName = convertView.findViewById(R.id.tv_name);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }
            PopListBean dataEntity = list.get(position);
            holder.mTvRoomName.setText(dataEntity.getName());
            return convertView;
        }
    }
    static class ViewHolder {

        TextView mTvRoomName;

    }
}
