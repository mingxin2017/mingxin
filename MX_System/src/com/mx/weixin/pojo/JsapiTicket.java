package com.mx.weixin.pojo;
/**
 * 调用api时的临时票据
 * @author zw
 *
 */
public class JsapiTicket {
	private String ticket;//票据
	private int expires_in;//有效时间
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public int getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
	
}
