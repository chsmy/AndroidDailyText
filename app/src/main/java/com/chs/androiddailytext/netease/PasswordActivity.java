package com.chs.androiddailytext.netease;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.chs.androiddailytext.R;
import com.chs.androiddailytext.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.core.content.ContextCompat;

/**
 * @author：chs date：2019/5/1
 * des：
 */
public class PasswordActivity extends BaseActivity {
    GridView mGridView;
    PsdEditText mPsdEditText;
    @Override
    public int getContentView(Bundle savedInstanceState) {
        return R.layout.activity_password;
    }

    @Override
    public void initView() {
        mGridView = findViewById(R.id.gridview);
        mPsdEditText = findViewById(R.id.psd_input);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        //初始化数据
        List<Integer> listNumber = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            listNumber.add(i);
        }
        listNumber.add(10);
        listNumber.add(0);
        listNumber.add(R.mipmap.delete);

        PsdAdapter adapter = new PsdAdapter(listNumber);

        mGridView.setAdapter(adapter);
    }

    class  PsdAdapter extends BaseAdapter{

        private List<Integer> mDatas;

        public PsdAdapter(List<Integer> datas) {
            mDatas = datas;
        }

        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return mDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(getApplicationContext(), R.layout.item_password_key, null);
                holder = new ViewHolder();
                holder.btnNumber = (TextView) convertView.findViewById(R.id.btNumber);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.btnNumber.setText(String.valueOf(mDatas.get(position)));
            if (position == 9) {
                holder.btnNumber.setText("");
                holder.btnNumber.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.gray_click));
            }
            if (position == 11) {
                holder.btnNumber.setText("");
                holder.btnNumber.setBackgroundResource(mDatas.get(position));
            }
            holder.btnNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(position<11&&position!=9){
                        mPsdEditText.addPassword(String.valueOf(mDatas.get(position)));
                    }else {
                        mPsdEditText.deleteLastPassword();
                    }
                }
            });
            return  convertView;
        }

    }
    static class ViewHolder {
        public TextView btnNumber;
    }
}
