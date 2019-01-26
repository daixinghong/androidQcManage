package com.sinano.devices.model;

import java.util.List;

public class DeviceListBean {


    /**
     * data : {"server":[{"id":"337794cca53a4fc5b74554a775ad6702","mac":"12","serverNo":"12","serverName":"12","online":false,"phoneDevice":[]},{"id":"9491b59992524559a6cb69339012dae5","mac":"00:01:6C:06:A6:15","serverNo":"Server002","serverName":"服务器2号","online":false,"phoneDevice":[{"id":"1f6de40c91fb4346bb8b14f8caa14feb","mac":"121212","deviceNo":"12121","deviceName":"1221","online":false}]},{"id":"c5bb59056a074868993da6f9689dc8b2","mac":"00:01:6C:06:A6:12","serverNo":"Server001","serverName":"服务器1号","online":false,"phoneDevice":[{"id":"49a0cd11e03f4361b7ccdd5aebdc956","mac":"12:01:6C:06:A6:26","deviceNo":"589494984","deviceName":"扫描仪2","online":false},{"id":"49a0cd11e03f4361b7ccdd5aebdc9c18","mac":"00:01:6C:06:A6:25","deviceNo":"DEVICE001","deviceName":"扫描仪3","online":false},{"id":"49a0cd11e03f4361b7ccdd5aebdc9c19","mac":"00:01:6C:06:A6:26","deviceNo":"DEVICE004","deviceName":"设备4","online":false},{"id":"4af87402fe8e4377990805a7875551","mac":"00:01:6C:06:A1:13","deviceNo":"DEVICE005","deviceName":"A设备","online":false},{"id":"4af87402fe8e4377990eef805a787550","mac":"10:01:6C:06:A6:13","deviceNo":"DEVICE002","deviceName":"设备2","online":false},{"id":"4af87402fe8e4377990eef805a787551","mac":"00:01:6C:06:A6:13","deviceNo":"DEVICE003","deviceName":"设备3","online":false},{"id":"e9fdac6fbbd74029a0561e46efd24321","mac":"12:15:6C:06:A6:12","deviceNo":"589494983","deviceName":"扫描仪1","online":false}]}],"clothDevice":[{"id":"3769a4e1585ea4b5f886417b2","mac":"00:01:6C:06:A6:A1","deviceNo":null,"deviceName":null,"online":false},{"id":"8af3e2937691585ea4b5f886417b2","mac":"00:01:6C:06:A6:A2","deviceNo":null,"deviceName":null,"online":false},{"id":"8af3e293769a45ea4b5f886417b2","mac":"00:01:6C:06:A6:A3","deviceNo":null,"deviceName":null,"online":false},{"id":"8af3e293769a4e1585ea4b5f886233","mac":"00:01:6C:06:A6:A4","deviceNo":null,"deviceName":null,"online":false},{"id":"8af3e293769a4e1585ea4b5f886417b2","mac":"00:01:6C:06:A6:82","deviceNo":"BP01","deviceName":"布匹检测a","online":false},{"id":"c385fa602876427ba9416e485c2ec811","mac":"bpjc001","deviceNo":"BP001","deviceName":"布匹检测1","online":false},{"id":"c385fa602876427ba9416e485c2ec851","mac":"00:01:6C:06:A6:67","deviceNo":"BP02","deviceName":"布匹检测b","online":false}]}
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
        private List<ClothDeviceBean> clothDevice;
        private List<PhoneDevice> phoneDevice;

        public static class PhoneDevice {

            /**
             * id : 1f6de40c91fb4346bb8b14f8caa14feb
             * mac : 121212
             * deviceNo : 12121
             * deviceName : 1221
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

        public List<PhoneDevice> getPhoneDevice() {
            return phoneDevice;
        }

        public void setPhoneDevice(List<PhoneDevice> phoneDevice) {
            this.phoneDevice = phoneDevice;
        }

        public List<ServerBean> getServer() {
            return server;
        }

        public void setServer(List<ServerBean> server) {
            this.server = server;
        }

        public List<ClothDeviceBean> getClothDevice() {
            return clothDevice;
        }

        public void setClothDevice(List<ClothDeviceBean> clothDevice) {
            this.clothDevice = clothDevice;
        }

        public static class ServerBean {
            /**
             * id : 337794cca53a4fc5b74554a775ad6702
             * mac : 12
             * serverNo : 12
             * serverName : 12
             * online : false
             * phoneDevice : []
             */

            public static class PhoneDevice {

                /**
                 * id : 1f6de40c91fb4346bb8b14f8caa14feb
                 * mac : 121212
                 * deviceNo : 12121
                 * deviceName : 1221
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

            private String id;
            private String mac;
            private String serverNo;
            private String serverName;
            private boolean online;
            private List<PhoneDevice> phoneDevice;

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

            public boolean isOnline() {
                return online;
            }

            public void setOnline(boolean online) {
                this.online = online;
            }

            public List<PhoneDevice> getPhoneDevice() {
                return phoneDevice;
            }

            public void setPhoneDevice(List<PhoneDevice> phoneDevice) {
                this.phoneDevice = phoneDevice;
            }
        }

        public static class ClothDeviceBean {
            /**
             * id : 3769a4e1585ea4b5f886417b2
             * mac : 00:01:6C:06:A6:A1
             * deviceNo : null
             * deviceName : null
             * online : false
             */

            private String id;
            private String mac;
            private Object deviceNo;
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

            public Object getDeviceNo() {
                return deviceNo;
            }

            public void setDeviceNo(Object deviceNo) {
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
