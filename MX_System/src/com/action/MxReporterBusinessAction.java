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
 * 小记者相关业务处理
 * 新闻类、小记者管理（新闻、团队、评分）
 * @author zw
 *
 */
public class MxReporterBusinessAction {
	
	private static final long serialVersionUID = -1627548805862485475L;

	private IWeixinNewsService weixinNewsService;//微信新闻类业务
    private IReporterBusinessService reporterBusinessService;//小记者业务
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
	//获取小记者申请页面
	public String loadReporterApply(){
		System.out.println("apply action");
		//获取code和state传回前端
		HttpServletRequest request = ServletActionContext.getRequest();
			try {
				request.setCharacterEncoding("UTF-8");
				//获取code
				String code = request.getParameter("code");
				String state = request.getParameter("state");
				// 设置要传递的参数
				request.setAttribute("code", code);
				request.setAttribute("state", state);
				//小记者团队查询返回
				request.setAttribute("MxUsersReporterTeam", reporterBusinessService.getReporterTeams());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		return "ReporterApply";
	}
	//获取小记者管理页面
	public String loadReporterManage(){
		System.out.println("manage action");
		//获取code和state传回前端
		HttpServletRequest request = ServletActionContext.getRequest();
			try {
				request.setCharacterEncoding("UTF-8");
				//获取code
				String code = request.getParameter("code");
				String state = request.getParameter("state");
				// 设置要传递的参数
				request.setAttribute("code", code);
				request.setAttribute("state", state);
				//code查询用户返回
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
				request.setAttribute("weixin_userInfo", snsUserInfo);
				//是否已关注用户
				Integer userId = weixinNewsService.getUser(snsUserInfo.getOpenId()).getUserId();
				System.out.println("-----------a-----------");
				System.out.println(userId.equals(""));
				System.out.println("-----------b-----------");
				//小记者团队查询返回
				request.setAttribute("userId", userId);
				request.setAttribute("MxUsersReporterTeam", reporterBusinessService.getTeamByUserId(userId));
				//分数查询返回
				request.setAttribute("MxUsersReporterScore", reporterBusinessService.getScoreByUserId(userId));
			    //新闻查询返回
				request.setAttribute("MxNewsData", reporterBusinessService.getNewsDataByUserId(userId));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		return "ReporterManage";
	}
	
	//小记者申请
	public String reporterApply(){
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
			//获取code
			String code = request.getParameter("code");
			String state = request.getParameter("state");
			
			String value = request.getParameter("value");
			System.out.println(value);
//			String teamId = request.getParameter("teamId");
//			System.out.println(teamId);
			/*//获取用户信息
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
	
	//新闻页面获取
	public String getNewsPage(){
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
			//校验用户
			//获取newsId
			String newsId = request.getParameter("newsId");
			//根据新闻id获取新闻
			request.setAttribute("MxNewsData", reporterBusinessService.getNewsDataByNewsId(new Integer(newsId)));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}	
		return "NewsPage";
	}
}
