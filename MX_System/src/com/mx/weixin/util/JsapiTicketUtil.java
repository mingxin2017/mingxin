package com.mx.weixin.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.mx.weixin.pojo.JsapiTicket;
import com.mx.weixin.pojo.Token;
import com.mx.weixin.task.WeixinGetTokenTimerTask;

/**
 * ���ɹ��ں����ڵ���΢��JS�ӿڵ���ʱƱ��
 * @author zw
 *
 */
public class JsapiTicketUtil {
	private static Logger log = LoggerFactory.getLogger(CommonUtil.class);
	public final static String noncestr = "Wm3WZYTPz0wzccnW";//����ַ���
	public final static String timestamp = "1414587457";//ʱ���
	/**
	 * ����token��ȡ��ʱƱ��
	 * @param token
	 * @return
	 */
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
	/**
	 * ����ǩ���㷨
	 */
	public static String signatureCreateUtil(String url){
		// 1.��jsapiTicket��noncestr��timestamp��url�ĸ����������ֵ������򣬶����д�ǩ�����������ֶ�����ASCII ���С���������ֵ���
        String[] arr = new String[] { "jsapi_ticket="+WeixinGetTokenTimerTask.jsapiTicket.getTicket(), "noncestr="+noncestr, "timestamp="+timestamp, "url="+url};
        // 2. ʹ��URL��ֵ�Եĸ�ʽ����key1=value1&key2=value2����ƴ�ӳ��ַ���
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
        	if(i == arr.length-1){
        		content.append(arr[i]);
        	}else{
        		content.append(arr[i]).append("&");
        	}
        }
        log.info(content.toString());
        MessageDigest md = null;
        String tmpStr = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            // ���ĸ������ַ���ƴ�ӳ�һ���ַ�������sha1����
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = WeixinSignUtil.byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        content = null;
        String signature = tmpStr;
		return signature;
	}
}
