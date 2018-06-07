package com.ctpsp.common.util;

import java.security.MessageDigest;


/**
 * 采用SHA1加密解密
 */
public class SHA1Util {

	/***
	 * SHA1加码 生成32位md5码
	 */
	public static String string2SHA1(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("SHA1");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuilder hexValue = new StringBuilder();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
                hexValue.append("0");
            }
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();

	}

	/**
	 * 加密解密算法 执行一次加密，两次解密
	 */
	public static String convertSHA1(String inStr) {

		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;

	}
}  