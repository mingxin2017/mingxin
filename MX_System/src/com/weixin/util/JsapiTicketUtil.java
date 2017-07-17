package com.weixin.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.weixin.pojo.JsapiTicket;
import com.weixin.pojo.Token;

/**
 * ���ɹ��ں����ڵ���΢��JS�ӿڵ���ʱƱ��
 * @author zw
 *
 */
public class JsapiTicketUtil {
	private static Logger log = LoggerFactory.getLogger(CommonUtil.class);
	public static  JsapiTicket getJsapiTicket(Token token){
		JsapiTicket jsapiTicket = null;
		String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
		String requestUrl = url.replace("ACCESS_TOKEN", token.getAccessToken());
		
		// ����GET�����ȡƾ֤
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
		
        if (null != jsonObject) {
            try {
            	jsapiTicket = new JsapiTicket();
            	jsapiTicket.setTicket(jsonObject.getString("ticket"));
            	jsapiTicket.setExpires_in(jsonObject.getInt("expires_in"));
            } catch (JSONException e) {
            	jsapiTicket = null;
                // ��ȡjsapiTicketʧ��
                log.error("��ȡjsapiTicketʧ�� errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return jsapiTicket;
	}
}
