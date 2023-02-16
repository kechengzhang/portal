package com.zgtech.portal.bean;

import com.zgtech.portal.enums.ResultCodeEnum;

import java.io.Serializable;



/**
 * @author zkc
 * @date  2021/05/05
 * @since  jdk 1.8
 * @version  1.0
 *
 * 返回结果
 *
 * @param <T>
 */

public class Result<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	private String code;
	private String msg;
	private T data;
	public Result() {
	}
	public Result(ResultCodeEnum resultCode) {
		this.code = resultCode.getCode();
		this.msg = resultCode.getMessage();
	}

	public Result(ResultCodeEnum resultCode, T data) {
		this.code = resultCode.getCode();
		this.msg = resultCode.getMessage();
		this.data = data;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	/**
	 * 返回成功
	 * @param resultCode
	 * @return
	 */

	public static <T> Result<T> success(ResultCodeEnum resultCode) {
		Result<T> result = new Result<T>();
		result.setCode(resultCode.getCode());
		result.setMsg(resultCode.getMessage());
		return result;
	}
	/**
	 * 返回成功有数据
	 * @param data
	 * @param resultCode
	 * @return
	 */
	public static <T> Result<T> success(ResultCodeEnum resultCode, T data) {
		Result<T> result = new Result<T>();
		result.setCode(resultCode.getCode());
		result.setMsg(resultCode.getMessage());
		result.setData(data);
		return result;
	}
	/**
	 * 返回失败
	 * @param resultCode
	 * @return
	 */
	public static <T> Result<T> failure(ResultCodeEnum resultCode) {
		Result<T> result = new Result<T>();
		result.setCode(resultCode.getCode());
		result.setMsg(resultCode.getMessage());
		return result;
	}
	/**
	 * 返回失败带描述
	 * @param resultCode
	 * @param msg
	 * @return
	 */
	public static <T> Result<T> failure(ResultCodeEnum resultCode, String msg) {
		Result<T> result = new Result<T>();
		result.setCode(resultCode.getCode());
		result.setMsg(msg);
		return result;
	}

	/**
	 * 默认返回失败带数据
	 * @param resultCode
	 * @param data
	 * @return
	 */
	public static <T> Result<T> failure(ResultCodeEnum resultCode, T data) {
		Result<T> result = new Result<T>();
		result.setCode(resultCode.getCode());
		result.setMsg(resultCode.getMessage());
		return result;
	}
	public static <T> Result<T> failure(String code, String msg) {
		Result<T> result = new Result<T>();
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}
}
