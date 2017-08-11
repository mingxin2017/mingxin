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
* 类名: WeixinGetTokenTimerTask </br>
* 描述: Token刷新定时器 </br>
* 开发人员： wulm </br>
* 创建时间：  2017.6.2 </br>
* 发布版本：V1.0  </br>
 */

@Component
public class WeixinGetTokenTimerTask{

	//全局token对象，可通过WeixinGetTokenTimerTask.token直接访问
	public static Token token;
	public static JsapiTicket jsapiTicket;
	
	@Scheduled(fixedRate = 7000000) //间隔7000秒执行 
	public void runTimerTask() {
		System.out.println("微信token动态更新操作");
		//刷新全局变量token，时间配置在applicationContext.xml中
		token=TokenUtil.getToken(WeixinSignUtil.AppID, WeixinSignUtil.AppSecret);
		
		System.out.println("token refresh:"+token.getAccessToken());
		System.out.println("expiresIn:"+token.getExpiresIn());
		
		System.out.println("微信临时票据jsapi_ticket动态更新操作");
		jsapiTicket=JsapiTicketUtil.getJsapiTicket(token);
		System.out.println("jsapiTicket:"+jsapiTicket.getTicket());
		System.out.println("expiresIn:"+jsapiTicket.getExpires_in());
		
		MenuManager.createMenu();//2小时创建一次动态菜单
	}

}
