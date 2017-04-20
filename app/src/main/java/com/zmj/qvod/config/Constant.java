package com.zmj.qvod.config;

import com.jude.beam.expansion.list.ListConfig;
import com.zmj.qvod.R;

/**
 * Created by Wenhuaijun on 2016/5/13 0013.
 */
public class Constant {
    public static final String URI_TYPE_NET = "net";
    public static final String URI_TYPE_NATIVE = "native";

    public static ListConfig getBaseConfig() {
        ListConfig listConfig = new ListConfig();
        return listConfig.setRefreshAble(true)
                .setNoMoreAble(false)
                .setLoadmoreAble(false)
                .setErrorAble(true)
                .setContainerEmptyAble(true)
                .setContainerErrorAble(true)
                .setContainerErrorRes(R.layout.view_net_error)
                .setContainerProgressRes(R.layout.default_loading_page)
                .setContainerEmptyRes(R.layout.view_empty)
                .setLoadMoreRes(R.layout.page_loadmore);
    }

    public static ListConfig getUnloadMoreConfig() {
        return getBaseConfig();
    }

    public static ListConfig getAllConfig() {
        return getLoadMoreConfig().setNoMoreAble(true);
    }

    public static ListConfig getNoMoreConfig() {
        return getBaseConfig().setNoMoreAble(true);
    }

    public static ListConfig getLoadMoreConfig() {
        return getBaseConfig().setLoadmoreAble(true);
    }

}
