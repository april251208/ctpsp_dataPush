package com.ctpsp.common.exception;

/**
 * Package:com.ctpsp.exception
 * Author: Haijian.Sun
 * Date: 2017-01-11 14:45
 * Description: 流程异常类
 */
public class ProcessException extends RuntimeException {


	public ProcessException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProcessException(String message) {
		super(message);
	}

	public ProcessException() {
		super();
	}
}
