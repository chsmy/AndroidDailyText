package com.chs.androiddailytext.jetpack.paging;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.chs.androiddailytext.R;

/**
 * @author：chs
 * date：2020/2/2
 * des：
 */
public class PagingAdapter extends PagedListAdapter<ArticleResponse.DataBean.DatasBean, PagingAdapter.ViewHolder> {

    protected PagingAdapter() {
        super(new DiffUtil.ItemCallback<ArticleResponse.DataBean.DatasBean>() {
            @Override
            public boolean areItemsTheSame(@NonNull ArticleResponse.DataBean.DatasBean oldItem, @NonNull ArticleResponse.DataBean.DatasBean newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull ArticleResponse.DataBean.DatasBean oldItem, @NonNull ArticleResponse.DataBean.DatasBean newItem) {
                return oldItem.equals(newItem);
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
          ArticleResponse.DataBean.DatasBean bean = getItem(position);
          holder.tvname.setText(bean.getTitle());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvname;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvname = itemView.findViewById(R.id.tvname);
        }
    }
}
