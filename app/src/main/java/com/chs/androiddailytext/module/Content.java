package com.chs.androiddailytext.module;

import java.util.List;

/**
 * 作者：chs on 2018-01-04 09:22
 * 邮箱：657083984@qq.com
 */

public class Content {

    private String title;
    private String id;
    private String type;
    private String is_checked;
    private String content;
    private List<OptionEntity> option;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIs_checked() {
        return is_checked;
    }

    public void setIs_checked(String is_checked) {
        this.is_checked = is_checked;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<OptionEntity> getOption() {
        return option;
    }

    public void setOption(List<OptionEntity> option) {
        this.option = option;
    }

    public static class OptionEntity {
        /**
         * value : a
         * is_default : 1
         * key : 1
         */

        private String value;
        private String is_default;
        private String key;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getIs_default() {
            return is_default;
        }

        public void setIs_default(String is_default) {
            this.is_default = is_default;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}
