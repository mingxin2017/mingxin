package com.mx.ssh.util;
/**
 * 返回信息封装类
 * @author zw
 *
 */
public class MsgResult {
	private String msg;//信息
	private boolean success = true;//成功1失败0标识
	private Object data;//返回对象
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
}
