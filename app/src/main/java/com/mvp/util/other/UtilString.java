package com.mvp.util.other;

/**
 * 字符串处理工具类
 * 
 * @ClassName: UtilString
 * @Description: 平常处理可以使用common-lang中的stringutil
 * @author 王超勇
 * @date 2015-5-5
 */ 
public class UtilString {

    public static boolean isNull(String string) {
        if (string == null || string.equals("") || string.equals("\"\"") || string.equals("null")) {
            return true;
        }
        return false;
    }
    
    /**
     * 信息为null返回未登记
     * @param string
     * @return
     */
    public static String isNullReturnNoRemark(String string) {
        if (string == null || string.equals("") || string.equals("\"\"") || string.equals("null")) {
            return "";
        }
        return string;
    }
    
    public static String isNullToReturn(String string){
    	if (string == null || string.equals("") || string.equals("\"\"") || string.equals("null")) {
            return "";
        }
        return string;
    }
    
    public static boolean isNotNull(String string) {
        if (string == null || string.equals("") || string.equals("\"\"") || string.equals("null")) {
            return false;
        }
        return true;
    }

    /**
     * 首字母大写
     * @param name
     * @return
     */
    public static String captureName(String name) {
        //     name = name.substring(0, 1).toUpperCase() + name.substring(1);
//        return  name;
        char[] cs=name.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);

    }
}
