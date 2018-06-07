package com.ctpsp.common;

/**
 * @author wangkuan
 */
public enum BusinessDataCode {
	SUCCESS("C10000", "操作成功"),
	FAIL("C10003", "操作失败"),
	FAIL_INPUT("C10004", "请检查您输入的信息"),
	SERVICE_EXCEPTION("C10001", "服务器异常"),
	ARGS_ERROR("C10005", "非法请求"),
	NO_DATA("C10008", "暂无数据"),
	VERSION_CANNOT_USE("C10002", "对不起，该版本正在维护中..."),

	USER_LOGIN_FAIL("U10001", "账号或密码错误"),
	USER_NOT_LOGIN("U10002", "没有登录"),
	USER_NOT_EXIST("U10003", "用户不存在"),
	USER_EXIST("U10004", "用户已存在"),
	USER_WAIT_AUDIT("U10005", "对不起,您的账号正在审核"),
	USER_NOT_PASS_AUDIT("U10006", "对不起,您的账号审核没有通过"),
	USER_DISABLE("U10007", "对不起,您的账号已被禁用"),
	USER_HAD_DEL("U10008", "对不起,您的账号已经被删除"),
	USER_OLD_PWD_ERROR("U10009", "对不起,您的旧密码不正确"),

	USER_OLD_PWD_WRONG("U10007", "对不起，您输入的原密码不正确"),
	USER_NOT_ADDCAR("U10008", "对不起，您还没有添加车辆"),
	USER_CHECK_MOBILE("U10009", "请检查您的手机号"),

	SMS_SEND_FAIL("S10001", "对不起，验证码发送失败"),
	SMS_EXPIRE("S10002", "对不起，手机验证码已经失效"),
	SMS_CODE_WRONG("S10003", "对不起，您输入的验证码不正确或已失效"),

	INFO_INSERT_FAIL("I10001", "信息录入失败"),
	INFO_UPDATE_FAIL("I10002", "信息修改失败"),
	ORG_CODE_ERROR("I10003", "人员组织机构代码不正确"),

	MEETING_CANCEL_FAIL("M10001","会议时间已过，不可再撤销会议"),
	MEETING_CANCEL_AUTHORITY("M10002","您没有权限撤销会议"),

	TOKEN_EXPIRE("T10001", "登录失效"),
	USER_NO_SYINFO("U10010", "未查找到参保人失业待遇信息"),
	USER_NO_INFO("U10011", "未查找到参保人基本信息"),
	CARD_NO_VALID("U10012", "身份证号不正确");




	///////////////////////////////////////////////////////////////////ƒƒ
	private String code;
	private String desc;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	BusinessDataCode(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
}
