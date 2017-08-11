package com.mx.ssh.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mx.ssh.bean.MxNewsType;
import com.mx.ssh.bean.MxRegion;
import com.mx.ssh.bean.MxSchools;
import com.mx.ssh.service.IWeixinNewsService;
import com.mx.ssh.service.IWeixinService;
import com.mx.ssh.service.impl.WeixinServiceImpl;
import com.mx.weixin.pojo.SNSUserInfo;
import com.mx.weixin.pojo.WeixinOauth2Token;
import com.mx.weixin.task.WeixinGetTokenTimerTask;
import com.mx.weixin.util.MxKeyValue;
import com.mx.weixin.util.OAuth2TokenUtil;
import com.mx.weixin.util.ResultUtil;
import com.mx.weixin.util.WeixinSignUtil;

@Controller//控制层的Spring注解
@Scope("prototype")//支持多例
@ParentPackage("default-package")//weixinAction不需要做登录校验，只需要做全局错误校验
@Namespace(value="/weixin") //表示当前Action所在命名空间 
public class WeixinAction { 

	private static final long serialVersionUID = -1627548805862485475L;

	@Autowired
	private IWeixinService weixinService;
	
	@Autowired
	private IWeixinNewsService weixinNewsService;//微信新闻类业务
	
	/**
	 * 　　*微信action中的处理方法 　　
	 */
	
	@Action(value = "defaultMethod")
	public String defaultMethod() throws Exception {
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
		
		System.out.println(signature+','+timestamp+','+nonce);
		
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

	
	@Action(value = "getWebAccessToken")
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
	public String loadEditNews(){
		System.out.println("getadd");
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
			//获取code
/*			String code = request.getParameter("code");
			String state = request.getParameter("state");*/
			//获取调用jssdk的临时票据
			String jsapi_ticket = WeixinGetTokenTimerTask.jsapiTicket.getTicket();
			//时间戳、随机数
			String noncestr = UUID.randomUUID().toString().replace("-", "").substring(0, 16);
			String timestamp = Long.toString(System.currentTimeMillis() / 1000);
			//String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb235c46c4c2740a9&redirect_uri=http://d1a7069951.iask.in/MX_System/weixin!loadAddNews.action&response_type=code&scope=snsapi_userinfo&state=123";
			String url = "http://d1a7069951.iask.in/MX_System/weixin!loadAddNews.action";
			String string1 = "jsapi_ticket="+jsapi_ticket+
							 "&noncestr="+noncestr+
							 "&timestamp="+timestamp+
							 "&url="+url;
			String signature = DigestUtils.shaHex(string1);
			System.out.println(string1);
			System.out.println(signature);
/*			System.out.println(code);
			System.out.println(state);*/
			// 设置要传递的参数
			request.setAttribute("signature", signature);
			request.setAttribute("timestamp", timestamp);
			request.setAttribute("noncestr", noncestr);
			
/*			request.setAttribute("code", code);
			request.setAttribute("state", state);*/
			request.setAttribute("server_app_id", WeixinSignUtil.AppID);
			
			//获取新闻类型列表
			List<MxNewsType> msNewsType = weixinNewsService.getNewsType();
			request.setAttribute("msNewsType", msNewsType);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return "gotoEditNews";
	}
	
	//级联学校和乡村参数返回
	public String loadKeyValue(){
		System.out.println("loadKeyValue");
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
			//获取newsTypeId
			String newsTypeId = request.getParameter("newsTypeId");
			//封装成对象
			List<MxKeyValue> list = new ArrayList<MxKeyValue>();
			if(newsTypeId.equals("4")){
				List<MxSchools> schools = weixinNewsService.getSchools();
				for(MxSchools sch:schools){
					System.out.println(sch.toString());
					MxKeyValue mod = new MxKeyValue();
					mod.setKey(sch.getSchoolId());
					mod.setValue(sch.getSchoolName());
					list.add(mod);
				}
			}
			if(newsTypeId.equals("5")){
				List<MxRegion> regions = weixinNewsService.getRegion();
				for(MxRegion reg:regions){
					System.out.println(reg.toString());
					MxKeyValue mod = new MxKeyValue();
					mod.setKey(reg.getRegionId());
					mod.setValue(reg.getRegionName());
					list.add(mod);
				}
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("data", list);
			try {
				ResultUtil.toJson(ServletActionContext.getResponse(), map);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//添加新闻
	public void addNews() throws UnsupportedEncodingException{
		System.out.println("addNews");
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		String serverId = request.getParameter("serverId");
		System.out.println(serverId);
		/*//获取用户信息
		//获取参数 
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		String newsHeadline = request.getParameter("headline");
		String newsLeadText = request.getParameter("leadText");
		String writer_name = request.getParameter("writer_name");
		String newsMainContent = request.getParameter("content");
		String newsTypeId = request.getParameter("newsTypeId");
		String subId = request.getParameter("subId");
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
		newsData.setNewsTypeId(new Integer(newsTypeId));
		if(newsTypeId.equals("4")){
			newsData.setSchoolId(new Integer(subId));
		}else if(newsTypeId.equals("5")){
			newsData.setRegionId(new Integer(subId));
		}
		//根据openid和用户状态为有效获取userid
		newsData.setNewsWriterId(weixinNewsService.getUser(snsUserInfo.getOpenId()).getUserId());
		System.out.println(newsData.toString());
		//添加新闻内容从表
		MxNewsContent newsContent = new MxNewsContent();
		newsContent.setNewsHeadline(newsHeadline);
		newsContent.setNewsLeadText(newsLeadText);
		newsContent.setNewsMainContent(newsMainContent);
		weixinNewsService.addNews(newsData,newsContent);
		
		//下载图片到本地服务器
		
		

		System.out.println("end");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "success");
		ResultUtil.toJson(ServletActionContext.getResponse(), map);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;*/
	}
	
	
	//新闻投稿页面
	public String getResultPage(){
		return "resultPage";
	}
	
	//投稿列表页面
	public String applyNewsList(){
		return "applyNewsList";
	}
	
}
