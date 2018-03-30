package com.mvp.util.encry;


import com.mvp.util.other.UtilLog;
import com.mvp.util.other.UtilString;

import java.io.UnsupportedEncodingException;

/**
 * Created by Wcy on 17/五月/18.
 * 加密工具类
 */
public class Encryption {
	
    /**
     * 数据加密
     */
    public static String dataEncryption(String content, String uuid){

        UtilLog.i("Encryption","加密前的内容:" +content);

        String md51 = MD5.StrTo32MD5("" + uuid).substring(0,16);

        UtilLog.i("Encryption","md5移位:" + md51);

        byte[] byt = AES.encrypt(content, AES.KEY_VALUE, md51);

        String encryptData = Base64.encode(byt);

        UtilLog.i("Encryption","加密后的内容:" + encryptData);

        return encryptData;

    }

    /**
     * 数据解密
     */
    public static String dataDencryption(String content, String uuid){

        String md51 = MD5.StrTo32MD5("" + uuid).substring(0,16);

        UtilLog.i("Encryption","md5移位:" + md51);

        String encryptData = "";
        if(UtilString.isNull(content)||UtilString.isNull(uuid)){
            return encryptData;
        }

        try {
            encryptData = new String(AES.decrypt(Base64.decode(content),AES. KEY_VALUE,md51), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println("解密后的内容:"+encryptData);

        return encryptData;

    }
    
    public static void main(String[] args) {
		String s="ABCDE";
		s=s.substring(1);
		System.out.println(s);
	}
}
