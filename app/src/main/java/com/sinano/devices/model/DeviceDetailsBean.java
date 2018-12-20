package com.sinano.devices.model;

public class DeviceDetailsBean {


    /**
     * data : {"id":"8bbe275372d24bd19971f1e9148baa6f","detectionSiteId":"4e3be98989d64d5084f108a9d73cf290","name":"配置2","content":"{\r\n\t\"user\": \"uid12345\",\r\n\t\"age\": \"12345\"\r\n}","del":false,"deleteTime":null,"createTime":"2018-12-18 18:05:39"}
     * code : 200
     */

    private DataBean data;
    private int code;

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
        /**
         * id : 8bbe275372d24bd19971f1e9148baa6f
         * detectionSiteId : 4e3be98989d64d5084f108a9d73cf290
         * name : 配置2
         * content : {
         "user": "uid12345",
         "age": "12345"
         }
         * del : false
         * deleteTime : null
         * createTime : 2018-12-18 18:05:39
         */

        private String id;
        private String detectionSiteId;
        private String name;
        private String content;
        private boolean del;
        private Object deleteTime;
        private String createTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDetectionSiteId() {
            return detectionSiteId;
        }

        public void setDetectionSiteId(String detectionSiteId) {
            this.detectionSiteId = detectionSiteId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public boolean isDel() {
            return del;
        }

        public void setDel(boolean del) {
            this.del = del;
        }

        public Object getDeleteTime() {
            return deleteTime;
        }

        public void setDeleteTime(Object deleteTime) {
            this.deleteTime = deleteTime;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
