package com.action;

import com.service.IActivitiesPublicityService;

public class MxActivitiesMySpaceAction {

	private IActivitiesPublicityService activitiesPublicityService;

	/**
	 * 　　*活动空间action中的默认处理方法 　　
	 */
	public String execute() throws Exception {

		return null;
	}

	/**
	 * 获取活动空间页面主框架
	 */
	public String gotoActivitiesMySpaceMain() {

		return "activitiesMySpaceMain";
	}
	
	/**
	 * 获取活动空间讨论内容列表
	 */
	public String getActivitiesMySpaceCommentList() {

		return "activitiesMySpaceCommentList";
	}

	/**
	 * 获取活动空间讨论内容列表
	 */
	public String getActivitiesMySpaceMaterialList() {

		return "activitiesMySpaceMaterialList";
	}
	
	/**
	 * 获取活动空间讨论内容列表
	 */
	public String getActivitiesMySpaceUsersList() {

		return "activitiesMySpaceUsersList";
	}
	
	/**
	 * 获取活动空间讨论内容列表
	 */
	public String getActivitiesMySpaceManage() {

		return "activitiesMySpaceManage";
	}

}
