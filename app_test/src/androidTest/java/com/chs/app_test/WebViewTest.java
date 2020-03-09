package com.chs.app_test;

import androidx.test.espresso.web.model.Atoms;
import androidx.test.espresso.web.webdriver.DriverAtoms;
import androidx.test.espresso.web.webdriver.Locator;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.web.assertion.WebViewAssertions.webMatches;
import static androidx.test.espresso.web.sugar.Web.onWebView;
import static androidx.test.espresso.web.webdriver.DriverAtoms.clearElement;
import static androidx.test.espresso.web.webdriver.DriverAtoms.findElement;
import static androidx.test.espresso.web.webdriver.DriverAtoms.getText;
import static androidx.test.espresso.web.webdriver.DriverAtoms.webClick;
import static org.hamcrest.Matchers.containsString;

/**
 * author：chs
 * date：2020/3/8
 * des：
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class WebViewTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class);
    @Rule
    public ActivityTestRule<WebViewActivity> activityWebRule =
            new ActivityTestRule<WebViewActivity>(WebViewActivity.class,false,false){
                @Override
                protected void afterActivityLaunched() {
                    onWebView().forceJavascriptEnabled();
                }
            };

    @Test
    public void testWebViewHasDisplay(){
        onView(withId(R.id.btn_go_to_web)).perform(click());
        onWebView().check(webMatches(Atoms.getTitle(),containsString("Hello Espresso Web")));
    }

    @Test
    public void testJsInteraction(){
        //点击进入WebViewActivity
        onView(withId(R.id.btn_go_to_web)).perform(click());
        onWebView()
                //html界面上找到id为text_input的元素
                .withElement(findElement(Locator.ID, "text_input"))
                //清除之前的文字
                .perform(DriverAtoms.clearElement())
                //输入文字Lily
                .perform(DriverAtoms.webKeys("Lily"))
                //通过id找到切换文字的按钮
                .withElement(findElement(Locator.ID, "changeTextBtn"))
                //点击按钮
                .perform(webClick())
                //通过id找到显示文字的标签
                .withElement(findElement(Locator.ID, "message"))
                //判断标签上的文字是不是Lily
                .check(webMatches(getText(), containsString("Lily")));
    }

    @Test
    public void testJsFormSubmit(){
        //点击进入WebViewActivity
        onView(withId(R.id.btn_go_to_web)).perform(click());
        onWebView()
                // html界面上找到id为text_input的元素
                .withElement(findElement(Locator.ID, "text_input"))
                // 清除之前的文字
                .perform(clearElement())
                // 输入文字Lily
                .perform(DriverAtoms.webKeys("Lily"))
                // 通过id找到提交按钮
                .withElement(findElement(Locator.ID, "submitBtn"))
                // 点击按钮执行提交动作
                .perform(webClick())
                // 通过id找到form表单的id
                .withElement(findElement(Locator.ID, "response"))
                // 验证提交的内容是不是Lily
                .check(webMatches(getText(), containsString("Lily")));
    }

}
