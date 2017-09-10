package com.mx.weixin.pojo;

import com.mx.weixin.util.JsapiTicketUtil;
import com.mx.weixin.util.WeixinSignUtil;

/**
 * jdsdk认证信息封装
 * @author zw
 *
 */
public class JDSDKTicket {
	private String appId = WeixinSignUtil.AppID;//服务器appid
	private String timestamp = JsapiTicketUtil.timestamp;//时间戳
	private String nonceStr = JsapiTicketUtil.noncestr;//随机字符串
	private String signature;//签名
	private String jsApiList = "'checkJsApi'";//api列表
	
	@Override
	public String toString() {
		return "JDSDKTicket [appId=" + appId + ", timestamp=" + timestamp
				+ ", nonceStr=" + nonceStr + ", signature=" + signature
				+ ", jsApiList=" + jsApiList + "]";
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getJsApiList() {
		return jsApiList;
	}
	public void setJsApiList(String jsApiList) {
		this.jsApiList = jsApiList;
	}
	
	
}
