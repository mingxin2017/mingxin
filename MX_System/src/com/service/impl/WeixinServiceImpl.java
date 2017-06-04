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
            	String content=requestMap.get("Content");
            	if("1".equals(content)){
            		respContent = "请投放个人简历至mingxin2017@yahoo.com，我们将尽快与你联系，鸣心真诚期待你的加入！";
            	}else if("2".equals(content)){
            		respContent = "广告投放请联系18888888888，鸣心将诚挚与您合作！";
            	}else if("3".equals(content)){
            		respContent = "鸣心文化有限公司地址：古田县城东街道建设路8号，邮政编码352200";
            	}else{
            		respContent = "回复有误,感谢您对鸣心的关注！";
            	}
                
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
                    respContent = "终于等到你！！";
                    
                    //用户关注后，即可成为系统普通用户
                    
                    
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
                	String eventKey=requestMap.get("EventKey");
                	System.out.println("eventKey值为"+ requestMap.get("EventKey"));
                	
                	if(eventKey!=null&&eventKey.equals("35")){
                		respContent="回复数字1：鸣心招聘\r\n\r\n回复数字2：鸣心广告\r\n\r\n回复数字3：联系地址";
                	}
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
