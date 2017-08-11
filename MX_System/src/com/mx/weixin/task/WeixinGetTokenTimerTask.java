package com.mx.weixin.task;

import java.util.TimerTask;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mx.weixin.menu.MenuManager;
import com.mx.weixin.pojo.*;
import com.mx.weixin.util.JsapiTicketUtil;
import com.mx.weixin.util.TokenUtil;
import com.mx.weixin.util.WeixinSignUtil;

/**
* ����: WeixinGetTokenTimerTask </br>
* ����: Tokenˢ�¶�ʱ�� </br>
* ������Ա�� wulm </br>
* ����ʱ�䣺  2017.6.2 </br>
* �����汾��V1.0  </br>
 */

@Component
public class WeixinGetTokenTimerTask{

	//ȫ��token���󣬿�ͨ��WeixinGetTokenTimerTask.tokenֱ�ӷ���
	public static Token token;
	public static JsapiTicket jsapiTicket;
	
	@Scheduled(fixedRate = 7000000) //���7000��ִ�� 
	public void runTimerTask() {
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
