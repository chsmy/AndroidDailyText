package com.chs.app_test;

import androidx.annotation.Nullable;

/**
 * author：chs
 * date：2020/3/11
 * des：
 */
public class Utils {

    private String name;

    public int calculate(int a, int b) {
        return a + b;
    }

    public Integer toNumber(String num) {
        if (num == null || num.isEmpty()) {
            return null;
        }
        Integer integer;
        try {
            integer = Integer.parseInt(num.trim());
        } catch (Exception e) {
            integer = null;
        }
        return integer;
    }

    public static boolean isEmpty(@Nullable CharSequence str) {
        return str == null || str.length() == 0;
    }

    private String changeName(String name) {
        return "ABC" + name;
    }

    public String getName() {
        return name;
    }

    public String getPersonName() {
        Person person = new Person("Lily");
        return person.getName();
    }
}
