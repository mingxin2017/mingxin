package com.action;

import com.service.IActivitiesPublicityService;

public class MxActivitiesMySpaceAction {

	private IActivitiesPublicityService activitiesPublicityService;

	/**
	 * ����*��ռ�action�е�Ĭ�ϴ����� ����
	 */
	public String execute() throws Exception {

		return null;
	}

	/**
	 * ��ȡ��ռ�ҳ�������
	 */
	public String gotoActivitiesMySpaceMain() {

		return "activitiesMySpaceMain";
	}
	
	/**
	 * ��ȡ��ռ����������б�
	 */
	public String getActivitiesMySpaceCommentList() {

		return "activitiesMySpaceCommentList";
	}

	/**
	 * ��ȡ��ռ����������б�
	 */
	public String getActivitiesMySpaceMaterialList() {

		return "activitiesMySpaceMaterialList";
	}
	
	/**
	 * ��ȡ��ռ����������б�
	 */
	public String getActivitiesMySpaceUsersList() {

		return "activitiesMySpaceUsersList";
	}
	
	/**
	 * ��ȡ��ռ����������б�
	 */
	public String getActivitiesMySpaceManage() {

		return "activitiesMySpaceManage";
	}

}
