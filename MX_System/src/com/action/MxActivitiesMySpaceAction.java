package com.action;

import com.service.IActivitiesPublicityService;

public class MxActivitiesMySpaceAction {

	

	/**
	 * ����*��ռ�action�е�Ĭ�ϴ����� ����
	 */
	public String execute() throws Exception {

		return null;
	}

	/**
	 * ��ת����ռ�ҳ�������
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
	 * ��ȡ��ռ��ز��б�
	 */
	public String getActivitiesMySpaceMaterialList() {

		return "activitiesMySpaceMaterialList";
	}
	
	/**
	 * ��ȡ��ռ��û�
	 */
	public String getActivitiesMySpaceUsersList() {

		return "activitiesMySpaceUsersList";
	}
	
	/**
	 * ��ȡ��ռ��ҵ������б�
	 */
	public String getActivitiesMySpaceMine() {

		return "activitiesMySpaceManage";
	}

}
