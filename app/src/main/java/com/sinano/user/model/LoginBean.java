package com.sinano.user.model;

public class LoginBean {


    /**
     * data : {"uid":55,"username":"fish","nickname":"daixinhong","phone":15018527184,"admin":false,"createTime":"2018-12-22 10:02:47","token":"986dfbbbd3d740ec825655b6d8153592"}
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
         * uid : 55
         * username : fish
         * nickname : daixinhong
         * phone : 15018527184
         * admin : false
         * createTime : 2018-12-22 10:02:47
         * token : 986dfbbbd3d740ec825655b6d8153592
         */
        private int uid;
        private String username;
        private String nickname;
        private long phone;
        private boolean admin;
        private String createTime;
        private String token;

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

        public long getPhone() {
            return phone;
        }

        public void setPhone(long phone) {
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

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
