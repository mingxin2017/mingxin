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
 * 小记者相关业务处理
 * 新闻类、小记者管理（新闻、团队、评分）
 * @author zw
 *
 */
@Controller//控制层的Spring注解
@Scope("prototype")//支持多例
@Namespace(value="/mxReporterBusiness") //表示当前Action所在命名空间 
@ParentPackage(value = "json-default") 
public class MxReporterBusinessAction {
	
	private static final long serialVersionUID = -1627548805862485475L;
	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private IWeixinNewsService weixinNewsService;//微信新闻类业务
	
	@Autowired
    private IReporterBusinessService reporterBusinessService;//小记者业务
	
	@Autowired
	private IUserService userService;//用户
	
	//获取小记者申请页面
	@Action(value = "loadReporterApply", results = { @Result(name = "loadReporterApply", location = "/WeixinPages/Reporter/reporterApplyIndex.jsp") })
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
		return "loadReporterApply";
	}
	//获取小记者管理页面
	@Action(value = "loadReporterManage", results = { @Result(name = "loadReporterManage", location = "/WeixinPages/Reporter/reporterManageIndex.jsp") })
	public String loadReporterManage() throws UnsupportedEncodingException{
		System.out.println("manage action");
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");

		// 用户同意授权后，能获取到code
/*		String code = request.getParameter("code");
		String state = request.getParameter("state");
		if (!"authdeny".equals(code)) {
			// 获取网页授权access_token
			WeixinOauth2Token weixinOauth2Token = OAuth2TokenUtil.getOauth2AccessToken(WeixinSignUtil.AppID,WeixinSignUtil.AppSecret, code);
			// 网页授权接口访问凭证
			String accessToken = weixinOauth2Token.getAccessToken();
			// 用户标识
			String openId = weixinOauth2Token.getOpenId();
			
			// 获取用户信息
			SNSUserInfo snsUserInfo = OAuth2TokenUtil.getSNSUserInfo(accessToken, openId);
			System.out.println(snsUserInfo.getOpenId() + "," + snsUserInfo.getNickname() + "," + snsUserInfo.getHeadImgUrl());
			//用户id存储到session
			request.getSession().setAttribute("userId", snsUserInfo.getOpenId() );
		}*/
		//openid查询用户信息
		MxUsersData userInfo = userService.getUserByOpenId("oNYP41OJ_d3fR-d4O479D8jFX3-A");
		logger.info(userInfo.toString());
		Integer userId = userInfo.getUserId();
		request.getSession().setAttribute("userId", ""+userInfo.getUserId());
		//小记者团队查询返回
		request.setAttribute("userId", userInfo.getUserId());
		request.setAttribute("MxUsersReporterTeam", reporterBusinessService.getTeamByUserId(userId));
		//分数查询返回
		request.setAttribute("MxUsersReporterScore", reporterBusinessService.getScoreByUserId(userId));
	    //新闻查询返回
		request.setAttribute("MxNewsData", reporterBusinessService.getNewsDataByUserId(userId));
		return "loadReporterManage";
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
