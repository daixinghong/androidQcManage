package com.sinano.result.model;

import java.util.List;

public class DeviceResultForConfigBean {

    /**
     * data : [{"no":1,"device_name":"设备3","yes":2,"mac":"00:01:6C:06:A6:13"},{"no":5,"device_name":"扫描仪3","yes":5,"mac":"00:01:6C:06:A6:25"},{"no":0,"device_name":"扫描仪2","yes":6,"mac":"12:01:6C:06:A6:26"}]
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
         * no : 1
         * device_name : 设备3
         * yes : 2
         * mac : 00:01:6C:06:A6:13
         */

        private int no;
        private String device_name;
        private int yes;
        private String mac;
        private String config_name;
        private String config_id;

        public String getConfig_id() {
            return config_id;
        }

        public void setConfig_id(String config_id) {
            this.config_id = config_id;
        }

        public String getConfig_name() {
            return config_name;
        }

        public void setConfig_name(String config_name) {
            this.config_name = config_name;
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public String getDevice_name() {
            return device_name;
        }

        public void setDevice_name(String device_name) {
            this.device_name = device_name;
        }

        public int getYes() {
            return yes;
        }

        public void setYes(int yes) {
            this.yes = yes;
        }

        public String getMac() {
            return mac;
        }

        public void setMac(String mac) {
            this.mac = mac;
        }
    }
}
