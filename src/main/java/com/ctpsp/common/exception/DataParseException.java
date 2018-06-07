package com.ctpsp.common.exception;


/**
 * 数据转换异常类
 * @author Haijian.sun
 * @version 2016年9月22日 08:49:54
 */
@SuppressWarnings("serial")
public class DataParseException extends RuntimeException {

	public DataParseException() {
	}

	public DataParseException(Throwable ex) {
		super(ex);
	}

	public DataParseException(String message) {
		super(message);
	}

	public DataParseException(String message, Throwable ex) {
		super(message, ex);
	}

}
