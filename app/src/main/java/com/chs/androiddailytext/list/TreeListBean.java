package com.chs.androiddailytext.list;

/**
 * @author chs
 * date：2019-05-10 16:32
 * des：
 */
public class TreeListBean {
    private String id;
    private String pid;
    private int level;
    private String name;
    /**
     * 是否展开
     */
    private boolean isExpend = false;
    /**
     * 是否选中
     */
    private boolean isSelected = false;
    /**
     * 是否有下级
     */
    private boolean hasLower = true;

    public TreeListBean(String id, String pid, int level, String name) {
        this.id = id;
        this.pid = pid;
        this.level = level;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isExpend() {
        return isExpend;
    }

    public void setExpend(boolean expend) {
        isExpend = expend;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isHasLower() {
        return hasLower;
    }

    public void setHasLower(boolean hasLower) {
        this.hasLower = hasLower;
    }
}
