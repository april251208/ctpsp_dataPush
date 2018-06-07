package com.ctpsp.common.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Properties;

/**
 * 解析文件的配置
 **/
public class PropertyUtil {

	private static Properties properties = new Properties();

	static {
		InputStream inputStream = PropertyUtil.class.getClassLoader().getResourceAsStream("config.properties");
		try {
			properties.load(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***
	 * 根据key获取values
	 *
	 * @param key
	 * @return
	 **/
	public static String getValues(String key) {
		Enumeration en = properties.propertyNames();
		String name = null;
		while (en.hasMoreElements()) {
			String per_key = (String) en.nextElement();
			if (key.equals(per_key)) {
				name = properties.getProperty(key);
			}
		}
		return name;
	}


}
