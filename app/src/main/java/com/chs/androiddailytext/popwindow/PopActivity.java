package com.chs.androiddailytext.popwindow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.chs.androiddailytext.R;

import java.util.ArrayList;
import java.util.List;

public class PopActivity extends AppCompatActivity implements View.OnClickListener{
    List<PopListBean> mlist = new ArrayList<>();
    private MyPopWindow mPopWindow;
    private LinearLayout ll_room;
    private LinearLayout ll_type;
    private LinearLayout ll_state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);
        ll_room = findViewById(R.id.ll_room);
        ll_type = findViewById(R.id.ll_type);
        ll_state = findViewById(R.id.ll_state);

        ll_room .setOnClickListener(this);
        ll_type .setOnClickListener(this);
        ll_state .setOnClickListener(this);

        mlist.add(new PopListBean(1,"1号库房",false));
        mlist.add(new PopListBean(1,"2号库房",false));
        mlist.add(new PopListBean(1,"3号库房",false));
        mlist.add(new PopListBean(1,"4号库房",false));
        mlist.add(new PopListBean(1,"5号库房",false));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_room:
                mPopWindow = new MyPopWindow(this,mlist);
                mPopWindow.show(ll_room,0,0);
                break;
            case R.id.ll_type:
                break;
            case R.id.ll_state:
                break;
        }
    }
}
