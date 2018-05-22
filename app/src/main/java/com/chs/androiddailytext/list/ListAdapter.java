package com.chs.androiddailytext.list;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chs.androiddailytext.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：chs
 * 时间：2018-05-16 16:32
 * 描述：
 */
public class ListAdapter extends BaseAdapter {
    private List<ListBean.DataEntity> mList;
    private List<ListBean.DataEntity> mClicked = new ArrayList<>();
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
    public ListBean.DataEntity clicked(int position){
        ListBean.DataEntity entityClicked = mList.get(position);

        entityClicked.setClicked(!entityClicked.isClicked());

        List<ListBean.DataEntity> current = new ArrayList<>();
        //把点击过的都放入到一个list中存放
        if(entityClicked.isClicked()){
            mClicked.add(entityClicked);
        }
        maopao(entityClicked);

        for (ListBean.DataEntity entity:mList){

            if(!entity.isClicked()&&entity.isExpend()){
                setNoClicked(entity);
                entity.setExpend(false);
            }
            //只有可见的才放进去
            if(entity.isVisible()){
                current.add(entity);
            }
            //ietm 被点击 它有子集 并且不是展开状态 才把子集放进去
            if(entity.isClicked()&&entity.getLists()!=null&&entity.getLists().size()>0&& !entity.isExpend()){
                entity.setExpend(!entity.isExpend());
                for(ListBean.DataEntity bean:entity.getLists()){
                    bean.setVisible(true);
                }
                current.addAll(entity.getLists());
            }
        }

        mList.clear();
        mList.addAll(current);
        notifyDataSetChanged();
        return entityClicked;
    }
    //如果该条isclick为false，那么它一定不是展开状态，它的下级也一定不是展开状态
    private void setNoClicked(ListBean.DataEntity entity){
        for(ListBean.DataEntity bean:entity.getLists()){
            bean.setVisible(false);
            bean.setClicked(false);
            bean.setExpend(false);
            if(bean.getLists()!=null&&bean.getLists().size()>0){
                setNoClicked(bean);
            }
        }
    }
    //冒泡找出需要变蓝的item
    private void maopao(ListBean.DataEntity entity){
        for (ListBean.DataEntity e:mClicked){
            if(entity.getLevel()>e.getLevel()&&entity.getPid().equals(e.getId())){
                e.setSelected(true);
                maopao(e);
            }else {
                e.setSelected(false);
            }
        }
        entity.setSelected(true);
    }
}
