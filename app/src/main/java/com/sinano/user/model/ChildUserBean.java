package com.sinano.user.model;

import java.util.List;

public class ChildUserBean {


    /**
     * data : [{"uid":68,"username":"fish","nickname":"代欣宏","phone":"15018527184","admin":false,"createTime":"2018-12-26 17:47:12"}]
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
         * uid : 68
         * username : fish
         * nickname : 代欣宏
         * phone : 15018527184
         * admin : false
         * createTime : 2018-12-26 17:47:12
         */

        private int uid;
        private String username;
        private String nickname;
        private String phone;
        private boolean admin;
        private String createTime;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public boolean isAdmin() {
            return admin;
        }

        public void setAdmin(boolean admin) {
            this.admin = admin;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
