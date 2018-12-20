package com.sinano.devices.model;

import java.util.List;

public class ConfigListBean {


    /**
     * data : {"records":[{"id":"8bbe275372d24bd19971f1e9148baa6f","detectionSiteId":"4e3be98989d64d5084f108a9d73cf290","name":"配置2","content":null,"del":false,"deleteTime":null,"createTime":"2018-12-18 18:05:39"},{"id":"f99105acddc94cfc96c0e30621ee4313","detectionSiteId":"4e3be98989d64d5084f108a9d73cf290","name":"配置1","content":null,"del":false,"deleteTime":null,"createTime":"2018-12-18 18:05:54"}],"total":2,"size":10,"current":1,"searchCount":true,"pages":1}
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
         * records : [{"id":"8bbe275372d24bd19971f1e9148baa6f","detectionSiteId":"4e3be98989d64d5084f108a9d73cf290","name":"配置2","content":null,"del":false,"deleteTime":null,"createTime":"2018-12-18 18:05:39"},{"id":"f99105acddc94cfc96c0e30621ee4313","detectionSiteId":"4e3be98989d64d5084f108a9d73cf290","name":"配置1","content":null,"del":false,"deleteTime":null,"createTime":"2018-12-18 18:05:54"}]
         * total : 2
         * size : 10
         * current : 1
         * searchCount : true
         * pages : 1
         */

        private int total;
        private int size;
        private int current;
        private boolean searchCount;
        private int pages;
        private List<RecordsBean> records;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public boolean isSearchCount() {
            return searchCount;
        }

        public void setSearchCount(boolean searchCount) {
            this.searchCount = searchCount;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public List<RecordsBean> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsBean> records) {
            this.records = records;
        }

        public static class RecordsBean {
            /**
             * id : 8bbe275372d24bd19971f1e9148baa6f
             * detectionSiteId : 4e3be98989d64d5084f108a9d73cf290
             * name : 配置2
             * content : null
             * del : false
             * deleteTime : null
             * createTime : 2018-12-18 18:05:39
             */

            private String id;
            private String detectionSiteId;
            private String name;
            private Object content;
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

            public Object getContent() {
                return content;
            }

            public void setContent(Object content) {
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
}
