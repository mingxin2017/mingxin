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
	 * 　　*微信action中的处理方法 　　
	 */
	public String execute() throws Exception {

		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// 接收参数微信加密签名、 时间戳、随机数
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");

		// 随机字符串
		String echostr = request.getParameter("echostr");
		// System.out.println(signature+"...............................");
		PrintWriter out = response.getWriter();

		// 请求校验
		if (WeixinSignUtil.checkSignature(signature, timestamp, nonce)) {
			String method = ServletActionContext.getRequest().getMethod();
			if (method.equals("POST")) {
				// 调用核心服务类接收处理请求
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

		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// 用户同意授权后，能获取到code
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		if (!"authdeny".equals(code)) {
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
			System.out.println(snsUserInfo.getOpenId() + ","
					+ snsUserInfo.getNickname() + ","
					+ snsUserInfo.getHeadImgUrl());
			// 设置要传递的参数
			request.setAttribute("snsUserInfo", snsUserInfo);
			request.setAttribute("state", state);
		}
		// 跳转到index.jsp
		//request.getRequestDispatcher("login.jsp").forward(request, response);

		return "weixinHome";
	}

	
	//鸣心时报主页跳转方法
	public String getCountryNews(){
		return "countryNews";
	}
	
	
	//鸣心时报主页跳转方法
	public String getSchoolNews(){
			return "schoolNews";
	}
	
	
}
