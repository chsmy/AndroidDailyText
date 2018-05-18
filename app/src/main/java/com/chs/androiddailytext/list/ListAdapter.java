package com.chs.androiddailytext.list;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chs.androiddailytext.R;

import java.util.List;

/**
 * 作者：chs
 * 时间：2018-05-16 16:32
 * 描述：
 */
public class ListAdapter extends BaseAdapter {
    private List<ListBean.DataEntity> mList;
    private LayoutInflater mInflater;
    private Context mContext;
    public ListAdapter(Context context,List<ListBean.DataEntity> list) {
        mList = list;
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_item,null);
            holder.tvname = convertView.findViewById(R.id.tvname);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        ListBean.DataEntity item = mList.get(position);
        holder.tvname.setPadding(item.getLevel() * 30, 0, 0, 0);
        if(item.isSelected()){
            holder.tvname.setTextColor(ContextCompat.getColor(mContext,R.color.blue));
        }else {
            holder.tvname.setTextColor(ContextCompat.getColor(mContext,R.color.black));
        }
        holder.tvname.setText(item.getPlace());
        return convertView;
    }
    public String getStr(){
        String str = "";
        for (ListBean.DataEntity e :
                mList) {
            if(e.isSelected()){
                str = str+e.getPlace()+"-";
            }
        }
        return str.substring(0,str.length()-1);
    }
    static class ViewHolder{
        TextView tvname;
    }
}
