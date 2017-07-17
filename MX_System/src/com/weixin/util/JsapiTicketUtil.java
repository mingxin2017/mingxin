package com.weixin.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.weixin.pojo.JsapiTicket;
import com.weixin.pojo.Token;

/**
 * 生成公众号用于调用微信JS接口的临时票据
 * @author zw
 *
 */
public class JsapiTicketUtil {
	private static Logger log = LoggerFactory.getLogger(CommonUtil.class);
	public static  JsapiTicket getJsapiTicket(Token token){
		JsapiTicket jsapiTicket = null;
		String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
		String requestUrl = url.replace("ACCESS_TOKEN", token.getAccessToken());
		
		// 发起GET请求获取凭证
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
		
        if (null != jsonObject) {
            try {
            	jsapiTicket = new JsapiTicket();
            	jsapiTicket.setTicket(jsonObject.getString("ticket"));
            	jsapiTicket.setExpires_in(jsonObject.getInt("expires_in"));
            } catch (JSONException e) {
            	jsapiTicket = null;
                // 获取jsapiTicket失败
                log.error("获取jsapiTicket失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return jsapiTicket;
	}
}
