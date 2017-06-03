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
            
            /* �û�ͬ����ҳ��Ȩ���ܻ�ȡ��code��estate*/
    		String code = requestMap.get("code")!=null?requestMap.get("code"):"";
    		String state = requestMap.get("state")!=null?requestMap.get("state"):"";
    		/* �û�ͬ����ҳ��Ȩ���ܻ�ȡ��code*/
            
    		// �û�ͬ����Ȩ
    		if (!"authdeny".equals(code)&&!code.isEmpty()) {
    			// ��ȡ��ҳ��Ȩaccess_token
    			WeixinOauth2Token weixinOauth2Token = OAuth2TokenUtil
    					.getOauth2AccessToken(WeixinSignUtil.AppID,
    							WeixinSignUtil.AppSecret, code);
    			// ��ҳ��Ȩ�ӿڷ���ƾ֤
    			String accessToken = weixinOauth2Token.getAccessToken();
    			// �û���ʶ
    			String openId = weixinOauth2Token.getOpenId();
    			// ��ȡ�û���Ϣ
    			SNSUserInfo snsUserInfo = OAuth2TokenUtil.getSNSUserInfo(
    					accessToken, openId);

    			// ����Ҫ���ݵĲ���
    			request.setAttribute("snsUserInfo", snsUserInfo);
    			request.setAttribute("state", state);
    		}
    		
    		
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
                respContent = "�����͵����ı���Ϣ��";
                
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
                    respContent = "лл���Ĺ�ע��";
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
