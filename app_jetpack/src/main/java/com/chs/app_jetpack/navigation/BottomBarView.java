package com.chs.app_jetpack.navigation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.SizeUtils;
import com.chs.app_jetpack.R;
import com.chs.app_jetpack.model.BottomBar;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

import java.util.List;

/**
 * author：chs
 * date：2020/3/27
 * des： 底部导航view
 */
public class BottomBarView extends BottomNavigationView {

    private static int[] sIcons = new int[]{R.drawable.icon_tab_home
    ,R.drawable.icon_tab_apply,R.drawable.icon_tab_find,R.drawable.icon_tab_mine};

    public BottomBarView(@NonNull Context context) {
        this(context,null);
    }

    public BottomBarView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    @SuppressLint("RestrictedApi")
    public BottomBarView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        BottomBar bottomBar = AppConfig.getsBottomBar();

        int[][] states = new int[2][];
        states[0] = new int[]{android.R.attr.state_selected};
        states[1] = new int[]{};
        int[] colors = new int[]{Color.parseColor(bottomBar.activeColor),Color.parseColor(bottomBar.inActiveColor)};
        ColorStateList colorStateList = new ColorStateList(states,colors);
        setItemTextColor(colorStateList);
        setItemIconTintList(colorStateList);
        //设置文本和图标都一直显示
        setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_SELECTED);

        List<BottomBar.Tabs> tabs = bottomBar.tabs;
        //循环添加item
        for (BottomBar.Tabs tab : tabs) {
            //是否显示该tab
            if(!tab.enable){
                continue;
            }
            int itemId = getItemId(tab.pageUrl);
            if(itemId<0){
                continue;
            }
            Menu menu = getMenu();
            MenuItem menuItem = menu.add(0, itemId, tab.index, tab.title);
            menuItem.setIcon(sIcons[tab.index]);
        }
        //添加完所有的itemMenu之后才改变大小，因为每次添加都会先移除所有的item，排序之后再放入到容器中
        //循环改变每个item的大小
        int index = 0;
        for (BottomBar.Tabs tab : tabs) {
            //是否显示该tab
            if(!tab.enable){
                continue;
            }
            int itemId = getItemId(tab.pageUrl);
            if(itemId<0){
                continue;
            }
            int size = SizeUtils.dp2px(tab.size);
            BottomNavigationMenuView  menuView = (BottomNavigationMenuView) getChildAt(0);
            BottomNavigationItemView itemView = (BottomNavigationItemView) menuView.getChildAt(index);
            itemView.setIconSize(size);
            if(TextUtils.isEmpty(tab.title)){
                //title为空的一般是中间的按钮 有那种中间变大的按钮
                int tintColor = TextUtils.isEmpty(tab.tintColor)?Color.parseColor("#ff678f"):
                        Color.parseColor(tab.tintColor);
                itemView.setIconTintList(ColorStateList.valueOf(tintColor));
                //禁止上下浮动的效果
                itemView.setShifting(false);
            }
            index++;
        }
        //底部导航栏默认选中项
        if(bottomBar.selectTab!=0){
            BottomBar.Tabs selectTab = tabs.get(bottomBar.selectTab);
            if(selectTab.enable){
                int itemId = getItemId(selectTab.pageUrl);
                //延迟一下在切换，等待NavGraphBuilder解析完成
                post(()->setSelectedItemId(itemId));
            }
        }
    }

    private int getItemId(String pageUrl) {
        Destination destination = AppConfig.getDestinationMap().get(pageUrl);
        if(destination!=null){
            return destination.id;
        }
        return -1;
    }
}
