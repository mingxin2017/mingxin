package com.weixin.task;

import java.util.TimerTask;

import com.weixin.menu.MenuManager;
import com.weixin.pojo.*;
import com.weixin.util.JsapiTicketUtil;
import com.weixin.util.TokenUtil;
import com.weixin.util.WeixinSignUtil;

/**
* ����: WeixinGetTokenTimerTask </br>
* ����: Tokenˢ�¶�ʱ�� </br>
* ������Ա�� wulm </br>
* ����ʱ�䣺  2017.6.2 </br>
* �����汾��V1.0  </br>
 */
public class WeixinGetTokenTimerTask  extends TimerTask{

	//ȫ��token���󣬿�ͨ��WeixinGetTokenTimerTask.tokenֱ�ӷ���
	public static Token token;
	public static JsapiTicket jsapiTicket;
	
	@Override
	public void run() {
		System.out.println("΢��token��̬���²���");
		//ˢ��ȫ�ֱ���token��ʱ��������applicationContext.xml��
		token=TokenUtil.getToken(WeixinSignUtil.AppID, WeixinSignUtil.AppSecret);
		
		System.out.println("token refresh:"+token.getAccessToken());
		System.out.println("expiresIn:"+token.getExpiresIn());
		
		System.out.println("΢����ʱƱ��jsapi_ticket��̬���²���");
		jsapiTicket=JsapiTicketUtil.getJsapiTicket(token);
		System.out.println("jsapiTicket:"+jsapiTicket.getTicket());
		System.out.println("expiresIn:"+jsapiTicket.getExpires_in());
		
		MenuManager.createMenu();//2Сʱ����һ�ζ�̬�˵�
	}

}
