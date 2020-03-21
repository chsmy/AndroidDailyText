package com.chs.app_test;

import android.text.TextUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import static org.junit.Assert.*;
/**
 * author：chs
 * date：2020/3/13
 * des：
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Utils.class)
public class PowerMockTest {

    @Test
    public void testStatic(){
        PowerMockito.mockStatic(Utils.class);
        PowerMockito.when(Utils.isEmpty("abc")).thenReturn(false);
        assertFalse(Utils.isEmpty("abc"));
    }

    @Test
    public void testPrivate() throws Exception {
        Utils util = new Utils();
        //调用私有方法
        String res = Whitebox.invokeMethod(util, "changeName", "Lily");
        assertEquals("ABCLily",res);
        //替换私有变量  也可以使用MemberModifier来修改
        Whitebox.setInternalState(util,"name","Lily");
        assertEquals("Lily",util.getName());
    }

    @Test
    public void testNew() throws Exception {
        Person person = PowerMockito.mock(Person.class);
        Utils util = new Utils();
        //当new一个Person对象并传入Lily的时候，返回person
        PowerMockito.whenNew(Person.class).withArguments("Lily").thenReturn(person);
        PowerMockito.when(util.getPersonName()).thenReturn("Diavd");
        assertEquals("Diavd",util.getPersonName());
    }

}
