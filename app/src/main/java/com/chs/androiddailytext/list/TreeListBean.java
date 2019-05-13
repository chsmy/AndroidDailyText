package com.chs.androiddailytext.list;

/**
 * @author chs
 * date：2019-05-10 16:32
 * des：
 */
public class TreeListBean {
    private int id;
    private int pid;
    private int level;
    private String name;
    private TreeListBean parent;
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
    /**
     * 代表目前显示的状态，它有多少个下级数据
     */
    private int moreSize;

    public TreeListBean(int id, int pid, int level, String name,TreeListBean parent) {
        this.id = id;
        this.pid = pid;
        this.level = level;
        this.name = name;
        this.parent = parent;
    }

    public int getMoreSize() {
        return moreSize;
    }

    public void setMoreSize(int moreSize) {
        this.moreSize = moreSize;
    }

    public TreeListBean getParent() {
        return parent;
    }

    public void setParent(TreeListBean parent) {
        this.parent = parent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
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
