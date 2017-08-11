package com.mx.weixin.util;
/**
 * 封装键值对类
 * @author zw
 *
 */
public class MxKeyValue {
	private Integer key;
	private String value;
	@Override
	public String toString() {
		return "MxKeyValue [key=" + key + ", value=" + value + "]";
	}
	public Integer getKey() {
		return key;
	}
	public void setKey(Integer key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
