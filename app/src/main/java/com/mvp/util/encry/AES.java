package com.mvp.util.encry;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {

        private static final String CIPHER_MODE = "AES/CBC/PKCS5Padding";

        public static final String KEY_VALUE = "D56572E740EE8C06";

        /**
         * AES加密字符串
         *
         * @param content
         *            需要被加密的字符串
         * @param password
         *            加密需要的密码
         * @return 密文
         */
        public static byte[] encrypt(String content, String password, String iv) {
                try {

                        SecretKeySpec key = new SecretKeySpec(password.getBytes("UTF-8"), "AES");// 转换为AES专用密钥

                        Cipher cipher = Cipher.getInstance(CIPHER_MODE);// 创建密码器

                        byte[] byteContent = content.getBytes("utf-8");

//                        System.out.println("md5移位：" + iv.substring(0, 16));

                        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv.getBytes("UTF-8")));
                        // 初始化为加密模式的密码器

                        byte[] result = cipher.doFinal(byteContent);// 加密

                        return result;

                } catch (NoSuchPaddingException e) {
                        e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                } catch (InvalidKeyException e) {
                        e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                        e.printStackTrace();
                } catch (BadPaddingException e) {
                        e.printStackTrace();
                } catch (InvalidAlgorithmParameterException e) {
                        e.printStackTrace();
                }
                return null;
        }

        /**
         * 解密AES加密过的字符串
         *
         * @param content
         *            AES加密过过的内容
         * @param password
         *            加密时的密码
         * @return 明文
         */
        public static byte[] decrypt(byte[] content, String password, String iv) {
                try {

                        SecretKeySpec key = new SecretKeySpec(password.getBytes("UTF-8"), "AES");// 转换为AES专用密钥

                        Cipher cipher = Cipher.getInstance(CIPHER_MODE);

//                        System.out.println("md5移位：" + iv.substring(0, 16));

                        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv.getBytes("UTF-8")));

                        byte[] result = cipher.doFinal(content);

                        return result; // 明文

                } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                        e.printStackTrace();
                } catch (InvalidKeyException e) {
                        e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                        e.printStackTrace();
                } catch (BadPaddingException e) {
                        e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                } catch (InvalidAlgorithmParameterException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                return null;
        }
}
