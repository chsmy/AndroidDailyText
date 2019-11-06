package com.chs.androiddailytext.widget.pop;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author chs
 * date：2019-05-15 14:30
 * des： 筛选中的recycleview中的item
 */
public class ItemFilter implements Serializable {
    private String name;
    private String subName;
    private boolean isSelected;
    private String id;
    /**
     * 0 挂单 1 超时 2 取消
     */
    private int subListType = -1;
    private List<ItemFilter> subList;

    public ItemFilter(String name, boolean isSelected) {
        this.name = name;
        this.isSelected = isSelected;
    }

    public ItemFilter(String name, String id) {
        this.name = name;
        this.id = id;
    }
    public ItemFilter(String name, String subName, String id) {
        this.name = name;
        this.subName = subName;
        this.id = id;
    }

    public int getSubListType() {
        return subListType;
    }

    public void setSubListType(int subListType) {
        this.subListType = subListType;
    }

    public List<ItemFilter> getSubList() {
        return subList;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubList(List<ItemFilter> subList) {
        this.subList = subList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemFilter filter = (ItemFilter) o;
        return id.equals(filter.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
