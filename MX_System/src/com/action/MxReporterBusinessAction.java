package com.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.bean.MxUsersReporterSignUp;
import com.service.IReporterBusinessService;
import com.service.IWeixinNewsService;
import com.weixin.pojo.SNSUserInfo;
import com.weixin.pojo.WeixinOauth2Token;
import com.weixin.util.OAuth2TokenUtil;
import com.weixin.util.ResultUtil;
import com.weixin.util.WeixinSignUtil;

/**
 * С�������ҵ����
 * �����ࡢС���߹������š��Ŷӡ����֣�
 * @author zw
 *
 */
public class MxReporterBusinessAction {
	
	private static final long serialVersionUID = -1627548805862485475L;

	private IWeixinNewsService weixinNewsService;//΢��������ҵ��
    private IReporterBusinessService reporterBusinessService;//С����ҵ��
	public IWeixinNewsService getWeixinNewsService() {
		return weixinNewsService;
	}

	public void setWeixinNewsService(IWeixinNewsService weixinNewsService) {
		this.weixinNewsService = weixinNewsService;
	}
	
	public IReporterBusinessService getReporterBusinessService() {
		return reporterBusinessService;
	}

	public void setReporterBusinessService(
			IReporterBusinessService reporterBusinessService) {
		this.reporterBusinessService = reporterBusinessService;
	}
	//��ȡС��������ҳ��
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
		return "ReporterApply";
	}
	//��ȡС���߹���ҳ��
	public String loadReporterManage(){
		System.out.println("manage action");
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
				//code��ѯ�û�����
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
				request.setAttribute("weixin_userInfo", snsUserInfo);
				//�Ƿ��ѹ�ע�û�
				Integer userId = weixinNewsService.getUser(snsUserInfo.getOpenId()).getUserId();
				System.out.println("-----------a-----------");
				System.out.println(userId.equals(""));
				System.out.println("-----------b-----------");
				//С�����ŶӲ�ѯ����
				request.setAttribute("userId", userId);
				request.setAttribute("MxUsersReporterTeam", reporterBusinessService.getTeamByUserId(userId));
				//������ѯ����
				request.setAttribute("MxUsersReporterScore", reporterBusinessService.getScoreByUserId(userId));
			    //���Ų�ѯ����
				request.setAttribute("MxNewsData", reporterBusinessService.getNewsDataByUserId(userId));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		return "ReporterManage";
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
