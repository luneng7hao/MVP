package com.mvp.app;

/**
 * Created by Asion on 2017/12/13.
 */

public class HttpConstants {

    //服务器的地址
//    public static final String  HOST_SITE_HTTPS = "http://123.57.218.227:7001/jscmonitorWEB/";
//    public static final String  HOST_SITE_HTTPS = "http://123.57.218.227:7002/jscmonitor/";
    public static final String  HOST_SITE_HTTPS = "https://api.douban.com/v2/movie/";
    //是否需要发送报文头信息
    public static final boolean ISHEADER=false;
    //网络请求超时时长，单位秒
    public static final long DEFAULT_TIMEOUT = 60;
    //请求的字符集
    public static final String CHARSET ="UTF-8";

}
