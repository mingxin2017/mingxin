package com.mx.weixin.pojo;
/**
 * ����apiʱ����ʱƱ��
 * @author zw
 *
 */
public class JsapiTicket {
	private String ticket;//Ʊ��
	private int expires_in;//��Чʱ��
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
