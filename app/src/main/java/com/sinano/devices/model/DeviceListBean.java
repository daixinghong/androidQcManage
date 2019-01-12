package com.sinano.devices.model;

import java.util.List;

public class DeviceListBean {


    /**
     * data : {"server":[{"id":"9491b59992524559a6cb69339012dae5","mac":"00:01:6C:06:A6:15","serverNo":"Server002","serverName":"服务器2号","phoneDevice":[]},{"id":"c5bb59056a074868993da6f9689dc8b2","mac":"00:01:6C:06:A6:12","serverNo":"Server001","serverName":"服务器1号","phoneDevice":[{"id":"49a0cd11e03f4361b7ccdd5aebdc9c18","mac":"00:01:6C:06:A6:25","deviceNo":"DEVICE001","deviceName":"扫描仪","online":false},{"id":"4af87402fe8e4377990eef805a787550","mac":"00:01:6C:06:A6:12","deviceNo":"DEVICE002","deviceName":"设备2","online":false},{"id":"4af87402fe8e4377990eef805a787551","mac":"00:01:6C:06:A6:13","deviceNo":"DEVICE003","deviceName":"设备3","online":false}]}],"clothDevice":[]}
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
        private List<ServerBean> server;
        private List<?> clothDevice;

        public List<ServerBean> getServer() {
            return server;
        }

        public void setServer(List<ServerBean> server) {
            this.server = server;
        }

        public List<?> getClothDevice() {
            return clothDevice;
        }

        public void setClothDevice(List<?> clothDevice) {
            this.clothDevice = clothDevice;
        }

        public static class ServerBean {
            /**
             * id : 9491b59992524559a6cb69339012dae5
             * mac : 00:01:6C:06:A6:15
             * serverNo : Server002
             * serverName : 服务器2号
             * phoneDevice : []
             */

            private String id;
            private String mac;
            private String serverNo;
            private String serverName;
            private boolean online;
            private List<PhoneBeanDevices> phoneDevice;

            public boolean isOnline() {
                return online;
            }

            public void setOnline(boolean online) {
                this.online = online;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMac() {
                return mac;
            }

            public void setMac(String mac) {
                this.mac = mac;
            }

            public String getServerNo() {
                return serverNo;
            }

            public void setServerNo(String serverNo) {
                this.serverNo = serverNo;
            }

            public String getServerName() {
                return serverName;
            }

            public void setServerName(String serverName) {
                this.serverName = serverName;
            }

            public List<PhoneBeanDevices> getPhoneDevice() {
                return phoneDevice;
            }

            public void setPhoneDevice(List<PhoneBeanDevices> phoneDevice) {
                this.phoneDevice = phoneDevice;
            }
        }

        public class PhoneBeanDevices {

            /**
             * id : 4af87402fe8e4377990eef805a787550
             * mac : 00:01:6C:06:A6:12
             * deviceNo : DEVICE002
             * deviceName : 设备2
             * online : false
             */

            private String id;
            private String mac;
            private String deviceNo;
            private String deviceName;
            private boolean online;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMac() {
                return mac;
            }

            public void setMac(String mac) {
                this.mac = mac;
            }

            public String getDeviceNo() {
                return deviceNo;
            }

            public void setDeviceNo(String deviceNo) {
                this.deviceNo = deviceNo;
            }

            public String getDeviceName() {
                return deviceName;
            }

            public void setDeviceName(String deviceName) {
                this.deviceName = deviceName;
            }

            public boolean isOnline() {
                return online;
            }

            public void setOnline(boolean online) {
                this.online = online;
            }
        }
    }
}
