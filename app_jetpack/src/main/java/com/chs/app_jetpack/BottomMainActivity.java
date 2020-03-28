package com.chs.app_jetpack;

import android.os.Bundle;
import android.view.MenuItem;

import com.chs.app_jetpack.navigation.BottomBarView;
import com.chs.app_jetpack.navigation.NavGraphBuilder;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class BottomMainActivity extends AppCompatActivity {
    NavController navController;
    BottomBarView navView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_main);
        navView = findViewById(R.id.nav_view);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavGraphBuilder.build(navController,this,R.id.nav_host_fragment);
        navView.setNavController(navController);
    }
}
