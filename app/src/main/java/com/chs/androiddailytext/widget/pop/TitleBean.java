package com.chs.androiddailytext.widget.pop;

import java.util.List;

/**
 * @author chs
 * date：2019-11-06 16:01
 * des：
 */
public class TitleBean {
    private String name;
    private boolean isSelected;
    private List<ItemFilter> selectData;

    public TitleBean(String name, boolean isSelected,List<ItemFilter> selectData) {
        this.name = name;
        this.isSelected = isSelected;
        this.selectData = selectData;
    }

    public List<ItemFilter> getSelectData() {
        return selectData;
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
}
