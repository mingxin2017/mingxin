package com.bean.sysBean;

import java.util.List;

import com.bean.MxActivitiesMySpaceData;
import com.bean.MxActivitiesMySpaceMaterial;
import com.bean.MxActivitiesMySpaceUsers;
import com.bean.MxUsersData;

/**
 * ��װ���û����û�ĳ���ռ������е�ý���ز�
 */
public class ActivitiesUserMySpaceUsers {
	
	
	private MxUsersData userData;
	private MxActivitiesMySpaceData activitiesMySpaceData;
	private List<MxUsersData> activitiesMySpaceUsers;
	public MxUsersData getUserData() {
		return userData;
	}
	public void setUserData(MxUsersData userData) {
		this.userData = userData;
	}
	public MxActivitiesMySpaceData getActivitiesMySpaceData() {
		return activitiesMySpaceData;
	}
	public void setActivitiesMySpaceData(MxActivitiesMySpaceData activitiesMySpaceData) {
		this.activitiesMySpaceData = activitiesMySpaceData;
	}
	public List<MxUsersData> getActivitiesMySpaceUsers() {
		return activitiesMySpaceUsers;
	}
	public void setActivitiesMySpaceUsers(List<MxUsersData> activitiesMySpaceUsers) {
		this.activitiesMySpaceUsers = activitiesMySpaceUsers;
	}
	
}
