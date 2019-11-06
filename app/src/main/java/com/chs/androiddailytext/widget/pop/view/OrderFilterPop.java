package com.chs.androiddailytext.widget.pop.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chs.androiddailytext.R;
import com.chs.androiddailytext.widget.pop.ItemFilter;
import com.chs.androiddailytext.widget.pop.SelectType;

import java.util.List;

/**
 * @author chs
 * Description: 列表筛选 弹出
 */
public class OrderFilterPop extends PartShadowPopupView {
    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerViewRight;
    private BaseQuickAdapter mAdapter;
    private BaseQuickAdapter mAdapterRight;
    private List<ItemFilter> mList;
    private int mCurrentPosition;
    private Context mContext;

    public OrderFilterPop(@NonNull Context context, List<ItemFilter> list) {
        super(context);
        this.mList = list;
    }
    @Override
    protected int getImplLayoutId() {
        return R.layout.repair_pop_order_filter;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        initView();
        intiRecyclerView(getContext());
    }
    private void initView() {
        LinearLayout llMain = findViewById(R.id.ll_main);
        llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mRecyclerView = findViewById(R.id.recycleview);
        mRecyclerViewRight = findViewById(R.id.recycleview_right);
        LinearLayout llConfirm = findViewById(R.id.ll_confirm);
        TextView tvReset = findViewById(R.id.tv_reset);
        TextView tvConfirm = findViewById(R.id.tv_confirm);
        if(popupInfo.mSelectType == SelectType.RADIO){
            llConfirm.setVisibility(View.GONE);
        }
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(popupInfo.xPopupCallback!=null){
                    popupInfo.xPopupCallback.id(mCurrentPosition,getIds());
                }
                dismiss();
            }
        });
        tvReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (ItemFilter bean : mList) {
                    bean.setSelected(false);
                }
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    public void notifyDataChanged(List<ItemFilter> list){
        mList.clear();
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
        if(mAdapterRight!=null){
            mAdapterRight.notifyDataSetChanged();
        }
    }
    private String getIds() {
        String ids = "";
        StringBuilder sb = new StringBuilder(ids);
        for (ItemFilter bean : mList) {
            if(bean.isSelected()) sb.append(bean.getId()).append(",");
        }
        if(!TextUtils.isEmpty(sb.toString()))
            return sb.toString().substring(0,sb.toString().length()-1);
        else return "";
    }
    private void intiRecyclerView(Context context) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerViewRight.setLayoutManager(new LinearLayoutManager(context));
        mAdapter = new BaseQuickAdapter<ItemFilter, BaseViewHolder>(R.layout.item_filter_list, mList) {
            @Override
            protected void convert(BaseViewHolder helper, ItemFilter item) {
                TextView tvName = helper.getView(R.id.tv_name);
                tvName.setText(item.getName());
                if(popupInfo.bigText){
                    tvName.setHeight(SizeUtils.dp2px(44));
                    tvName.setPadding(0,0,0,0);
                    tvName.setGravity(Gravity.CENTER);
                    tvName.setTextSize(16);
                }
                if (item.isSelected()) {
                    tvName.setTextColor(ContextCompat.getColor(mContext, R.color.tab_color_blue));
                } else {
                    tvName.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                }
                tvName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(popupInfo.mSelectType == SelectType.RADIO){
                            for (int i = 0; i < getData().size(); i++) {
                                if(i == helper.getAdapterPosition()){
                                    getData().get(i).setSelected(true);
                                }else {
                                    getData().get(i).setSelected(false);
                                }
                            }
                            if(popupInfo.hasSubOption){
                                //把子项置为未选中
                                setSubOptionFalse(getData());
                                mRecyclerViewRight.setVisibility(View.GONE);
                            }
                            if(item.getSubList()==null){
                                if(popupInfo.xPopupCallback!=null){
                                    popupInfo.xPopupCallback.id(helper.getAdapterPosition(),item.getId());
                                }
                                dismiss();
                            }else {
                                setSubRecyclerView(item.getSubList(),helper.getAdapterPosition());
                            }
                            notifyDataSetChanged();
                        }else {
                            mCurrentPosition = helper.getAdapterPosition();
                            if(popupInfo.hasAllOptions){
                                if(mCurrentPosition!=0){
                                    mList.get(0).setSelected(false);
                                    ItemFilter filter = mList.get(mCurrentPosition);
                                    filter.setSelected(!filter.isSelected());
                                }else {
                                    for (int i = 0; i < mList.size(); i++) {
                                        ItemFilter filter = mList.get(i);
                                        if(i == 0){
                                            filter.setSelected(true);
                                        }else {
                                            filter.setSelected(false);
                                        }
                                    }
                                }
                            }else {
                                item.setSelected(!item.isSelected());
                            }
                            notifyDataSetChanged();
                        }
                    }
                });
            }
        };
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setSubOptionFalse(List<ItemFilter> data) {
        for (ItemFilter filter : data) {
            if(filter.getSubList()!=null){
                for (ItemFilter f : filter.getSubList()) {
                    f.setSelected(false);
                }
            }
        }
    }

    /**
     * 设置第二级的数据
     */
    private void setSubRecyclerView(List<ItemFilter> data,int position) {
        mAdapterRight = new BaseQuickAdapter<ItemFilter, BaseViewHolder>(R.layout.item_filter_list, data) {
            @Override
            protected void convert(BaseViewHolder helper, ItemFilter item) {
                helper.setText(R.id.tv_name, item.getName());
                if (item.isSelected()) {
                    helper.setTextColor(R.id.tv_name, ContextCompat.getColor(mContext, R.color.tab_color_blue));
                } else {
                    helper.setTextColor(R.id.tv_name, ContextCompat.getColor(mContext, R.color.black));
                }
                helper.getView(R.id.tv_name).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (popupInfo.mSelectType == SelectType.RADIO) {
                            for (int i = 0; i < getData().size(); i++) {
                                if (i == helper.getAdapterPosition()) {
                                    getData().get(i).setSelected(true);
                                } else {
                                    getData().get(i).setSelected(false);
                                }
                            }
                            mList.get(position).setName(getData().get(helper.getAdapterPosition()).getName());
                            if (popupInfo.xPopupCallback != null) {
                                popupInfo.xPopupCallback.id(position, item.getId());
                            }
                            notifyDataSetChanged();
                            dismiss();
                        }
                    }
                });
            }
        };
        mRecyclerViewRight.setVisibility(View.VISIBLE);
        mRecyclerViewRight.setAdapter(mAdapterRight);
    }
    @Override
    protected void onShow() {
        super.onShow();
    }

    @Override
    protected void onDismiss() {
        super.onDismiss();
    }
}
