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
 * 生成公众号用于调用微信JS接口的临时票据
 * @author zw
 *
 */
public class JsapiTicketUtil {
	private static Logger log = LoggerFactory.getLogger(CommonUtil.class);
	public final static String noncestr = "Wm3WZYTPz0wzccnW";//随机字符串
	public final static String timestamp = "1414587457";//时间戳
	/**
	 * 根据token获取临时票据
	 * @param token
	 * @return
	 */
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
	/**
	 * 生成签名算法
	 */
	public static String signatureCreateUtil(String url){
		// 1.将jsapiTicket、noncestr、timestamp、url四个参数进行字典序排序，对所有待签名参数按照字段名的ASCII 码从小到大排序（字典序）
        String[] arr = new String[] { "jsapi_ticket="+WeixinGetTokenTimerTask.jsapiTicket.getTicket(), "noncestr="+noncestr, "timestamp="+timestamp, "url="+url};
        // 2. 使用URL键值对的格式（即key1=value1&key2=value2…）拼接成字符串
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
            // 将四个参数字符串拼接成一个字符串进行sha1加密
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
