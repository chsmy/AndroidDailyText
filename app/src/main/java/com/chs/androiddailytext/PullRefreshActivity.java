package com.chs.androiddailytext;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.TextView;

import com.chs.androiddailytext.widget.CustomCircle;

public class PullRefreshActivity extends AppCompatActivity implements CustomCircle.RefreshCallBack{
    private RecyclerView mRecyclerView;
    private CustomCircle reFreshView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_refresh);
        mRecyclerView= findViewById(R.id.recycler);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(new HomeAdapter());
        reFreshView = (CustomCircle) findViewById(R.id.refreshView);
        reFreshView.setRefreshCallBack(this);
    }

    @Override
    public void upRefresh(Scroller scroller, int y) {
        scroller.startScroll(0, y, 0, -100);
        reFreshView.invalidate();
    }

    @Override
    public void downLoad(Scroller scroller, int y) {
        scroller.startScroll(0, y, 0, 100);
        reFreshView.invalidate();
    }


    class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(PullRefreshActivity.this).inflate(R.layout.item_bear,parent,false);
            return new HomeViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((HomeViewHolder) holder).tv_name.setText("熊熊"+position);
            ((HomeViewHolder) holder).iv_picture.setBackgroundResource(R.drawable.timg2);
        }

        @Override
        public int getItemCount() {
            return 10;
        }

        class HomeViewHolder extends RecyclerView.ViewHolder {

            public TextView tv_name = null;
            public ImageView iv_picture = null;
            public HomeViewHolder(View itemView) {
                super(itemView);
                tv_name = (TextView) itemView.findViewById(R.id.tv_name);
                iv_picture = (ImageView) itemView.findViewById(R.id.iv_picture);
            }
        }
    }
}
