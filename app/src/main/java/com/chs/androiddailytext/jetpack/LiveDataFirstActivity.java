package com.chs.androiddailytext.jetpack;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.chs.androiddailytext.R;
import com.chs.androiddailytext.jetpack.botton_navigation.BottomNavActivity;
import com.chs.androiddailytext.jetpack.navigation.NavigationActivity;
import com.chs.androiddailytext.jetpack.paging.PagingActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.navigation.ActivityNavigator;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavGraphNavigator;
import androidx.navigation.NavigatorProvider;

/**
 * @author chs
 * date：2019-04-26 14:53
 * des：
 */
public class LiveDataFirstActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data_bus);
        LiveDataBus1.get().with("text", String.class).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                ToastUtils.showShort(s);
            }
        });
        LiveDataBus.<String>get("test").observe(this,res->{
            ToastUtils.showShort(res);
        });
    }

    public void sendMessage(View view) {
//        LiveDataBus1.get().with("text").setValue("哈哈哈");
        LiveDataBus.<String>get("sticky").postStickyData("firstActivity过来的");
    }

    public void Jump(View view) {
        Intent intent = new Intent(this, LiveDataSecondActivity.class);
        startActivity(intent);
    }

    public void Paging(View view) {
        Intent intent = new Intent(this, PagingActivity.class);
        startActivity(intent);
    }

    public void Navigation(View view) {
        Intent intent = new Intent(this, NavigationActivity.class);
        startActivity(intent);
    }

    public void BottomNavigation(View view) {
        Intent intent = new Intent(this, BottomNavActivity.class);
        startActivity(intent);
    }

    public void NavToActivity(View view) {
//        Intent intent = new Intent();
//        intent.setData(Uri.parse("http://www.chs.com/Divad"));
//        NavController navController = new NavController(this);
//        NavigatorProvider navigatorProvider = navController.getNavigatorProvider();
////        FragmentNavigator navigator = navigatorProvider.getNavigator(FragmentNavigator.class);
//        FragmentNavigator navigator = new FragmentNavigator(this, getSupportFragmentManager(),
//                getContainerId());
//        FragmentNavigator.Destination destination = navigator.createDestination();
//        NavGraph navGraph = new NavGraph(new NavGraphNavigator(navigatorProvider));
//        navGraph.addDestination(destination);
//        navController.setGraph(navGraph);
//        navController.handleDeepLink(intent);

        NavController navController = new NavController(this);
        NavigatorProvider navigatorProvider = navController.getNavigatorProvider();
        NavGraph navGraph = new NavGraph(new NavGraphNavigator(navigatorProvider));
        ActivityNavigator navigator = navigatorProvider.getNavigator(ActivityNavigator.class);
        ActivityNavigator.Destination destination = navigator.createDestination();
        destination.setId(R.id.bottom_nav_activity);
        destination.setComponentName(new ComponentName(getApplication().getPackageName(),
                "com.chs.androiddailytext.jetpack.botton_navigation.BottomNavActivity"));
        navGraph.addDestination(destination);
        navGraph.setStartDestination(destination.getId());
        navController.setGraph(navGraph);
    }
}
