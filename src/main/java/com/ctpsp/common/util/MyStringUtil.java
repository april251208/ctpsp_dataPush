package com.ctpsp.common.util;

import com.ctpsp.common.base.BaseService;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.Random;

/**
 * Created by wangkuan on 16/4/22.
 */
public final class MyStringUtil {
	private MyStringUtil() {
	}

	/**
	 * isEmpty
	 *
	 * @param params
	 * @return
	 */
	public static boolean isEmpty(String... params) {
		boolean flag = false;
		for (String param : params) {
			if (StringUtils.isEmpty(param)) {
				flag = true;
				break;
			}
		}
		return flag;
	}


	/**
	 * isEmpty
	 *
	 * @param params
	 * @return
	 */
	public static boolean isBlank(String... params) {
		boolean flag = false;
		for (String param : params) {
			if (StringUtils.isBlank(param) || "null".equals(param)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	/**
	 * isNoneBlank
	 *
	 * @param params
	 * @return
	 */
	public static boolean isNoneBlank(String... params) {
		return !isBlank(params);
	}

	/**
	 * isNoneEmpty
	 *
	 * @param params
	 * @return
	 */
	public static boolean isNoneEmpty(String... params) {
		return !isEmpty(params);
	}

	/**
	 * 处理图片路径
	 *
	 * @param img
	 * @return
	 */
	public static String addHttp2UriStart(String img) {
		if (null == img || "null" == img || img.contains("http://") || img.contains("https://") || isBlank(img) || "undefined".equals(img)) {
			return img;
		} else {
			return BaseService.BASE_URL + File.separator + img;
		}
	}
	/**
	 * 获取6位随机数
	 */
	public static String getRandom() {
		String str = "0,1,2,3,4,5,6,7,8,9,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z";
		String str2[] = str.split(",");
		Random rand = new Random();
		int index = 0;
		String randStr = "";
		for (int i = 0; i < 6; ++i) {
			index = rand.nextInt(str2.length - 1);
			randStr += str2[index];
		}
		return randStr;
	}
}

