package com.example.learn.utils;

import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ResultUtil {
	org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

	// 成功状态码
	public static final String RET_CODE = "000000";
	// 成功信息
	public static final String RET_MSG = "success";
	// 失败状态码
	public static final String ERR_CODE = "999999";
	// 失败信息
	public static final String ERR_MSG = "error";

	// 返回状态码参数
	private String code;
	// 返回信息参数
	private String msg;
	// 返回结果参数
	private Object payload;

	private ResultUtil () {
	}

	public String getCode () {
		return code;
	}

	public void setCode (String code) {
		this.code = code;
	}

	public String getMsg () {
		return msg;
	}

	public void setMsg (String msg) {
		this.msg = msg;
	}

	public Object getPayload () {
		return payload;
	}

	public void setPayload (Object payload) {
		this.payload = payload;
	}

	public static ResultUtil success () {
		ResultUtil result = new ResultUtil();
		result.setCode(RET_CODE);
		result.setMsg(RET_MSG);
		return result;
	}

	public static ResultUtil error () {
		return error(ERR_CODE, ERR_MSG);
	}

	public static ResultUtil error (String msg) {
		return error(ERR_CODE, msg);
	}

	public static ResultUtil error (String code, String msg) {
		ResultUtil result = new ResultUtil();
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}

	public ResultUtil code (String code) {
		this.code = code;
		return this;
	}

	public ResultUtil msg (String msg) {
		this.msg = msg;
		return this;
	}

	public ResultUtil render (Object el) {
		this.payload = el;
		return this;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResultUtil render (String key, Object value) {
		if (this.payload == null) {
			this.payload = new HashMap<String, Object>();
		}
		if (this.payload instanceof Map) {
			Map map = (Map) this.payload;
			map.put(key, value);
		} else {
			logger.error("The type of payload should be Map");
		}
		return this;
	}
}
