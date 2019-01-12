package com.sinano.result.model;

import java.io.Serializable;
import java.util.List;

public class ClothContentBean {


    private List<BadInfoBean> bad_info;

    public List<BadInfoBean> getBad_info() {
        return bad_info;
    }

    public void setBad_info(List<BadInfoBean> bad_info) {
        this.bad_info = bad_info;
    }

    public static class BadInfoBean implements Serializable {
        /**
         * desc : 污渍
         * location : {"bottom":110,"left":232,"right":456,"top":741}
         * type : 1
         */

        private String desc;
        private LocationBean location;
        private int type;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public static class LocationBean implements Serializable {
            /**
             * bottom : 110
             * left : 232
             * right : 456
             * top : 741
             */

            private int bottom;
            private int left;
            private int right;
            private int top;

            public int getBottom() {
                return bottom;
            }

            public void setBottom(int bottom) {
                this.bottom = bottom;
            }

            public int getLeft() {
                return left;
            }

            public void setLeft(int left) {
                this.left = left;
            }

            public int getRight() {
                return right;
            }

            public void setRight(int right) {
                this.right = right;
            }

            public int getTop() {
                return top;
            }

            public void setTop(int top) {
                this.top = top;
            }
        }
    }
}
