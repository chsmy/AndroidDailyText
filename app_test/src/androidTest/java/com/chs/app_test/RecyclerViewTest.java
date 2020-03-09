package com.chs.app_test;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * author：chs
 * date：2020/3/4
 * des：
 */
@RunWith(AndroidJUnit4.class)
public class RecyclerViewTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testRecyclerView(){
        //点击进入列表页
        onView(ViewMatchers.withId(R.id.btn_to_recyclerview)).perform(click());
        //定位到第30条并点击
        onView(ViewMatchers.withId(R.id.recyclerview))
                .perform(RecyclerViewActions.actionOnItemAtPosition(30, click()));
        //判断第30条的文字"item++++++++++++++30"是否显示了
        onView(ViewMatchers.withText("item++++++++++++++30")).check(matches(isDisplayed()));
    }

    @Test
    public void matchItemData(){
        //点击进入列表页
        onView(ViewMatchers.withId(R.id.btn_to_recyclerview)).perform(click());
        //根据匹配器滑动到相应的位置
        onView(withId(R.id.recyclerview)).perform(RecyclerViewActions.scrollToHolder(withAdaptedData()));
        //判断期望的文本有没有显示
        onView(withText("item++++++++++++++30")).check(matches(isDisplayed()));
    }

    public static Matcher<MyAdapter.ViewHolder> withAdaptedData(){
         return new TypeSafeMatcher<MyAdapter.ViewHolder>(){

             @Override
             public void describeTo(Description description) {
                 description.appendText("测试item");
             }

             @Override
             protected boolean matchesSafely(MyAdapter.ViewHolder item) {
                 return item.mTextView.getText().equals("item++++++++++++++30");
             }
         };
    }
}
