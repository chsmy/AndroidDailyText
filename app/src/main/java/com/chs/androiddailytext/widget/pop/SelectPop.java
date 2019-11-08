package com.chs.androiddailytext.widget.pop;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.chs.androiddailytext.R;
import com.chs.androiddailytext.utils.DensityUtil;
import com.chs.androiddailytext.widget.pop.interfaces.SimpleCallback;
import com.chs.androiddailytext.widget.pop.view.OrderFilterPop;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chs
 * date：2019-11-06 15:55
 * des：下拉弹窗选择
 */
public class SelectPop extends LinearLayout {

    public interface OnSelectedListener{
        /**
         *
         * @param pos 整个大类中的位置
         * @param position 每个item下面的子item的位置
         * @param id 选择的条目的id
         */
        void selected(int pos,int position, String id);
    }
    private OnSelectedListener mOnSelectedListener;

    public void setOnSelectedListener(OnSelectedListener onSelectedListener) {
        mOnSelectedListener = onSelectedListener;
    }

    private List<OrderFilterPop> mPops = new ArrayList<>();

    public SelectPop(Context context) {
        super(context);
        initData();
    }

    public SelectPop(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    public SelectPop(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    private void initData() {
      setOrientation(HORIZONTAL);
      setBackgroundColor(ContextCompat.getColor(getContext(),R.color.white));
      setGravity(Gravity.CENTER_VERTICAL);
    }


    public void setData(List<TitleBean> data) {
        setItemView(data);
        setItemClick(data);
    }

    private void setItemClick(List<TitleBean> data) {
        for (int i = 0; i < getChildCount(); i++) {
            LinearLayout itemWrap = (LinearLayout) getChildAt(i);
            TextView titleView = (TextView) itemWrap.getChildAt(0);
            int finalI1 = i;
            itemWrap.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mPops.size()>0&&mPops.get(finalI1)!=null){
                        showOrDismiss(finalI1);
                        setTextColor(titleView);
                    }
                }
            });
        }
    }
    public void showOrDismiss(int pos) {
        for (int i = 0; i < mPops.size(); i++) {
            OrderFilterPop orderFilterPop = mPops.get(i);
            if(i == pos){
                if(orderFilterPop.isShow()){
                    orderFilterPop.dismiss();
                }else {
                    orderFilterPop.show();
                }
            }else {
                if(orderFilterPop.isShow()){
                    orderFilterPop.dismiss();
                }
            }
        }
    }
    private void setItemView(List<TitleBean> data) {
        mPops.clear();
        for (int i = 0; i < data.size(); i++) {
            TitleBean item = data.get(i);
            LinearLayout wrapView = getWrapView();
            TextView titleView = getTitleView(item);
            wrapView.addView(titleView);
            addView(wrapView);
            setPops(wrapView,titleView,item,i);
        }
    }

    private void setPops(LinearLayout wrapView, TextView titleView,TitleBean item,int pos) {
        OrderFilterPop popWindow = (OrderFilterPop) new XPopup.Builder(getContext())
                .atView(wrapView)
                .autoOpenSoftInput(false)
                .dismissOnTouchOutside(false)
                .selectType(SelectType.RADIO)
                .setPopupCallback(new SimpleCallback() {
                    @Override
                    public void id(int position, String id) {
                        super.id(position, id);
                        if(mOnSelectedListener!=null){
                            mOnSelectedListener.selected(pos,position,id);
                        }
                        if(position == 0){
                            titleView.setText(item.getName());
                        }else {
                            titleView.setText(item.getSelectData().get(position).getName());
                        }
                    }
                    @Override
                    public void onDismiss() {
                        setDismissState(titleView);
                    }
                })
                .asCustom(new OrderFilterPop(getContext(), item.getSelectData()));
        mPops.add(popWindow);
    }

    private LinearLayout getWrapView() {
        LinearLayout wrap = new LinearLayout(getContext());
        LayoutParams wrapLayoutParams = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
        wrapLayoutParams.weight = 1;
        wrap.setGravity(Gravity.CENTER);
        wrap.setPadding(0, DensityUtil.dip2px(getContext(), 10), 0, DensityUtil.dip2px(getContext(), 10));
        wrap.setLayoutParams(wrapLayoutParams);
        return wrap;
    }

    private TextView getTitleView(TitleBean item) {
        TextView title = new TextView(getContext());
        title.setText(item.getName());
        title.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        title.setGravity(Gravity.CENTER);
        title.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.mipmap.filter_arrow_down, 0);
        title.setCompoundDrawablePadding(DensityUtil.dip2px(getContext(), 5));
        return title;
    }

    public void setTextColor(TextView tv) {
        tv.setTextColor(ContextCompat.getColor(getContext(), R.color.tab_color_blue));
        tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.filter_arrow_up, 0);
    }

    public void setDismissState(TextView tv) {
        tv.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.filter_arrow_down, 0);
    }
}
