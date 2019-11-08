package com.chs.androiddailytext.popwindow;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.LogUtils;
import com.chs.androiddailytext.R;
import com.chs.androiddailytext.widget.pop.ItemFilter;
import com.chs.androiddailytext.widget.pop.SelectPop;
import com.chs.androiddailytext.widget.pop.TitleBean;

import java.util.ArrayList;
import java.util.List;

public class PopActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);

        List<ItemFilter> stateData = new ArrayList<>();
        List<ItemFilter> accountData = new ArrayList<>();
        List<ItemFilter> sortData = new ArrayList<>();

        String[] orderState = getResources().getStringArray(R.array.repair_order_state_arr);
        for (int i = 0; i < orderState.length; i++) {
            if (i == 0) {
                stateData.add(new ItemFilter(orderState[i], String.valueOf(i)));
            } else if (i < 6) {
                stateData.add(new ItemFilter(orderState[i], String.valueOf(i + 1)));
            } else if (i == 6) {
                //挂单
                stateData.add(getSubState(this,orderState[i], String.valueOf(i + 1),
                        0,R.array.repair_order_state_entry));
            } else if (i == 7) {
                //超时
                stateData.add(getSubState(this,orderState[i], String.valueOf(i + 1),
                        1,R.array.repair_order_state_time));
            } else {
                //取消
                stateData.add(getSubState(this,orderState[i], String.valueOf(i + 1),
                        2,R.array.repair_order_state_cancel));
            }
        }

        String[] account = getResources().getStringArray(R.array.repair_account);
        setOutOrderList(account, accountData);

        String[] sortState = getResources().getStringArray(R.array.repair_sort_left);
        setCustomOrderList(sortState,new String[]{"2","5","6"},sortData);

        List<TitleBean> data = new ArrayList<>();
        data.add(new TitleBean("状态",false,stateData));
        data.add(new TitleBean("关联客户",false,accountData));
        data.add(new TitleBean("排序",false,sortData));
        SelectPop selectPop = findViewById(R.id.pop);
        selectPop.setData(data);
        selectPop.setOnSelectedListener(new SelectPop.OnSelectedListener() {
            @Override
            public void selected(int pos, int position, String id) {
                LogUtils.i(pos+">>>"+id);
            }
        });
    }

    public void setOutOrderList(String[] arr, List<ItemFilter> data) {
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                data.add(new ItemFilter(arr[i], String.valueOf(i)));
            } else if (i == 1) {
                data.add(new ItemFilter(arr[i], "2"));
            } else {
                data.add(new ItemFilter(arr[i], "1"));
            }
        }
    }
    public void setCustomOrderList(String[] arr, String [] ids,List<ItemFilter> data){
        for (int i = 0; i < arr.length; i++) {
            data.add(new ItemFilter(arr[i], ids[i]));
        }
    }
    public  ItemFilter getSubState(Context context, String name, String id, int subType, int resId){
        ItemFilter filter = new ItemFilter(name, id);
        List<ItemFilter> list = new ArrayList<>();
        String[] orderStateCancel = context.getResources().getStringArray(resId);
        setOrderListPlus(orderStateCancel,list);
        filter.setSubList(list);
        filter.setSubListType(subType);
        return  filter;
    }
    public void setOrderListPlus(String[] arr, List<ItemFilter> data) {
        for (int i = 0; i < arr.length; i++) {
            data.add(new ItemFilter(arr[i], String.valueOf(i + 1)));
        }
    }
}
