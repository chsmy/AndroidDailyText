package com.chs.app_test;

/**
 * author：chs
 * date：2020/3/11
 * des：
 */
public class Person {
    private String name;
    private int age;
    private String duty;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDuty() {
        return duty;
    }

    public String getDuty(int type) {
        switch (type){
            case 0:
                return "教师";
            case 1:
                return "律师";
            case 2:
                return "士兵";
            default: return duty;
        }
    }
    public void setDuty(String duty) {
        this.duty = duty;
    }
}
