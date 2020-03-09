package com.chs.app_test;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import androidx.test.espresso.intent.matcher.ComponentNameMatchers;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Description;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;

/**
 * author：chs
 * date：2020/3/5
 * des：
 */
@RunWith(AndroidJUnit4.class)
public class IntentTest {

    @Rule
    public IntentsTestRule<MainActivity> intentsTestRule =
            new IntentsTestRule<>(MainActivity.class);

     @Test
    public void testPickIntent(){
         //测试去联系人页面获取一个电话号码
         //创建一个Intent用来封装返回的数据
         Intent intent = new Intent();
         intent.putExtra(ContactsActivity.KEY_PHONE_NUMBER,"123456789");
         //验证如果有相应的startActivityOnResult发生，就返回前面自己创建的结果。
         intending(IntentMatchers.hasComponent(ComponentNameMatchers.hasShortClassName(".ContactsActivity")))
             .respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK,intent));
         //点击去联系人页面的按钮
         onView(withId(R.id.btn_to_contacts)).perform(click());
         //点击打电话的按钮
         onView(withId(R.id.btn_call)).perform(click());
         //验证打电话的Intent是否发送
         intended(allOf(
                 hasAction(Intent.ACTION_CALL),
                 hasData("tel:123456789")
         ));
    }

    @Test
    public void testTackPhoto() throws InterruptedException {
         //自定义一个拍照返回drawable图片的Intent
         Intent picIntent = new Intent();
         //把drawable放到bundle中传递
        Bundle bundle = new Bundle();
        Bitmap bitmap = BitmapFactory.decodeResource(intentsTestRule.getActivity().getResources(), R.mipmap.ic_launcher);
        bundle.putParcelable("data", bitmap);
        picIntent.putExtras(bundle);
        Instrumentation.ActivityResult result =
                new Instrumentation.ActivityResult(Activity.RESULT_OK,picIntent);
        //判断是否有包含ACTION_IMAGE_CAPTURE的Intent出现，出现就给它返回result
        intending(hasAction(MediaStore.ACTION_IMAGE_CAPTURE)).respondWith(result);

        //判断ImageView上没有显示drawable
        onView(withId(R.id.iv_take_photo)).check(matches(not(hasDrawable())));
        //点击拍照按钮去拍照界面
        onView(withId(R.id.btn_take_photo)).perform(click());
        //判断ImageView上显示了一个drawable
        onView(withId(R.id.iv_take_photo)).check(matches(hasDrawable()));
    }

    private BoundedMatcher<View, ImageView> hasDrawable(){
         return new BoundedMatcher<View, ImageView>(ImageView.class) {
             @Override
             protected boolean matchesSafely(ImageView item) {
                 return item.getDrawable()!=null;
             }

             @Override
             public void describeTo(Description description) {
                 description.appendText("是否有drawable");
             }
         };
    }

}
