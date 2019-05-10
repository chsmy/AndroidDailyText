package com.chs.androiddailytext.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chs.androiddailytext.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author chs
 * date：2019-05-10 16:30
 * des：
 */
public class TreeListAdapter extends RecyclerView.Adapter<TreeListAdapter.TreeListViewHolder> {

    private List<TreeListBean> mListBeans;
    public TreeListAdapter(List<TreeListBean> listBeans) {
        mListBeans = listBeans;
    }

    @NonNull
    @Override
    public TreeListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new TreeListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TreeListViewHolder holder, int position) {
        TreeListBean item = mListBeans.get(holder.getAdapterPosition());
        holder.mTextView.setText(item.getName());
        holder.mTextView.setPadding(item.getLevel() * 30, 0, 0, 0);

        if(item.isSelected()){
            holder.mTextView.setTextColor(ContextCompat.getColor(holder.itemView.getContext(),R.color.blue));
        }else {
            holder.mTextView.setTextColor(ContextCompat.getColor(holder.itemView.getContext(),R.color.black));
        }
        holder.mItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setSelected(!item.isSelected());
                if(item.isHasLower()){
                    reassemblyData(holder.getAdapterPosition());
                }
                notifyDataSetChanged();
            }
        });
    }

    int moreSize;
    /**
     * 重新组装数据
     * @param currentPosition 当前点击的位置
     */
    private void reassemblyData(int currentPosition) {
        List<TreeListBean> datas = new ArrayList<>();
        for (int i = 0; i < mListBeans.size(); i++) {
           if(i == currentPosition){
               datas.add(mListBeans.get(i+moreSize));
               //点击状态，添加下级数据
               if(mListBeans.get(i).isSelected()){
                   List<TreeListBean> listBeans = new ArrayList<>();
                   int level = mListBeans.get(i).getLevel()+1;
                   listBeans.add(new TreeListBean("1","2",level,"item2"));
                   listBeans.add(new TreeListBean("3","2",level,"item2"));
                   listBeans.add(new TreeListBean("3","2",level,"item2"));
                   datas.addAll(listBeans);
               }else {
                   moreSize = 3;
               }
           }else {
               //如果不是点击状态,跳过它下级的数据
               if(i+moreSize<mListBeans.size()){
                   datas.add(mListBeans.get(i+moreSize));
               }
           }
        }
        mListBeans.clear();
        mListBeans.addAll(datas);
        moreSize = 0;
    }
    
    @Override
    public int getItemCount() {
        return mListBeans.size();
    }

    class TreeListViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        RelativeLayout mItem;
        public TreeListViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tvname);
            mItem = itemView.findViewById(R.id.item);
        }
    }

}
