package com.zmj.qvod.utils;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Description:
 * Creator: yxc
 * date: $date $time
 */
public class StringUtils {
    /**
     * 去掉特殊字符
     *
     * @param s
     * @return
     */
    public static String removeOtherCode(String s) {
        if (TextUtils.isEmpty(s))
            return "";
        s = s.replaceAll("\\<.*?>|\\n", "");
        return s;
    }

    /**
     * 判断非空
     *
     * @param str
     * @return
     */
    public static String isEmpty(String str) {
        String result = TextUtils.isEmpty(str) ? "" : str;
        return result;
    }

    /**
     * 根据Url获取catalogId
     *
     * @param url
     * @return
     */
    public static String getCatalogId(String url) {
        String catalogId = null;
        if (!TextUtils.isEmpty(url) && url.contains("=")) {
            catalogId = url.substring(url.lastIndexOf("=") + 1);
        }

        return catalogId;
    }

    public static int getRandomNumber(int min, int max) {
        return new Random().nextInt(max) % (max - min + 1) + min;
    }

    public static String getErrorMsg(String msg) {
        if (msg.contains("*")) {
            msg = msg.substring(msg.indexOf("*") + 1);
            return msg;
        } else
            return "";
    }

//    public static void setIconDrawable(Context mContext, TextView view, IIcon icon, int size, int padding) {
//        view.setCompoundDrawablesWithIntrinsicBounds(new IconicsDrawable(mContext)
//                        .icon(icon)
//                        .color(Color.WHITE)
//                        .sizeDp(size),
//                null, null, null);
//        view.setCompoundDrawablePadding(ScreenUtil.dip2px(mContext, padding));
//    }

    /**
     * 解析出url参数中的键值对
     * 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
     *
     * @param URL url地址
     * @return url请求参数部分
     */
    public static Map<String, String> URLRequest(String URL) {
        Map<String, String> mapRequest = new HashMap<>();

        String[] arrSplit = null;

        String strUrlParam = TruncateUrlPage(URL);
        if (strUrlParam == null) {
            return mapRequest;
        }
        //每个键值为一组 www.2cto.com
        arrSplit = strUrlParam.split("[&]");
        for (String strSplit : arrSplit) {
            String[] arrSplitEqual = null;
            arrSplitEqual = strSplit.split("[=]");

            //解析出键值
            if (arrSplitEqual.length > 1) {
                //正确解析
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);

            } else {
                if (arrSplitEqual[0] != "") {
                    //只有参数没有值，不加入
                    mapRequest.put(arrSplitEqual[0], "");
                }
            }
        }
        return mapRequest;
    }

    /**
     * 去掉url中的路径，留下请求参数部分
     *
     * @param strURL url地址
     * @return url请求参数部分
     */
    private static String TruncateUrlPage(String strURL) {
        String strAllParam = null;
        String[] arrSplit = null;

        strURL = strURL.trim();

        arrSplit = strURL.split("[?]");
        if (strURL.length() > 1) {
            if (arrSplit.length > 1) {
                if (arrSplit[1] != null) {
                    strAllParam = arrSplit[1];
                }
            }
        }
        return strAllParam;
    }
}
