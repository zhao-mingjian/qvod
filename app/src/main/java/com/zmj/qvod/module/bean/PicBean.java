package com.zmj.qvod.module.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by matt on 2017/4/17.
 */
public class PicBean implements Serializable {

    private List<ItemsBean> items;

    public List<ItemsBean> getItems() {
        return items;
    }

    public static class ItemsBean implements Serializable {

        private String thumbUrl;
        private String pic_url;
        private String height;
        private String width;

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getWidth() {
            return width;
        }


        public String getThumbUrl() {
            return thumbUrl;
        }

        public String getPic_url() {
            return pic_url;
        }

    }
}
