package com.chs.app_jetpack.navigation;

import android.content.ComponentName;
import android.content.Context;

import androidx.fragment.app.FragmentActivity;
import androidx.navigation.ActivityNavigator;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavGraphNavigator;
import androidx.navigation.NavigatorProvider;
import androidx.navigation.fragment.FragmentNavigator;

import com.blankj.utilcode.util.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * author：chs
 * date：2020/3/27
 * des： 构建一个导航图 并设置给NavController
 */
public class NavGraphBuilder {

    public static void build(NavController controller, FragmentActivity activity,int containerId){
        NavigatorProvider navigatorProvider = controller.getNavigatorProvider();

//        FragmentNavigator fragmentNavigator = navigatorProvider.getNavigator(FragmentNavigator.class);
        FixFragmentNavigator fixFragmentNavigator = new FixFragmentNavigator(activity,
                activity.getSupportFragmentManager(),containerId);
        navigatorProvider.addNavigator(fixFragmentNavigator);

        ActivityNavigator activityNavigator = navigatorProvider.getNavigator(ActivityNavigator.class);
        HashMap<String, Destination> destinationMap = AppConfig.getDestinationMap();

        //NavGraphNavigator只为默认的启动页提供导航服务，具体的导航还是需要对应的导航器fragmentNavigator，ActivityNavigator
        NavGraph navGraph = new NavGraph(new NavGraphNavigator(navigatorProvider));

        for (Map.Entry<String, Destination> desEntry : destinationMap.entrySet()) {
            Destination node = desEntry.getValue();
            if(node.isFragment){
                //创建目标
                FragmentNavigator.Destination destination = fixFragmentNavigator.createDestination();
                destination.setId(node.id);
                destination.setClassName(node.className);
                destination.addDeepLink(node.pageUrl);
                //放入图中
                navGraph.addDestination(destination);
            }else {
                ActivityNavigator.Destination destination = activityNavigator.createDestination();
                destination.setId(node.id);
                destination.setComponentName(new ComponentName(Utils.getApp().getPackageName(),node.className));
                destination.addDeepLink(node.pageUrl);
                navGraph.addDestination(destination);
            }
            //设置第一个启动页
            if(node.asStarter){
                navGraph.setStartDestination(node.id);
            }
        }
        controller.setGraph(navGraph);
    }

}
