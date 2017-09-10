package com.mx.ssh.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mx.ssh.bean.MxUsersData;
import com.mx.ssh.service.IReporterBusinessService;
import com.mx.ssh.service.IUserService;
import com.mx.ssh.service.IWeixinNewsService;
import com.mx.weixin.pojo.SNSUserInfo;
import com.mx.weixin.pojo.WeixinOauth2Token;
import com.mx.weixin.util.OAuth2TokenUtil;
import com.mx.weixin.util.ResultUtil;
import com.mx.weixin.util.WeixinSignUtil;

/**
 * С�������ҵ����
 * �����ࡢС���߹������š��Ŷӡ����֣�
 * @author zw
 *
 */
@Controller//���Ʋ��Springע��
@Scope("prototype")//֧�ֶ���
@Namespace(value="/mxReporterBusiness") //��ʾ��ǰAction���������ռ� 
@ParentPackage(value = "json-default") 
public class MxReporterBusinessAction {
	
	private static final long serialVersionUID = -1627548805862485475L;
	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private IWeixinNewsService weixinNewsService;//΢��������ҵ��
	
	@Autowired
    private IReporterBusinessService reporterBusinessService;//С����ҵ��
	
	@Autowired
	private IUserService userService;//�û�
	
	//��ȡС��������ҳ��
	@Action(value = "loadReporterApply", results = { @Result(name = "loadReporterApply", location = "/WeixinPages/Reporter/reporterApplyIndex.jsp") })
	public String loadReporterApply(){
		System.out.println("apply action");
		//��ȡcode��state����ǰ��
		HttpServletRequest request = ServletActionContext.getRequest();
			try {
				request.setCharacterEncoding("UTF-8");
				//��ȡcode
				String code = request.getParameter("code");
				String state = request.getParameter("state");
				// ����Ҫ���ݵĲ���
				request.setAttribute("code", code);
				request.setAttribute("state", state);
				//С�����ŶӲ�ѯ����
				request.setAttribute("MxUsersReporterTeam", reporterBusinessService.getReporterTeams());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		return "loadReporterApply";
	}
	//��ȡС���߹���ҳ��
	@Action(value = "loadReporterManage", results = { @Result(name = "loadReporterManage", location = "/WeixinPages/Reporter/reporterManageIndex.jsp") })
	public String loadReporterManage() throws UnsupportedEncodingException{
		System.out.println("manage action");
		// ��������Ӧ�ı��������ΪUTF-8����ֹ�������룩
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");

		// �û�ͬ����Ȩ���ܻ�ȡ��code
/*		String code = request.getParameter("code");
		String state = request.getParameter("state");
		if (!"authdeny".equals(code)) {
			// ��ȡ��ҳ��Ȩaccess_token
			WeixinOauth2Token weixinOauth2Token = OAuth2TokenUtil.getOauth2AccessToken(WeixinSignUtil.AppID,WeixinSignUtil.AppSecret, code);
			// ��ҳ��Ȩ�ӿڷ���ƾ֤
			String accessToken = weixinOauth2Token.getAccessToken();
			// �û���ʶ
			String openId = weixinOauth2Token.getOpenId();
			
			// ��ȡ�û���Ϣ
			SNSUserInfo snsUserInfo = OAuth2TokenUtil.getSNSUserInfo(accessToken, openId);
			System.out.println(snsUserInfo.getOpenId() + "," + snsUserInfo.getNickname() + "," + snsUserInfo.getHeadImgUrl());
			//�û�id�洢��session
			request.getSession().setAttribute("userId", snsUserInfo.getOpenId() );
		}*/
		//openid��ѯ�û���Ϣ
		MxUsersData userInfo = userService.getUserByOpenId("oNYP41OJ_d3fR-d4O479D8jFX3-A");
		logger.info(userInfo.toString());
		Integer userId = userInfo.getUserId();
		request.getSession().setAttribute("userId", ""+userInfo.getUserId());
		//С�����ŶӲ�ѯ����
		request.setAttribute("userId", userInfo.getUserId());
		request.setAttribute("MxUsersReporterTeam", reporterBusinessService.getTeamByUserId(userId));
		//������ѯ����
		request.setAttribute("MxUsersReporterScore", reporterBusinessService.getScoreByUserId(userId));
	    //���Ų�ѯ����
		request.setAttribute("MxNewsData", reporterBusinessService.getNewsDataByUserId(userId));
		return "loadReporterManage";
	}
	
	//С��������
	public String reporterApply(){
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
			//��ȡcode
			String code = request.getParameter("code");
			String state = request.getParameter("state");
			
			String value = request.getParameter("value");
			System.out.println(value);
//			String teamId = request.getParameter("teamId");
//			System.out.println(teamId);
			/*//��ȡ�û���Ϣ
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
			
			MxUsersReporterSignUp mxUsersReporterSignUp = new MxUsersReporterSignUp();
			mxUsersReporterSignUp.setUserId(weixinNewsService.getUser(snsUserInfo.getOpenId()).getUserId());
			mxUsersReporterSignUp.setReporterTeamId(new Integer(teamId));*/
			//reporterBusinessService.reporterApply(mxUsersReporterSignUp);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("data", "666");
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
	
	//����ҳ���ȡ
	public String getNewsPage(){
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
			//У���û�
			//��ȡnewsId
			String newsId = request.getParameter("newsId");
			//��������id��ȡ����
			request.setAttribute("MxNewsData", reporterBusinessService.getNewsDataByNewsId(new Integer(newsId)));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}	
		return "NewsPage";
	}
}
