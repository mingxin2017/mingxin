package com.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

import com.service.IWeixinService;
import com.weixin.pojo.SNSUserInfo;
import com.weixin.pojo.WeixinOauth2Token;
import com.weixin.util.OAuth2TokenUtil;
import com.weixin.util.WeixinSignUtil;

public class WeixinAction { 

	private static final long serialVersionUID = -1627548805862485475L;

	private IWeixinService weixinService;

	public IWeixinService getWeixinService() {
		return weixinService;
	}

	public void setWeixinService(IWeixinService weixinService) {
		this.weixinService = weixinService;
	}

	/**
	 * ����*΢��action�еĴ����� ����
	 */
	public String execute() throws Exception {

		// ��������Ӧ�ı��������ΪUTF-8����ֹ�������룩
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// ���ղ���΢�ż���ǩ���� ʱ����������
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");

		// ����ַ���
		String echostr = request.getParameter("echostr");
		// System.out.println(signature+"...............................");
		PrintWriter out = response.getWriter();

		// ����У��
		if (WeixinSignUtil.checkSignature(signature, timestamp, nonce)) {
			String method = ServletActionContext.getRequest().getMethod();
			if (method.equals("POST")) {
				// ���ú��ķ�������մ�������
				String respXml = weixinService.processRequest(request);
				out.print(respXml);
			} else {
				out.print(echostr);
			}

		}
		out.close();
		out = null;
		return null;
	}

	public String getWebAccessToken() throws ServletException, IOException {

		// ��������Ӧ�ı��������ΪUTF-8����ֹ�������룩
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// �û�ͬ����Ȩ���ܻ�ȡ��code
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		if (!"authdeny".equals(code)) {
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
			System.out.println(snsUserInfo.getOpenId() + ","
					+ snsUserInfo.getNickname() + ","
					+ snsUserInfo.getHeadImgUrl());
			// ����Ҫ���ݵĲ���
			request.setAttribute("snsUserInfo", snsUserInfo);
			request.setAttribute("state", state);
		}
		// ��ת��index.jsp
		//request.getRequestDispatcher("login.jsp").forward(request, response);

		return "weixinHome";
	}

	
	//����ʱ����ҳ��ת����
	public String getCountryNews(){
		return "countryNews";
	}
	
	
	//����ʱ����ҳ��ת����
	public String getSchoolNews(){
			return "schoolNews";
	}
	
	
}
