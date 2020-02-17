package com.chs.androiddailytext.jetpack.botton_navigation;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.chs.androiddailytext.R;
import com.chs.androiddailytext.base.BaseActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

/**
 * author：chs
 * date：2020/2/14
 * des：
 */
public class BottomNavActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        //导航控制器
        NavController controller = Navigation.findNavController(this,R.id.bottom_fragment);
        //底部导航的配置
        AppBarConfiguration configuration = new AppBarConfiguration.Builder(bottomNavigationView.getMenu()).build();
        //关联控制器和底部导航的配置
        NavigationUI.setupActionBarWithNavController(this,controller,configuration);
        //关联底部bottomNavigationView和控制器
        NavigationUI.setupWithNavController(bottomNavigationView,controller);
    }
}
