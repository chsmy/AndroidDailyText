package com.chs.app_test;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

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
public class HelloWorldTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void listGoesOverTheFold() {
        onView(withText("Hello World!")).check(matches(isDisplayed()));
    }

    @Test
    public void clickTest(){
        onView(withId(R.id.btn_click))
                .perform(click())
                .check(matches(isDisplayed()));
    }
    @Test
    public void matchText(){
        //点击按钮，将Hello World!改为Hello Espresso!
        onView(withId(R.id.btn_click)).perform(click());
        //检查TextView上的文字是不是Hello Espresso!
        onView(withId(R.id.tv_text)).check(matches(withText("Hello Espresso!")));
    }

}
