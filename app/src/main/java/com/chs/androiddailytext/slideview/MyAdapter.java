package com.chs.androiddailytext.slideview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chs.androiddailytext.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author：chs date：2019/4/17
 * des：
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    private List<String> mData;
    private Context mContext;

    public MyAdapter(List<String> data, Context context) {
        mData = data;
        mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
         holder.mTextView.setText("item"+holder.getLayoutPosition());
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
       TextView mTextView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tvname);
        }
    }

}
