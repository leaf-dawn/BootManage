package com.book.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author 李嘉劲
 **/
public class MD5Util {

    /**
     * @Description 生成普通的MD5密码
     **/
    public static String MD5(String input) {
        MessageDigest md5 = null;
        try {
            // 生成普通的MD5密码
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return "check jdk";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        char[] charArray = input.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * @Description比较密码
     */
    public static boolean compare(String md5, String password) {
        String p = MD5(password);
        return p.compareTo(md5) == 0;
    }
}
