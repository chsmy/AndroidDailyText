package com.chs.androiddailytext.jetpack;

import android.os.Bundle;
import android.view.View;

import com.chs.androiddailytext.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author chs
 * date：2019-04-19 11:32
 * des：
 */
public class RoomActivity extends AppCompatActivity {
    List<User> mUsers = new ArrayList<>();
    UserRepository mRepository;
    MyAdapter mAdapter;
    int index = 0;
    private UserViewModel mViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
//        mRepository = new UserRepository(getApplication());
//        mUsers = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.recycleview);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        mAdapter = new MyAdapter(mUsers,this);
        recyclerView.setAdapter(mAdapter);

        mViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        mViewModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                mUsers.clear();
                mUsers.addAll(users);
                mAdapter.notifyDataSetChanged();
            }
        });

    }



    public void insert(View view) {
        User user = new User();
        user.id =index;
        user.name = "张三"+ Math.random()*100;
        user.school = "北大"+ Math.random()*100;
        user.password = "123"+ Math.random()*100;
//        mRepository.insert(user);
        mViewModel.insertUser(user);
        index++;
    }

    public void query(View view) {
//        List<User> allUser = mRepository.getAllUser();
//        mUsers.addAll(allUser);
//        mAdapter.notifyDataSetChanged();
    }

    public void deleteAll(View view) {
        mViewModel.deleteAll();
    }

    public void update(View view) {
        User user = new User();
        user.id =0;
        user.name = "张三"+ Math.random()*100;
        user.school = "北大"+ Math.random()*100;
        user.password = "123"+ Math.random()*100;
        mViewModel.update(user);
    }
}
