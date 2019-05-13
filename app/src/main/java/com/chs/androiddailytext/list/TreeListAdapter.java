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

    /**
     * 添加或者删除完之后需要跳过多少个
     */
    int moreSize;
    /**
     * 重新组装数据
     * 点击位置如果有下级数据，并且是点击状态，添加下级数据
     * 点击位置如果有下级数据，并且不是点击状态，删除下级数据
     * @param currentPosition 当前点击的位置
     */
    private void reassemblyData(int currentPosition) {
        List<TreeListBean> datas = new ArrayList<>();
        for (int i = 0; i < mListBeans.size(); i++) {
            TreeListBean current = mListBeans.get(i);
            if(i == currentPosition){
               datas.add(mListBeans.get(i+moreSize));
                List<TreeListBean> listBeans;
               //点击状态，添加下级数据
               if(mListBeans.get(i).isSelected()){
                   listBeans = new ArrayList<>();
                   int level = mListBeans.get(i).getLevel()+1;
                   int pid = mListBeans.get(i).getId();
                   listBeans.add(new TreeListBean(i,pid,level,"item+",current));
                   listBeans.add(new TreeListBean(i,pid,level,"item+",current));
                   listBeans.add(new TreeListBean(i,pid,level,"item+",current));
                   datas.addAll(listBeans);
                   setMoreSize(current,listBeans.size());
               }else {
                   moreSize += current.getMoreSize();
                   current.setSelected(false);
                   clearMoreSize(current,current.getMoreSize());
               }
           }else {
                //如果不是当前点击,跳过它下级的数据
               if(i<currentPosition||i>currentPosition+moreSize){
                   datas.add(current);
               }
           }
        }
        mListBeans.clear();
        mListBeans.addAll(datas);
        moreSize = 0;
    }

    /**
     * 减去折叠起来的下级个数
     * @param current
     * @param moreSize
     */
    private void clearMoreSize(TreeListBean current, int moreSize) {
        current.setMoreSize(current.getMoreSize()-moreSize);
        if(current.getParent()!=null){
            clearMoreSize(current.getParent(),moreSize);
        }
    }

    /**
     * 加上新添加的下级个数
     * @param current
     * @param moreSize
     */
    private void setMoreSize(TreeListBean current, int moreSize) {
        current.setSelected(true);
        current.setMoreSize(current.getMoreSize()+moreSize);
        if(current.getParent()!=null){
           setMoreSize(current.getParent(),moreSize);
        }
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
