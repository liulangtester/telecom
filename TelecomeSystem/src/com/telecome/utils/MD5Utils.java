package com.telecome.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5Utils {

	 /**
	 *MD5加密算法（对密码进行加密）
	  *对字符串md5加密(小写字符+数字)
    * @param str 传入要加密的字符串
    * @return  MD5加密后的字符串
    */
   public static String getMD5(String str) {
       try {
           // 生成一个MD5加密计算摘要
           MessageDigest md = MessageDigest.getInstance("MD5");
           // 计算md5函数
           md.update(str.getBytes());
           // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
           // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
           return new BigInteger(1, md.digest()).toString(16);
       } catch (Exception e) {
          e.printStackTrace();
          return null;
       }
   }
}
