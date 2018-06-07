package com.ctpsp.common.util;

import java.util.UUID;

/**
 * Package:com.ctpsp.common.util
 * Author: Haijian.Sun
 * Date: 2016-09-21 17:19
 * Description:
 */
public final class UUIDUtils {
	public static final String generate() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
