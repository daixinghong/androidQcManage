package com.sinano.devices.model;

import java.util.List;

public class DeviceListBean {


    /**
     * data : {"records":[{"id":"853b75c972b64612b33460a0f6b46f61","deviceNo":"D002","deviceName":"Device2","supplier":"阿里巴巴","bind":"-","del":false,"createTime":"2018-12-11 09:22:28"},{"id":"f9682f30101e44acb3f89a7a3f283ebb","deviceNo":"D001","deviceName":"设备1","supplier":"腾讯公司","bind":"-","del":false,"createTime":"2018-12-07 11:16:24"}],"total":2,"size":10,"current":1,"searchCount":true,"pages":1}
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
         * records : [{"id":"853b75c972b64612b33460a0f6b46f61","deviceNo":"D002","deviceName":"Device2","supplier":"阿里巴巴","bind":"-","del":false,"createTime":"2018-12-11 09:22:28"},{"id":"f9682f30101e44acb3f89a7a3f283ebb","deviceNo":"D001","deviceName":"设备1","supplier":"腾讯公司","bind":"-","del":false,"createTime":"2018-12-07 11:16:24"}]
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
             * id : 853b75c972b64612b33460a0f6b46f61
             * deviceNo : D002
             * deviceName : Device2
             * supplier : 阿里巴巴
             * bind : -
             * del : false
             * createTime : 2018-12-11 09:22:28
             */

            private String id;
            private String deviceNo;
            private String deviceName;
            private String supplier;
            private String bind;
            private boolean del;
            private String createTime;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getSupplier() {
                return supplier;
            }

            public void setSupplier(String supplier) {
                this.supplier = supplier;
            }

            public String getBind() {
                return bind;
            }

            public void setBind(String bind) {
                this.bind = bind;
            }

            public boolean isDel() {
                return del;
            }

            public void setDel(boolean del) {
                this.del = del;
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
