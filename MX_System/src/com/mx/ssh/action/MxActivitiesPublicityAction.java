package com.mx.ssh.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import com.mx.ssh.bean.MxActivitiesPublicityContent;
import com.mx.ssh.bean.MxActivitiesPublicityData;
import com.mx.ssh.service.IActivitiesPublicityService;
import com.mx.weixin.pojo.SNSUserInfo;
import com.mx.weixin.util.WeixinSignUtil;
import com.mx.weixin.util.WeixinUtil;


@Controller
//控制层的Spring注解
@Scope("prototype")
//支持多例
@ParentPackage("sys-default")
//表示struts继承的父包
@Namespace(value = "/activitiesPublicity")
//表示当前Action所在命名空间
public class MxActivitiesPublicityAction {

	@Autowired// spring扫描注入
	private IActivitiesPublicityService activitiesPublicityService;

	
	/**
	 * 获取校友会宣传文章列表
	 */
	@Action(value = "getActivitiesPublicityList", results = { @Result(name = "ActivitiesPublicityList", location = "/WeixinPages/ActivitiesPublicity/ActivitiesPublicityList.jsp") })
	public String getActivitiesPublicityList() {

		HttpServletRequest request = ServletActionContext.getRequest();
		
		/**
		 * 此方法验证微信用户是否已经关注公众号
		 * 需在struts.xml中配置noFucus和error的响应页面
		 */
		
		SNSUserInfo userInfo=WeixinUtil.validateWeixinWebUser(request);

		String activitiesType = request.getParameter("type");
		List<MxActivitiesPublicityData> activitiesPublicityList = activitiesPublicityService
				.getActivitiesPublicityByType(Integer.parseInt(activitiesType));

		if (activitiesPublicityList != null && activitiesType != null) {
			request.getSession().setAttribute("activitiesPublicityList",
					activitiesPublicityList);
			request.getSession().setAttribute("activitiesType",
					Integer.parseInt(activitiesType));
			request.getSession().setAttribute("appID",WeixinSignUtil.AppID);
			request.getSession().setAttribute("serverUrl",WeixinSignUtil.serverUrl);
		}
		return "ActivitiesPublicityList";
	}

	/**
	 *
	 * 跳转到活动宣传文章详细内容页
	 * @return
	 */
	@Action(value = "gotoActivitiesDetail", results = { @Result(name = "ActivitiesPublicityDetail", location = "/WeixinPages/ActivitiesPublicity/ActivitiesPublicityDetail.jsp") })
	public String gotoActivitiesDetail(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		int pdID=Integer.parseInt(request.getParameter("publicityDataId"));
		MxActivitiesPublicityContent apd=activitiesPublicityService.getActivitiesPublicityContent(pdID);
			
					request.getSession().setAttribute("ActivitiesPublicityDetail",apd);
				
		return "ActivitiesPublicityDetail";
	}
	
	
	@Action(value = "gotoArtEditor", results = { @Result(name = "ArtEditor", location = "/WeixinPages/ActivitiesPublicity/ActivitiesPublicityDetail.jsp") })
	public String gotoArtEditor(){
		return "ArtEditor";
	}
	
	
}
