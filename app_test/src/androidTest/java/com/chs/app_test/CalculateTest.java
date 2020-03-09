package com.chs.app_test;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.KeyEvent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SdkSuppress;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.Until;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * author：chs
 * date：2020/3/2
 * des： uiAutomator
 */
//执行单元测试的执行类
@RunWith(AndroidJUnit4.class)
//4.3以上系统可以使用
@SdkSuppress(minSdkVersion = 18)
public class CalculateTest {

     private UiDevice mUiDevice;

     //测试执行之前的操作
    @Before
    public void startMainActivityFromHomeScreen() {
        mUiDevice = UiDevice.getInstance(getInstrumentation());
        //点击home键
        mUiDevice.pressHome();
        // 获取启动页的包名，并判断是否为空
        final String launcherPackage = getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        //等待启动完成
        mUiDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), 3000);

        //启动计算器程序
        Context context = getApplicationContext();
        //通过包名创建启动的intent
        Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage("com.android.calculator2");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        //等待启动完成
        mUiDevice.wait(Until.hasObject(By.pkg("com.android.calculator2").depth(0)),3000);
    }

    @Test
    public void calculate() throws InterruptedException, UiObjectNotFoundException {
        //点击1
        Thread.sleep(1000);
        mUiDevice.pressKeyCode(KeyEvent.KEYCODE_1);
        Thread.sleep(1000);
        //点击加号
        mUiDevice.findObject(By.res("com.android.calculator2:id/op_add")).click();
        Thread.sleep(1000);
        //点击9
        mUiDevice.findObject(By.text("9")).click();
        Thread.sleep(1000);
        //点击等号
        mUiDevice.findObject(By.desc("等于")).click();
        Thread.sleep(1000);
        //断言验证结果是否正确
        //模拟器
        UiObject2 result = mUiDevice.findObject(By.res("com.android.calculator2:id/result"));
        //华为mate20
//        UiObject2 result = mUiDevice.findObject(By.res("com.android.calculator2:id/formula"));
        //使用UiSelector的方式查找
//        UiObject result = mUiDevice.findObject(new UiSelector().resourceId("com.android.calculator2:id/result"));
        Assert.assertEquals("10",result.getText());
    }

    @After
    public void clearNum() {
        //测试完成之后，点击clear键清除界面上是数字
        //模拟器
        mUiDevice.findObject(By.res("com.android.calculator2:id/clr")).click();
        //华为mate20
//        mUiDevice.findObject(By.res("com.android.calculator2:id/op_clr")).click();
    }

    /**
     * 获取程序的启动包名
     * @return
     */
    private String getLauncherPackageName() {
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        // 通过PackageManager获取启动的包名
        PackageManager pm = getApplicationContext().getPackageManager();
        ResolveInfo resolveInfo = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return resolveInfo.activityInfo.packageName;
    }
}
