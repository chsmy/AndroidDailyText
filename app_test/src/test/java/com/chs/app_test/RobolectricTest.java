package com.chs.app_test;

import android.app.Application;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowAlertDialog;
import org.robolectric.shadows.ShadowCompoundButton;
import org.robolectric.shadows.ShadowTextView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

/**
 * author：chs
 * date：2020/3/21
 * des：
 */
@RunWith(RobolectricTestRunner.class)
public class RobolectricTest {

    private Application context;

    @Before
    public void setUp() throws Exception {
        context = ApplicationProvider.getApplicationContext();
    }

    //点击button，改变TextView上的文字，判断改变之后的文字是不是预期的
    @Test
    public void clickingButtonShouldChangeMessage() {
        //默认会调用Activity的onCreate()、onStart()、onResume()
//        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
//        TextView textView = activity.findViewById(R.id.tv_text);
//        Button button = activity.findViewById(R.id.btn_click);
//        button.performClick();
//        assertThat(textView.getText().toString(), equalTo("Hello Espresso!"));

        //Robolectric.setupActivity显示过时了，使用ActivityScenario来代替
        //ActivityScenario提供api来启动和驱动Activity的生命周期状态以进行测试,
        // 适用于任意Activity，并能在不同版本的Android上一致工作
        //通过scenario.moveToState来控制生命周期比如  scenario.moveToState(Lifecycle.State.CREATED)
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);
        scenario.onActivity(activity -> {
            TextView textView = activity.findViewById(R.id.tv_text);
            Button button = activity.findViewById(R.id.btn_click);
            button.performClick();
            assertThat(textView.getText().toString(), equalTo("Hello Espresso!"));
        });

    }

    //点击按钮从MainActivity到UnitTestActivity,Robolectric是运行在JVM上的测试框架，并不会真正的启动UnitTestActivity
    //但是可以检查MainActivity是不是触发了真正的意图
    @Test
    public void testClickButtonToPicking() {

        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);
        scenario.onActivity(activity -> {
            Button button = activity.findViewById(R.id.btn_go_to_unit);
            button.performClick();

            Intent expectedIntent = new Intent(activity, UnitTestActivity.class);
            Intent actual = shadowOf(context)
                    .getNextStartedActivity();
            assertEquals(expectedIntent.getComponent(),actual.getComponent());
        });

    }

    //Shadow是Robolectric的核心，Robolectric中内置了很多Android SDK中的类的影子，
    // 比如ShadowCompoundButton,ShadowTextView,ShadowActivity .....
    //当一个android.jar中的某个类被调用的时候，Robolectric会尝试寻找该类的影子，调用影子中的方法
    //通过shadowOf可以很方便的拿到对应类的影子类
    public void testAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("title").setMessage("message");
        builder.setCancelable(true);
        AlertDialog alert = builder.create();
        alert.show();

        assertTrue(alert.isShowing());

        ShadowAlertDialog shadowAlertDialog = (ShadowAlertDialog) shadowOf(alert);
        assertEquals("title", shadowAlertDialog.getTitle());
        assertEquals("message", shadowAlertDialog.getMessage());
        assertTrue(shadowAlertDialog.isCancelable());
    }
}
