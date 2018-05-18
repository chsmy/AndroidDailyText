package com.chs.androiddailytext.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.chs.androiddailytext.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private List<ListBean.DataEntity> mList = new ArrayList<>();
    private List<ListBean.DataEntity> mClicked = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ListView listView = findViewById(R.id.listview);
        final List<String> data = new ArrayList<>();
        String json = "{\"total_number\":\"44\",\"number\":44,\"pages\":\"1\",\"page\":\"1\",\"returncode\":\"0\",\"data\":[{\"id\":\"100\",\"place\":\"一号楼\",\"pid\":\"0\",\"ppath\":\"100\",\"level\":\"1\",\"lists\":[{\"id\":\"101\",\"place\":\"1单元\",\"pid\":\"100\",\"ppath\":\"100,101\",\"level\":\"2\",\"lists\":[{\"id\":\"105\",\"place\":\"地下通道\",\"pid\":\"101\",\"ppath\":\"100,101,105\",\"level\":\"3\",\"lists\":[]}]},{\"id\":\"114\",\"place\":\"101\",\"pid\":\"100\",\"ppath\":\"100,114\",\"level\":\"2\",\"lists\":[]},{\"id\":\"115\",\"place\":\"102\",\"pid\":\"100\",\"ppath\":\"100,115\",\"level\":\"2\",\"lists\":[]},{\"id\":\"116\",\"place\":\"201\",\"pid\":\"100\",\"ppath\":\"100,116\",\"level\":\"2\",\"lists\":[]},{\"id\":\"117\",\"place\":\"202\",\"pid\":\"100\",\"ppath\":\"100,117\",\"level\":\"2\",\"lists\":[]},{\"id\":\"132\",\"place\":\"夜夜夜夜\",\"pid\":\"100\",\"ppath\":\"100,132\",\"level\":\"2\",\"lists\":[]},{\"id\":\"133\",\"place\":\"有意义有意义\",\"pid\":\"100\",\"ppath\":\"100,133\",\"level\":\"2\",\"lists\":[{\"id\":\"138\",\"place\":\"哈哈\",\"pid\":\"133\",\"ppath\":\"100,133,138\",\"level\":\"3\",\"lists\":[]}]},{\"id\":\"135\",\"place\":\"101\",\"pid\":\"100\",\"ppath\":\"100,135\",\"level\":\"2\",\"lists\":[]},{\"id\":\"136\",\"place\":\"102\",\"pid\":\"100\",\"ppath\":\"100,136\",\"level\":\"2\",\"lists\":[]},{\"id\":\"141\",\"place\":\"大堂\",\"pid\":\"100\",\"ppath\":\"100,141\",\"level\":\"2\",\"lists\":[]},{\"id\":\"143\",\"place\":\"2单元\",\"pid\":\"100\",\"ppath\":\"100,143\",\"level\":\"2\",\"lists\":[]},{\"id\":\"144\",\"place\":\"3单元\",\"pid\":\"100\",\"ppath\":\"100,144\",\"level\":\"2\",\"lists\":[]}]},{\"id\":\"102\",\"place\":\"5号楼\",\"pid\":\"0\",\"ppath\":\"102\",\"level\":\"1\",\"lists\":[{\"id\":\"103\",\"place\":\"602\",\"pid\":\"102\",\"ppath\":\"102,103\",\"level\":\"2\",\"lists\":[]}]},{\"id\":\"104\",\"place\":\"F座\",\"pid\":\"0\",\"ppath\":\"104\",\"level\":\"1\",\"lists\":[]},{\"id\":\"106\",\"place\":\"16区\",\"pid\":\"0\",\"ppath\":\"106\",\"level\":\"1\",\"lists\":[{\"id\":\"107\",\"place\":\"大堂\",\"pid\":\"106\",\"ppath\":\"106,107\",\"level\":\"2\",\"lists\":[]},{\"id\":\"108\",\"place\":\"前台办公室\",\"pid\":\"106\",\"ppath\":\"106,108\",\"level\":\"2\",\"lists\":[]},{\"id\":\"109\",\"place\":\"员工通道\",\"pid\":\"106\",\"ppath\":\"106,109\",\"level\":\"2\",\"lists\":[]},{\"id\":\"110\",\"place\":\"东侧卫生间\",\"pid\":\"106\",\"ppath\":\"106,110\",\"level\":\"2\",\"lists\":[]}]},{\"id\":\"111\",\"place\":\"二号楼\",\"pid\":\"0\",\"ppath\":\"111\",\"level\":\"1\",\"lists\":[{\"id\":\"118\",\"place\":\"101\",\"pid\":\"111\",\"ppath\":\"111,118\",\"level\":\"2\",\"lists\":[]},{\"id\":\"119\",\"place\":\"102\",\"pid\":\"111\",\"ppath\":\"111,119\",\"level\":\"2\",\"lists\":[]},{\"id\":\"120\",\"place\":\"201\",\"pid\":\"111\",\"ppath\":\"111,120\",\"level\":\"2\",\"lists\":[]},{\"id\":\"121\",\"place\":\"202\",\"pid\":\"111\",\"ppath\":\"111,121\",\"level\":\"2\",\"lists\":[]},{\"id\":\"142\",\"place\":\"大堂\",\"pid\":\"111\",\"ppath\":\"111,142\",\"level\":\"2\",\"lists\":[]},{\"id\":\"145\",\"place\":\"2单元\",\"pid\":\"111\",\"ppath\":\"111,145\",\"level\":\"2\",\"lists\":[]},{\"id\":\"146\",\"place\":\"3单元\",\"pid\":\"111\",\"ppath\":\"111,146\",\"level\":\"2\",\"lists\":[]}]},{\"id\":\"112\",\"place\":\"三号楼\",\"pid\":\"0\",\"ppath\":\"112\",\"level\":\"1\",\"lists\":[{\"id\":\"122\",\"place\":\"101\",\"pid\":\"112\",\"ppath\":\"112,122\",\"level\":\"2\",\"lists\":[]},{\"id\":\"123\",\"place\":\"102\",\"pid\":\"112\",\"ppath\":\"112,123\",\"level\":\"2\",\"lists\":[]},{\"id\":\"124\",\"place\":\"201\",\"pid\":\"112\",\"ppath\":\"112,124\",\"level\":\"2\",\"lists\":[]},{\"id\":\"125\",\"place\":\"202\",\"pid\":\"112\",\"ppath\":\"112,125\",\"level\":\"2\",\"lists\":[]}]},{\"id\":\"113\",\"place\":\"四号楼\",\"pid\":\"0\",\"ppath\":\"113\",\"level\":\"1\",\"lists\":[{\"id\":\"126\",\"place\":\"101\",\"pid\":\"113\",\"ppath\":\"113,126\",\"level\":\"2\",\"lists\":[]},{\"id\":\"127\",\"place\":\"102\",\"pid\":\"113\",\"ppath\":\"113,127\",\"level\":\"2\",\"lists\":[]},{\"id\":\"128\",\"place\":\"201\",\"pid\":\"113\",\"ppath\":\"113,128\",\"level\":\"2\",\"lists\":[]},{\"id\":\"129\",\"place\":\"202\",\"pid\":\"113\",\"ppath\":\"113,129\",\"level\":\"2\",\"lists\":[]}]},{\"id\":\"139\",\"place\":\"15区\",\"pid\":\"0\",\"ppath\":\"139\",\"level\":\"1\",\"lists\":[{\"id\":\"140\",\"place\":\"二层\",\"pid\":\"139\",\"ppath\":\"139,140\",\"level\":\"2\",\"lists\":[]}]},{\"id\":\"147\",\"place\":\"55\",\"pid\":\"0\",\"ppath\":\"147\",\"level\":\"1\",\"lists\":[]}]}";

        final Gson gson = new Gson();
        ListBean listBean = gson.fromJson(json,ListBean.class);


//        for(int i=0;i<20000;i++){
//            data.add("item---------"+i);
//        }
        mList.addAll(listBean.getData());
        //为第一季都设置可见
        for (ListBean.DataEntity bean : mList){
            bean.setVisible(true);
        }
        final ListAdapter adapter = new ListAdapter(this,mList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListBean.DataEntity entityClicked = mList.get(position);

                entityClicked.setClicked(!entityClicked.isClicked());

                List<ListBean.DataEntity> current = new ArrayList<>();
                //把点击过的都放入到一个list中存放
                if(entityClicked.isClicked()){
                  mClicked.add(entityClicked);
                }
                maopao(entityClicked);

                for (ListBean.DataEntity entity:mList){

                    if(!entity.isClicked()&&entity.isExpend()){
                        setNoClicked(entity);
                        entity.setExpend(false);
                    }
                    //只有可见的才放进去
                    if(entity.isVisible()){
                        current.add(entity);
                    }
                    //ietm 被点击 它有子集 并且不是展开状态 才把子集放进去
                    if(entity.isClicked()&&entity.getLists()!=null&&entity.getLists().size()>0&& !entity.isExpend()){
                        entity.setExpend(!entity.isExpend());
                        for(ListBean.DataEntity bean:entity.getLists()){
                            bean.setVisible(true);
                        }
                        current.addAll(entity.getLists());
                    }
                }

                mList.clear();
                mList.addAll(current);
                adapter.notifyDataSetChanged();
                Log.i("Str-----",adapter.getStr());
            }
        });
    }

    //如果该条isclick为false，那么它一定不是展开状态，它的下级也一定不是展开状态
    private void setNoClicked(ListBean.DataEntity entity){
        for(ListBean.DataEntity bean:entity.getLists()){
            bean.setVisible(false);
            bean.setClicked(false);
            bean.setExpend(false);
            if(bean.getLists()!=null&&bean.getLists().size()>0){
                  setNoClicked(bean);
            }
        }
    }
   //冒泡找出需要变蓝的item
    private void maopao(ListBean.DataEntity entity){
        for (ListBean.DataEntity e:mClicked){
            if(entity.getLevel()>e.getLevel()&&entity.getPid().equals(e.getId())){
                e.setSelected(true);
                maopao(e);
            }else {
                e.setSelected(false);
            }
        }
        entity.setSelected(true);
    }
}
