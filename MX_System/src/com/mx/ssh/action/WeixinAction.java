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

@Controller//���Ʋ��Springע��
@Scope("prototype")//֧�ֶ���
@ParentPackage("default-package")//weixinAction����Ҫ����¼У�飬ֻ��Ҫ��ȫ�ִ���У��
@Namespace(value="/weixin") //��ʾ��ǰAction���������ռ� 
public class WeixinAction { 

	private static final long serialVersionUID = -1627548805862485475L;

	@Autowired
	private IWeixinService weixinService;
	
	@Autowired
	private IWeixinNewsService weixinNewsService;//΢��������ҵ��
	
	/**
	 * ����*΢��action�еĴ����� ����
	 */
	
	@Action(value = "defaultMethod")
	public String defaultMethod() throws Exception {
        System.out.println("exec");   
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
		
		System.out.println(signature+','+timestamp+','+nonce);
		
		//���ز���
		String respParams= null;
//		if(1==1){
//			String respXml = weixinService.processRequest(request);
//			return "notMyUser";
//		}
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
		return respParams;
	}

	
	@Action(value = "getWebAccessToken")
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

	
	//����ʱ�����ʱ����ҳ��ת����
	public String getCountryNews(){
		return "countryNews";
	}
	
	
	//����ʱ��ѧУʱ����ҳ��ת����
	public String getSchoolNews(){
			return "schoolNews";
	}
	
	//δ��ע���빫�ں�
	public String getNotMyUser(){
			return "notMyUser";
	}
	
	//����Ͷ��ҳ��
	public String loadEditNews(){
		System.out.println("getadd");
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
			//��ȡcode
/*			String code = request.getParameter("code");
			String state = request.getParameter("state");*/
			//��ȡ����jssdk����ʱƱ��
			String jsapi_ticket = WeixinGetTokenTimerTask.jsapiTicket.getTicket();
			//ʱ����������
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
			// ����Ҫ���ݵĲ���
			request.setAttribute("signature", signature);
			request.setAttribute("timestamp", timestamp);
			request.setAttribute("noncestr", noncestr);
			
/*			request.setAttribute("code", code);
			request.setAttribute("state", state);*/
			request.setAttribute("server_app_id", WeixinSignUtil.AppID);
			
			//��ȡ���������б�
			List<MxNewsType> msNewsType = weixinNewsService.getNewsType();
			request.setAttribute("msNewsType", msNewsType);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return "gotoEditNews";
	}
	
	//����ѧУ������������
	public String loadKeyValue(){
		System.out.println("loadKeyValue");
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
			//��ȡnewsTypeId
			String newsTypeId = request.getParameter("newsTypeId");
			//��װ�ɶ���
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
	
	//�������
	public void addNews() throws UnsupportedEncodingException{
		System.out.println("addNews");
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		String serverId = request.getParameter("serverId");
		System.out.println(serverId);
		/*//��ȡ�û���Ϣ
		//��ȡ���� 
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
		//ͨ��code��ȡ�û���Ϣ
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
			snsUserInfo = OAuth2TokenUtil.getSNSUserInfo(
					accessToken, openId);
			System.out.println(snsUserInfo.getOpenId() + ","
					+ snsUserInfo.getNickname() + ","
					+ snsUserInfo.getHeadImgUrl());
		}
		try {
		//������� 
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
		//����openid���û�״̬Ϊ��Ч��ȡuserid
		newsData.setNewsWriterId(weixinNewsService.getUser(snsUserInfo.getOpenId()).getUserId());
		System.out.println(newsData.toString());
		//����������ݴӱ�
		MxNewsContent newsContent = new MxNewsContent();
		newsContent.setNewsHeadline(newsHeadline);
		newsContent.setNewsLeadText(newsLeadText);
		newsContent.setNewsMainContent(newsMainContent);
		weixinNewsService.addNews(newsData,newsContent);
		
		//����ͼƬ�����ط�����
		
		

		System.out.println("end");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "success");
		ResultUtil.toJson(ServletActionContext.getResponse(), map);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;*/
	}
	
	
	//����Ͷ��ҳ��
	public String getResultPage(){
		return "resultPage";
	}
	
	//Ͷ���б�ҳ��
	public String applyNewsList(){
		return "applyNewsList";
	}
	
}
