package com.sinano.devices.model;

import java.util.List;

public class TypeBean {


    /**
     * data : [{"id":"364c0cde9e194b9e987dfb7a885507e2","name":"包装检测","value":null,"type":10,"createTime":"2018-12-13 18:15:23","childCount":1},{"id":"4858356422014919a5c053cc492ecf50","name":"半成品检测","value":"","type":10,"createTime":"2018-12-11 09:22:28","childCount":0},{"id":"4858356422014919a5c053cc492ecf89","name":"外观检测","value":"","type":10,"createTime":"2018-12-11 09:22:28","childCount":0}]
     * code : 200
     */

    private int code;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 364c0cde9e194b9e987dfb7a885507e2
         * name : 包装检测
         * value : null
         * type : 10
         * createTime : 2018-12-13 18:15:23
         * childCount : 1
         */

        private String id;
        private String name;
        private Object value;
        private int type;
        private String createTime;
        private int childCount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getChildCount() {
            return childCount;
        }

        public void setChildCount(int childCount) {
            this.childCount = childCount;
        }
    }
}
