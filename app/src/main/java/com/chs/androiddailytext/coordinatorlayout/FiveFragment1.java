package com.chs.androiddailytext.coordinatorlayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.chs.androiddailytext.R;
import com.chs.androiddailytext.coordinatorlayout.recycleview.BaseRecycleAdapter;

import java.util.ArrayList;
import java.util.List;


public class FiveFragment1 extends Fragment {
//    private RecyclerView mRecyclerView;
    private List<String> mList = new ArrayList<>();
    private BaseRecycleAdapter mAdapter;
    public static FiveFragment1 newInstance(String title) {
        FiveFragment1 fragment = new FiveFragment1();
        Bundle args = new Bundle();
        args.putString("title",title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second_five, container, false);
        initData();
//        mRecyclerView =  view.findViewById(R.id.recycleview);
//        LinearLayoutManager layoutManager =  new LinearLayoutManager(getContext());
//        mRecyclerView.setLayoutManager(layoutManager);
//        mAdapter = new BaseRecycleAdapter<String>(getContext(),R.layout.item,mList,mRecyclerView) {
//            @Override
//            public void convert(ViewHolder holder, String str) {
//                holder.setText(R.id.tv_item,str);
//            }
//        };
//        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    private void initData() {
        for(int i = 0;i<20;i++){
            mList.add("item"+i);
        }
    }

}
