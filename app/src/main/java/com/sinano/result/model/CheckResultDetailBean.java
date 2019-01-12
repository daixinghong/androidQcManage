package com.sinano.result.model;

import java.util.List;

public class CheckResultDetailBean {


    /**
     * data : {"records":[{"id":"02d51990f1e84362adee1b35db9c0c20","createTime":"2019-01-09 11:17:49","content":"JSON内容","description":"描述","cust":"a"},{"id":"02d51990f1e84362adee1b35db9c0c21","createTime":"2019-01-09 11:17:49","content":"JSON内容","description":"描述","cust":"a"}],"total":2,"size":10,"current":1,"searchCount":true,"pages":1}
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
         * records : [{"id":"02d51990f1e84362adee1b35db9c0c20","createTime":"2019-01-09 11:17:49","content":"JSON内容","description":"描述","cust":"a"},{"id":"02d51990f1e84362adee1b35db9c0c21","createTime":"2019-01-09 11:17:49","content":"JSON内容","description":"描述","cust":"a"}]
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
             * id : 02d51990f1e84362adee1b35db9c0c20
             * createTime : 2019-01-09 11:17:49
             * content : JSON内容
             * description : 描述
             * cust : a
             */

            private String id;
            private String createTime;
            private String content;
            private String description;
            private String cust;
            private List<String> uri;
            private boolean status;

            public List<String> getUrl() {
                return uri;
            }

            public void setUrl(List<String> url) {
                this.uri = url;
            }

            public boolean isStatus() {
                return status;
            }

            public void setStatus(boolean status) {
                this.status = status;
            }


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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getCust() {
                return cust;
            }

            public void setCust(String cust) {
                this.cust = cust;
            }
        }
    }
}
