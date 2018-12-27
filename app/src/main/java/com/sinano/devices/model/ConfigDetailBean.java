package com.sinano.devices.model;

import java.util.List;

public class ConfigDetailBean {


    /**
     * data : [{"id":"5e62e310304e4bf5bd676b76689697e2","createTime":"2018-12-21 09:40:37","configId":"edf9d2dcb2f44836ab37913b54130f21","version":"20181221094037-378","content":"配置内容"},{"id":"edf9d2dcb2f44836ab37913b54130f21","createTime":"2018-12-21 09:40:37","configId":"edf9d2dcb2f44836ab37913b54130f21","version":"20181221094052-707","content":"配置内容"}]
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
         * id : 5e62e310304e4bf5bd676b76689697e2
         * createTime : 2018-12-21 09:40:37
         * configId : edf9d2dcb2f44836ab37913b54130f21
         * version : 20181221094037-378
         * content : 配置内容
         */

        private String id;
        private String createTime;
        private String configId;
        private String version;
        private String content;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getConfigId() {
            return configId;
        }

        public void setConfigId(String configId) {
            this.configId = configId;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
