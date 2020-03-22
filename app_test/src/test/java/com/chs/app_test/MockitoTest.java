package com.chs.app_test;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * author：chs
 * date：2020/3/11
 * des：
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {

    //结果验证，测试某些结果是否正确
    @Test
    public void testMockitoResult() {
        Person person = mock(Person.class);
        //当调用person.getAge()方法的时候，给它返回一个18
        when(person.getAge()).thenReturn(18);
        //当调用person.getName()方法的时候，给它返回一个Lily
        when(person.getName()).thenReturn("Lily");
        //判断返回跟预期是否一样
        assertEquals(18, person.getAge());
        assertEquals("Lily", person.getName());
    }

    //验证行为，有时候会测试某些行为是否被执行过
    @Test
    public void testMockitoBehavior() {
        Person person = mock(Person.class);
        int age = person.getAge();
        //验证getAge动作有没有发生
        verify(person).getAge();
        //验证person.getName()是不是没有调用
        verify(person, never()).getName();
        //验证是否最少调用过一次person.getAge
        verify(person, atLeast(1)).getAge();
        //验证getAge动作是否被调用了2次，前面只用了一次所以这里会报错
        verify(person, times(2)).getAge();
    }

    @Test
    public void testNotNull(){
        Person person = mock(Person.class);
        System.out.println(person.getName());
        Person person1 = mock(Person.class,RETURNS_SMART_NULLS);
        System.out.println(person1.getName());
    }

    @Mock
    List<Integer> mList;
    @Test
    public void testAnnotationMock(){
        mList.add(0);
        verify(mList).add(0);
    }

    //测试参数
    @Test
    public void testParameter(){
        Person person = mock(Person.class);
        when(person.getDuty(1)).thenReturn("医生");
        System.out.println(person.getDuty(1));
        //anyInt任何Int值，此外还有anyString，anyFloat等
        when(person.getDuty(anyInt())).thenReturn("护士");
        System.out.println(person.getDuty(anyInt()));
        //验证person.getDuty(1)方法有没有调用
        verify(person).getDuty(ArgumentMatchers.eq(1));
    }

    //mock出来的对象都是虚拟的对象，我们可以验证其执行次数，状态等
    //如果一个对象是真实的，那怎么验证呢 可以使用spy包装一下
    @Test
    public void testSpy(){
        Person person = getPerson();
        Person spy = spy(person);
        when(spy.getName()).thenReturn("Lily");
        System.out.println(spy.getName());
        verify(spy).getName();
    }

    private Person getPerson(){
        return new Person();
    }
}