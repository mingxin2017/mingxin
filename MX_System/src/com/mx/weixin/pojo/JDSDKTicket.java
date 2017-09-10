package com.mx.weixin.pojo;

import com.mx.weixin.util.JsapiTicketUtil;
import com.mx.weixin.util.WeixinSignUtil;

/**
 * jdsdk��֤��Ϣ��װ
 * @author zw
 *
 */
public class JDSDKTicket {
	private String appId = WeixinSignUtil.AppID;//������appid
	private String timestamp = JsapiTicketUtil.timestamp;//ʱ���
	private String nonceStr = JsapiTicketUtil.noncestr;//����ַ���
	private String signature;//ǩ��
	private String jsApiList = "'checkJsApi'";//api�б�
	
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
