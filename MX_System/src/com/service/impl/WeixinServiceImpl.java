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
		System.out.println("΢���û���������");
		// xml��ʽ����Ϣ����
        String respXml = null;
        // Ĭ�Ϸ��ص��ı���Ϣ����
        String respContent = "δ֪����Ϣ���ͣ�";
        try {
        	
            // ����parseXml��������������Ϣ
            Map<String, String> requestMap = MessageUtil.parseXml(request);
            
            // ���ͷ��ʺ�openid
            String fromUserName = requestMap.get("FromUserName");
            //System.out.println("���ͷ��˺ţ�"+fromUserName);
            
            // ������΢�ź�
            String toUserName = requestMap.get("ToUserName");
            //System.out.println("������΢�źţ�"+toUserName);
            
            // ��Ϣ����
            String msgType = requestMap.get("MsgType");

            // �ظ��ı���Ϣ
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

            // �ı���Ϣ
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
            	String content=requestMap.get("Content");
            	if("1".equals(content)){
            		respContent = "��Ͷ�Ÿ��˼�����mingxin2017@yahoo.com�����ǽ�����������ϵ����������ڴ���ļ��룡";
            	}else if("2".equals(content)){
            		respContent = "���Ͷ������ϵ18888888888�����Ľ���ֿ����������";
            	}else if("3".equals(content)){
            		respContent = "�����Ļ����޹�˾��ַ�������سǶ��ֵ�����·8�ţ���������352200";
            	}else{
            		respContent = "�ظ�����,��л�������ĵĹ�ע��";
            	}
                
                //���Ի�ȡ�û���ϸ��Ϣ
                WeixinUserInfo wui=WeixinUtil.getUserInfo(WeixinGetTokenTimerTask.token.getAccessToken(), fromUserName);
                System.out.println(wui.getNickname());
                System.out.println(wui.getSex());
                System.out.println(wui.getCity());
                System.out.println(wui.getOpenId());
                System.out.println(wui.getHeadImgUrl());
                System.out.println(wui.getProvince());
                
            }
            // ͼƬ��Ϣ
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                respContent = "�����͵���ͼƬ��Ϣ��";
            }
            // ������Ϣ
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "�����͵���������Ϣ��";
            }
            // ��Ƶ��Ϣ
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
                respContent = "�����͵�����Ƶ��Ϣ��";
            }
            // ��Ƶ��Ϣ
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_SHORTVIDEO)) {
                respContent = "�����͵���С��Ƶ��Ϣ��";
            }
            // ����λ����Ϣ
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                respContent = "�����͵��ǵ���λ����Ϣ��";
            }
            // ������Ϣ
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                respContent = "�����͵���������Ϣ��";
            }
            // �¼�����
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                // �¼�����
                String eventType = requestMap.get("Event");
                // ��ע
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                    respContent = "���ڵȵ��㣡��";
                    
                    //�û���ע�󣬼��ɳ�Ϊϵͳ��ͨ�û�
                    
                    
                }
                // ȡ����ע
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                    // TODO ȡ�����ĺ��û��������յ������˺ŷ��͵���Ϣ����˲���Ҫ�ظ�
                }
                // ɨ���������ά��
                else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
                    // TODO ����ɨ���������ά���¼�
                	respContent = "ɨ���˶�ά�룡";
                }
                // �ϱ�����λ��
                else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
                    // TODO �����ϱ�����λ���¼�
                	respContent = "�ϱ�����λ�ã�";
                }
                // �Զ���˵�
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    // TODO ����˵�����¼�
                	respContent = "�˵�����¼���";
                	String eventKey=requestMap.get("EventKey");
                	System.out.println("eventKeyֵΪ"+ requestMap.get("EventKey"));
                	
                	if(eventKey!=null&&eventKey.equals("35")){
                		respContent="�ظ�����1��������Ƹ\r\n\r\n�ظ�����2�����Ĺ��\r\n\r\n�ظ�����3����ϵ��ַ";
                	}
                }
            }
            // �����ı���Ϣ������
            textMessage.setContent(respContent);
            // ���ı���Ϣ����ת����xml
            respXml = MessageUtil.messageToXml(textMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respXml;
    
	}

}
