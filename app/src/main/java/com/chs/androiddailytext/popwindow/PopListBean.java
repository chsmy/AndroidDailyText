package com.chs.androiddailytext.popwindow;

/**
 * 作者：chs
 * 时间：2018-07-09 14:43
 * 描述：
 */
public class PopListBean {
    private int id;
    private String name;
    private boolean isClicked;

    public PopListBean(int id, String name, boolean isClicked) {
        this.id = id;
        this.name = name;
        this.isClicked = isClicked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }
}
