package com.sinano.devices.model;

public class DeviceInfoBean {


    /**
     * data : {"id":"01e8b0bda0f846eba185d3e42465fb48","serverId":null,"type":"cloth","classificationId":null,"mac":"7F","deviceNo":"1111","deviceName":"wwww","supplierId":"e9fdac6fbbd74029a0561e46efd24321","bindId":"f00c9af3a81d424eb2b7758272fd210","createTime":null,"confVerId":null,"diskSeq":null,"deviceInfo":"{\"user\": \"fish\", \"length\": 200, \"bad_count\": 20}","deviceAllData":"{\"all_meter\": 200, \"all_bad_count\": 40, \"all_cloth_count\": 2}","deviceDataDay":"{\"badCount\":60,\"cloth_count\":2,\"length\":400}","bind":null,"supplier":null,"classification":null}
     * msg : 成功
     * code : 200
     */

    private DataBean data;
    private String msg;
    private int code;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class DataBean {
        /**
         * id : 01e8b0bda0f846eba185d3e42465fb48
         * serverId : null
         * type : cloth
         * classificationId : null
         * mac : 7F
         * deviceNo : 1111
         * deviceName : wwww
         * supplierId : e9fdac6fbbd74029a0561e46efd24321
         * bindId : f00c9af3a81d424eb2b7758272fd210
         * createTime : null
         * confVerId : null
         * diskSeq : null
         * deviceInfo : {"user": "fish", "length": 200, "bad_count": 20}
         * deviceAllData : {"all_meter": 200, "all_bad_count": 40, "all_cloth_count": 2}
         * deviceDataDay : {"badCount":60,"cloth_count":2,"length":400}
         * bind : null
         * supplier : null
         * classification : null
         */

        private String id;
        private Object serverId;
        private String type;
        private Object classificationId;
        private String mac;
        private String deviceNo;
        private String deviceName;
        private String supplierId;
        private String bindId;
        private Object createTime;
        private Object confVerId;
        private Object diskSeq;
        private String deviceInfo;
        private String deviceAllData;
        private String deviceDataDay;
        private Object bind;
        private Object supplier;
        private Object classification;
        private String isUpdate;

        public String getIsUpdate() {
            return isUpdate;
        }

        public void setIsUpdate(String isUpdate) {
            this.isUpdate = isUpdate;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getServerId() {
            return serverId;
        }

        public void setServerId(Object serverId) {
            this.serverId = serverId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getClassificationId() {
            return classificationId;
        }

        public void setClassificationId(Object classificationId) {
            this.classificationId = classificationId;
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

        public String getSupplierId() {
            return supplierId;
        }

        public void setSupplierId(String supplierId) {
            this.supplierId = supplierId;
        }

        public String getBindId() {
            return bindId;
        }

        public void setBindId(String bindId) {
            this.bindId = bindId;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getConfVerId() {
            return confVerId;
        }

        public void setConfVerId(Object confVerId) {
            this.confVerId = confVerId;
        }

        public Object getDiskSeq() {
            return diskSeq;
        }

        public void setDiskSeq(Object diskSeq) {
            this.diskSeq = diskSeq;
        }

        public String getDeviceInfo() {
            return deviceInfo;
        }

        public void setDeviceInfo(String deviceInfo) {
            this.deviceInfo = deviceInfo;
        }

        public String getDeviceAllData() {
            return deviceAllData;
        }

        public void setDeviceAllData(String deviceAllData) {
            this.deviceAllData = deviceAllData;
        }

        public String getDeviceDataDay() {
            return deviceDataDay;
        }

        public void setDeviceDataDay(String deviceDataDay) {
            this.deviceDataDay = deviceDataDay;
        }

        public Object getBind() {
            return bind;
        }

        public void setBind(Object bind) {
            this.bind = bind;
        }

        public Object getSupplier() {
            return supplier;
        }

        public void setSupplier(Object supplier) {
            this.supplier = supplier;
        }

        public Object getClassification() {
            return classification;
        }

        public void setClassification(Object classification) {
            this.classification = classification;
        }
    }
}
