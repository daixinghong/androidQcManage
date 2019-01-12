package com.sinano.result.model;

import java.util.List;

public class ResultBean {


    /**
     * data : {"config":[{"name":"配置1","no":0,"yes":6,"cid":"137aa8a682f14f15894634552df9c807"},{"name":"配置2","no":1,"yes":1,"cid":"47a6f47a693d404f865880af591be7c1"}],"device":[{"name":"扫描仪","no":1,"yes":3},{"name":"设备2","no":0,"yes":0},{"name":"设备3","no":0,"yes":4}]}
     * code : 200
     */

    private DataBean data;
    private int code;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class DataBean {
        private List<ConfigBean> config;
        private List<DeviceBean> device;

        public List<ConfigBean> getConfig() {
            return config;
        }

        public void setConfig(List<ConfigBean> config) {
            this.config = config;
        }

        public List<DeviceBean> getDevice() {
            return device;
        }

        public void setDevice(List<DeviceBean> device) {
            this.device = device;
        }

        public static class ConfigBean {
            /**
             * name : 配置1
             * no : 0
             * yes : 6
             * cid : 137aa8a682f14f15894634552df9c807
             */

            private String name;
            private int no;
            private int yes;
            private String cid;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getNo() {
                return no;
            }

            public void setNo(int no) {
                this.no = no;
            }

            public int getYes() {
                return yes;
            }

            public void setYes(int yes) {
                this.yes = yes;
            }

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }
        }

        public static class DeviceBean {
            /**
             * name : 扫描仪
             * no : 1
             * yes : 3
             */

            private String name;
            private int no;
            private int yes;
            private String mac;
            private String type;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getMac() {
                return mac;
            }

            public void setMac(String mac) {
                this.mac = mac;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getNo() {
                return no;
            }

            public void setNo(int no) {
                this.no = no;
            }

            public int getYes() {
                return yes;
            }

            public void setYes(int yes) {
                this.yes = yes;
            }
        }
    }
}
