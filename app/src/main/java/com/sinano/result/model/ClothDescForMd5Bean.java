package com.sinano.result.model;

public class ClothDescForMd5Bean {


    /**
     * data : {"id":"3b6fab0b1b2e4f1db2c9450a45ee9c0c","createTime":"2019-01-17 10:45:06","mac":"00:01:6C:06:A6:82","content":"{\"bad_info\": [{\"location\": {\"bottom\": \"485\", \"top\": \"323\", \"y\": \"417\", \"x\": \"80\", \"left\": \"1668\", \"right\": \"1887\"}, \"desc\": \"\\u7834\\u635f\", \"type\": \"6\"}, {\"location\": {\"bottom\": \"482\", \"top\": \"329\", \"y\": \"418\", \"x\": \"82\", \"left\": \"1673\", \"right\": \"1885\"}, \"desc\": \"\\u7834\\u635f\", \"type\": \"6\"}, {\"location\": {\"bottom\": \"482\", \"top\": \"326\", \"y\": \"417\", \"x\": \"81\", \"left\": \"1671\", \"right\": \"1891\"}, \"desc\": \"\\u7834\\u635f\", \"type\": \"6\"}]}","description":"{\"width\": \"120\", \"cust\": 90, \"cylinder_numbe\": \"66\", \"piece_no\": \"uuu\", \"title\": \"\", \"color\": \"\", \"date\": \"20190117-104506\", \"weight\": \"8\", \"entrusted_unit\": \"\", \"product_name\": \"af\", \"length\": \"\", \"user\": \"kkkk\", \"order_number\": \"\", \"MD5\": \"c962694bbb265e3d797a353f91f371a3\"}","cust":"a"}
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
         * id : 3b6fab0b1b2e4f1db2c9450a45ee9c0c
         * createTime : 2019-01-17 10:45:06
         * mac : 00:01:6C:06:A6:82
         * content : {"bad_info": [{"location": {"bottom": "485", "top": "323", "y": "417", "x": "80", "left": "1668", "right": "1887"}, "desc": "\u7834\u635f", "type": "6"}, {"location": {"bottom": "482", "top": "329", "y": "418", "x": "82", "left": "1673", "right": "1885"}, "desc": "\u7834\u635f", "type": "6"}, {"location": {"bottom": "482", "top": "326", "y": "417", "x": "81", "left": "1671", "right": "1891"}, "desc": "\u7834\u635f", "type": "6"}]}
         * description : {"width": "120", "cust": 90, "cylinder_numbe": "66", "piece_no": "uuu", "title": "", "color": "", "date": "20190117-104506", "weight": "8", "entrusted_unit": "", "product_name": "af", "length": "", "user": "kkkk", "order_number": "", "MD5": "c962694bbb265e3d797a353f91f371a3"}
         * cust : a
         */

        private String id;
        private String createTime;
        private String mac;
        private String content;
        private String description;
        private String cust;

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

        public String getMac() {
            return mac;
        }

        public void setMac(String mac) {
            this.mac = mac;
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
