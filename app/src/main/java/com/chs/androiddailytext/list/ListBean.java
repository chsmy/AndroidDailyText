package com.chs.androiddailytext.list;

import java.util.List;

/**
 * 作者：chs
 * 时间：2018-05-16 20:09
 * 描述：
 */
public class ListBean {
    /**
     * total_number : 44
     * number : 44
     * pages : 1
     * page : 1
     * returncode : 0
     * data : [{"id":"100","place":"一号楼","pid":"0","ppath":"100","level":"1","lists":[{"id":"101","place":"1单元","pid":"100","ppath":"100,101","level":"2","lists":[{"id":"105","place":"地下通道","pid":"101","ppath":"100,101,105","level":"3","lists":[]}]},{"id":"114","place":"101","pid":"100","ppath":"100,114","level":"2","lists":[]},{"id":"115","place":"102","pid":"100","ppath":"100,115","level":"2","lists":[]},{"id":"116","place":"201","pid":"100","ppath":"100,116","level":"2","lists":[]},{"id":"117","place":"202","pid":"100","ppath":"100,117","level":"2","lists":[]},{"id":"132","place":"夜夜夜夜","pid":"100","ppath":"100,132","level":"2","lists":[]},{"id":"133","place":"有意义有意义","pid":"100","ppath":"100,133","level":"2","lists":[{"id":"138","place":"哈哈","pid":"133","ppath":"100,133,138","level":"3","lists":[]}]},{"id":"135","place":"101","pid":"100","ppath":"100,135","level":"2","lists":[]},{"id":"136","place":"102","pid":"100","ppath":"100,136","level":"2","lists":[]},{"id":"141","place":"大堂","pid":"100","ppath":"100,141","level":"2","lists":[]},{"id":"143","place":"2单元","pid":"100","ppath":"100,143","level":"2","lists":[]},{"id":"144","place":"3单元","pid":"100","ppath":"100,144","level":"2","lists":[]}]},{"id":"102","place":"5号楼","pid":"0","ppath":"102","level":"1","lists":[{"id":"103","place":"602","pid":"102","ppath":"102,103","level":"2","lists":[]}]},{"id":"104","place":"F座","pid":"0","ppath":"104","level":"1","lists":[]},{"id":"106","place":"16区","pid":"0","ppath":"106","level":"1","lists":[{"id":"107","place":"大堂","pid":"106","ppath":"106,107","level":"2","lists":[]},{"id":"108","place":"前台办公室","pid":"106","ppath":"106,108","level":"2","lists":[]},{"id":"109","place":"员工通道","pid":"106","ppath":"106,109","level":"2","lists":[]},{"id":"110","place":"东侧卫生间","pid":"106","ppath":"106,110","level":"2","lists":[]}]},{"id":"111","place":"二号楼","pid":"0","ppath":"111","level":"1","lists":[{"id":"118","place":"101","pid":"111","ppath":"111,118","level":"2","lists":[]},{"id":"119","place":"102","pid":"111","ppath":"111,119","level":"2","lists":[]},{"id":"120","place":"201","pid":"111","ppath":"111,120","level":"2","lists":[]},{"id":"121","place":"202","pid":"111","ppath":"111,121","level":"2","lists":[]},{"id":"142","place":"大堂","pid":"111","ppath":"111,142","level":"2","lists":[]},{"id":"145","place":"2单元","pid":"111","ppath":"111,145","level":"2","lists":[]},{"id":"146","place":"3单元","pid":"111","ppath":"111,146","level":"2","lists":[]}]},{"id":"112","place":"三号楼","pid":"0","ppath":"112","level":"1","lists":[{"id":"122","place":"101","pid":"112","ppath":"112,122","level":"2","lists":[]},{"id":"123","place":"102","pid":"112","ppath":"112,123","level":"2","lists":[]},{"id":"124","place":"201","pid":"112","ppath":"112,124","level":"2","lists":[]},{"id":"125","place":"202","pid":"112","ppath":"112,125","level":"2","lists":[]}]},{"id":"113","place":"四号楼","pid":"0","ppath":"113","level":"1","lists":[{"id":"126","place":"101","pid":"113","ppath":"113,126","level":"2","lists":[]},{"id":"127","place":"102","pid":"113","ppath":"113,127","level":"2","lists":[]},{"id":"128","place":"201","pid":"113","ppath":"113,128","level":"2","lists":[]},{"id":"129","place":"202","pid":"113","ppath":"113,129","level":"2","lists":[]}]},{"id":"139","place":"15区","pid":"0","ppath":"139","level":"1","lists":[{"id":"140","place":"二层","pid":"139","ppath":"139,140","level":"2","lists":[]}]},{"id":"147","place":"55","pid":"0","ppath":"147","level":"1","lists":[]}]
     */

    private String total_number;
    private int number;
    private String pages;
    private String page;
    private String returncode;
    private List<DataEntity> data;

    public String getTotal_number() {
        return total_number;
    }

    public void setTotal_number(String total_number) {
        this.total_number = total_number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getReturncode() {
        return returncode;
    }

    public void setReturncode(String returncode) {
        this.returncode = returncode;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public static class DataEntity {
        /**
         * id : 100
         * place : 一号楼
         * pid : 0
         * ppath : 100
         * level : 1
         * lists : [{"id":"101","place":"1单元","pid":"100","ppath":"100,101","level":"2","lists":[{"id":"105","place":"地下通道","pid":"101","ppath":"100,101,105","level":"3","lists":[]}]},{"id":"114","place":"101","pid":"100","ppath":"100,114","level":"2","lists":[]},{"id":"115","place":"102","pid":"100","ppath":"100,115","level":"2","lists":[]},{"id":"116","place":"201","pid":"100","ppath":"100,116","level":"2","lists":[]},{"id":"117","place":"202","pid":"100","ppath":"100,117","level":"2","lists":[]},{"id":"132","place":"夜夜夜夜","pid":"100","ppath":"100,132","level":"2","lists":[]},{"id":"133","place":"有意义有意义","pid":"100","ppath":"100,133","level":"2","lists":[{"id":"138","place":"哈哈","pid":"133","ppath":"100,133,138","level":"3","lists":[]}]},{"id":"135","place":"101","pid":"100","ppath":"100,135","level":"2","lists":[]},{"id":"136","place":"102","pid":"100","ppath":"100,136","level":"2","lists":[]},{"id":"141","place":"大堂","pid":"100","ppath":"100,141","level":"2","lists":[]},{"id":"143","place":"2单元","pid":"100","ppath":"100,143","level":"2","lists":[]},{"id":"144","place":"3单元","pid":"100","ppath":"100,144","level":"2","lists":[]}]
         */

        private String id;
        private String place;
        private String pid;
        private String ppath;
        private int level;
        private boolean isVisible = false;//是否可见
        private boolean isExpend = false;//是否展开
        private boolean isClicked = false;//是否点击
        private boolean isSelected = false;//是否点击
        private List<DataEntity> lists;

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public boolean isClicked() {
            return isClicked;
        }

        public void setClicked(boolean clicked) {
            isClicked = clicked;
        }

        public boolean isExpend() {
            return isExpend;
        }

        public void setExpend(boolean expend) {
            isExpend = expend;
        }

        public boolean isVisible() {
            return isVisible;
        }

        public void setVisible(boolean visible) {
            isVisible = visible;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getPpath() {
            return ppath;
        }

        public void setPpath(String ppath) {
            this.ppath = ppath;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public List<DataEntity> getLists() {
            return lists;
        }

        public void setLists(List<DataEntity> lists) {
            this.lists = lists;
        }
    }
}
