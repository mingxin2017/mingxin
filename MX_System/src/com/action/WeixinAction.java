package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

import com.bean.MxNewsData;
import com.service.IWeixinNewsService;
import com.service.IWeixinService;
import com.weixin.pojo.SNSUserInfo;
import com.weixin.pojo.WeixinOauth2Token;
import com.weixin.pojo.WeixinUserInfo;
import com.weixin.task.WeixinGetTokenTimerTask;
import com.weixin.util.MessageUtil;
import com.weixin.util.OAuth2TokenUtil;
import com.weixin.util.ResultUtil;
import com.weixin.util.WeixinSignUtil;
import com.weixin.util.WeixinUtil;

public class WeixinAction { 

	private static final long serialVersionUID = -1627548805862485475L;

	private IWeixinService weixinService;
	private IWeixinNewsService weixinNewsService;//微信新闻类业务
	
	public IWeixinService getWeixinService() {
		return weixinService;
	}

	public IWeixinNewsService getWeixinNewsService() {
		return weixinNewsService;
	}

	public void setWeixinNewsService(IWeixinNewsService weixinNewsService) {
		this.weixinNewsService = weixinNewsService;
	}

	public void setWeixinService(IWeixinService weixinService) {
		this.weixinService = weixinService;
	}
	

	/**
	 * 　　*微信action中的处理方法 　　
	 */
	public String execute() throws Exception {
        System.out.println("exec");   
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
		
		//返回参数
		String respParams= null;
//		if(1==1){
//			String respXml = weixinService.processRequest(request);
//			return "notMyUser";
//		}
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
		return respParams;
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

	
	//鸣心时报乡村时报主页跳转方法
	public String getCountryNews(){
		return "countryNews";
	}
	
	
	//鸣心时报学校时报主页跳转方法
	public String getSchoolNews(){
			return "schoolNews";
	}
	
	//未关注进入公众号
	public String getNotMyUser(){
			return "notMyUser";
	}
	
	//新闻投稿页面
	public String loadAddNews(){
		System.out.println("getadd");
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
			//获取code
			String code = request.getParameter("code");
			String state = request.getParameter("state");
			// 设置要传递的参数
			System.out.println(code);
			System.out.println(state);
			request.setAttribute("code", code);
			request.setAttribute("state", state);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return "gotoEditNews";
	}
	
	//添加新闻
	public String addNews() throws UnsupportedEncodingException{
		//获取用户信息
		//获取参数
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		String newsHeadline = request.getParameter("headline");
		String newsLeadText = request.getParameter("leadText");
		String writer_name = request.getParameter("writer_name");
		String newsMainContent = request.getParameter("content");
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		System.out.println(newsHeadline+","+newsLeadText+","+writer_name+","+newsMainContent+","+code+","+state);
		
		SNSUserInfo snsUserInfo = new SNSUserInfo();
		//通过code获取用户信息
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
			snsUserInfo = OAuth2TokenUtil.getSNSUserInfo(
					accessToken, openId);
			System.out.println(snsUserInfo.getOpenId() + ","
					+ snsUserInfo.getNickname() + ","
					+ snsUserInfo.getHeadImgUrl());
		}
		try {
		//添加新闻
		System.out.println("action");
		MxNewsData newsData = new MxNewsData();
		newsData.setNewsHeadline(newsHeadline);
		newsData.setNewsLeadText(newsLeadText);
		newsData.setWriterName(writer_name);
		newsData.setNewsMainContent(newsMainContent);
		//根据openid和用户状态为有效获取userid
		newsData.setNewsWriterId(weixinNewsService.getUser(snsUserInfo.getOpenId()).getUserId());
		System.out.println(newsData.toString());
		weixinNewsService.addNews(newsData);
		System.out.println("end");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "success");
		ResultUtil.toJson(ServletActionContext.getResponse(), map);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//新闻投稿页面
	public String getResultPage(){
		return "resultPage";
	}
	
}
