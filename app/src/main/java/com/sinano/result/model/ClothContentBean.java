package com.sinano.result.model;

import java.io.Serializable;
import java.util.List;

public class ClothContentBean implements Serializable{


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
             * bottom : 471
             * left : 388
             * right : 1913
             * top : 55
             * x : 613
             * y : 97
             */

            private String bottom;
            private String left;
            private String right;
            private String top;
            private String x;
            private String y;

            public String getBottom() {
                return bottom;
            }

            public void setBottom(String bottom) {
                this.bottom = bottom;
            }

            public String getLeft() {
                return left;
            }

            public void setLeft(String left) {
                this.left = left;
            }

            public String getRight() {
                return right;
            }

            public void setRight(String right) {
                this.right = right;
            }

            public String getTop() {
                return top;
            }

            public void setTop(String top) {
                this.top = top;
            }

            public String getX() {
                return x;
            }

            public void setX(String x) {
                this.x = x;
            }

            public String getY() {
                return y;
            }

            public void setY(String y) {
                this.y = y;
            }
        }
    }
}
