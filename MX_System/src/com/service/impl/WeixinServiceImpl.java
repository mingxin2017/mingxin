package com.service.impl;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.weixin.message.resp.TextMessage;
import com.weixin.pojo.SNSUserInfo;
import com.weixin.pojo.WeixinOauth2Token;
import com.weixin.pojo.WeixinUserInfo;
import com.weixin.task.WeixinGetTokenTimerTask;
import com.weixin.util.MessageUtil;
import com.weixin.util.OAuth2TokenUtil;
import com.weixin.util.WeixinSignUtil;
import com.weixin.util.WeixinUtil;

public class WeixinServiceImpl implements com.service.IWeixinService{

	public String processRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		System.out.println("微信用户发出请求");
		// xml格式的消息数据
        String respXml = null;
        // 默认返回的文本消息内容
        String respContent = "未知的消息类型！";
        try {
        	
            // 调用parseXml方法解析请求消息
            Map<String, String> requestMap = MessageUtil.parseXml(request);
            
            /* 用户同意网页授权后，能获取到code和estate*/
    		String code = requestMap.get("code")!=null?requestMap.get("code"):"";
    		String state = requestMap.get("state")!=null?requestMap.get("state"):"";
    		/* 用户同意网页授权后，能获取到code*/
            
    		// 用户同意授权
    		if (!"authdeny".equals(code)&&!code.isEmpty()) {
    			// 获取网页授权access_token
    			WeixinOauth2Token weixinOauth2Token = OAuth2TokenUtil
    					.getOauth2AccessToken(WeixinSignUtil.AppID,
    							WeixinSignUtil.AppSecret, code);
    			// 网页授权接口访问凭证
    			String accessToken = weixinOauth2Token.getAccessToken();
    			// 用户标识
    			String openId = weixinOauth2Token.getOpenId();
    			// 获取用户信息
    			SNSUserInfo snsUserInfo = OAuth2TokenUtil.getSNSUserInfo(
    					accessToken, openId);

    			// 设置要传递的参数
    			request.setAttribute("snsUserInfo", snsUserInfo);
    			request.setAttribute("state", state);
    		}
    		
    		
            // 发送方帐号openid
            String fromUserName = requestMap.get("FromUserName");
            //System.out.println("发送方账号："+fromUserName);
            
            // 开发者微信号
            String toUserName = requestMap.get("ToUserName");
            //System.out.println("开发者微信号："+toUserName);
            
            // 消息类型
            String msgType = requestMap.get("MsgType");

            // 回复文本消息
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

            // 文本消息
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                respContent = "您发送的是文本消息！";
                
                //测试获取用户详细信息
                WeixinUserInfo wui=WeixinUtil.getUserInfo(WeixinGetTokenTimerTask.token.getAccessToken(), fromUserName);
                System.out.println(wui.getNickname());
                System.out.println(wui.getSex());
                System.out.println(wui.getCity());
                System.out.println(wui.getOpenId());
                System.out.println(wui.getHeadImgUrl());
                System.out.println(wui.getProvince());
            }
            // 图片消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                respContent = "您发送的是图片消息！";
            }
            // 语音消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "您发送的是语音消息！";
            }
            // 视频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
                respContent = "您发送的是视频消息！";
            }
            // 视频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_SHORTVIDEO)) {
                respContent = "您发送的是小视频消息！";
            }
            // 地理位置消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                respContent = "您发送的是地理位置消息！";
            }
            // 链接消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                respContent = "您发送的是链接消息！";
            }
            // 事件推送
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                // 事件类型
                String eventType = requestMap.get("Event");
                // 关注
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                    respContent = "谢谢您的关注！";
                }
                // 取消关注
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                    // TODO 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
                }
                // 扫描带参数二维码
                else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
                    // TODO 处理扫描带参数二维码事件
                	respContent = "扫描了二维码！";
                }
                // 上报地理位置
                else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
                    // TODO 处理上报地理位置事件
                	respContent = "上报地理位置！";
                }
                // 自定义菜单
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    // TODO 处理菜单点击事件
                	respContent = "菜单点击事件！";
                }
            }
            // 设置文本消息的内容
            textMessage.setContent(respContent);
            // 将文本消息对象转换成xml
            respXml = MessageUtil.messageToXml(textMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respXml;
    
	}

}
