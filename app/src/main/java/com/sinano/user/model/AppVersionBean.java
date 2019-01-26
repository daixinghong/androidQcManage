package com.sinano.user.model;

public class AppVersionBean {


    /**
     * data : {"name":"1.1.1","remarks":"1.喜爱哈哈\r\n2.哈哈嘻嘻我\r\n3.呵呵呵无无误\r\n4.哈哈哈哈呵呵呵\r\n5.呜呜呜呜嘻嘻嘻呼","versionNo":"2","createTime":"2019-01-15 16:22:22","forcedUpdate":0,"uri":"advan-models.oss-cn-shenzhen.aliyuncs.com/2019-01-15/3fe73464-e5d8-4075-b90c-1e296fa8a75eapp-release.apk","size":5933211}
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
        /**
         * name : 1.1.1
         * remarks : 1.喜爱哈哈
         2.哈哈嘻嘻我
         3.呵呵呵无无误
         4.哈哈哈哈呵呵呵
         5.呜呜呜呜嘻嘻嘻呼
         * versionNo : 2
         * createTime : 2019-01-15 16:22:22
         * forcedUpdate : 0
         * uri : advan-models.oss-cn-shenzhen.aliyuncs.com/2019-01-15/3fe73464-e5d8-4075-b90c-1e296fa8a75eapp-release.apk
         * size : 5933211
         */

        private String name;
        private String remarks;
        private String versionNo;
        private String createTime;
        private int forcedUpdate;
        private String uri;
        private double size;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getVersionNo() {
            return versionNo;
        }

        public void setVersionNo(String versionNo) {
            this.versionNo = versionNo;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getForcedUpdate() {
            return forcedUpdate;
        }

        public void setForcedUpdate(int forcedUpdate) {
            this.forcedUpdate = forcedUpdate;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public double getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }
    }
}
