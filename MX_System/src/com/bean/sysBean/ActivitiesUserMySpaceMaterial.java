package com.bean.sysBean;

import java.util.List;

import com.bean.MxActivitiesMySpaceData;
import com.bean.MxActivitiesMySpaceMaterial;
import com.bean.MxActivitiesMySpaceUsers;
import com.bean.MxUsersData;

/**
 * ��װ���û����û�ĳ���ռ������е�ý���ز�
 */
public class ActivitiesUserMySpaceMaterial {
	
	
	private MxUsersData userData;
	private MxActivitiesMySpaceData activitiesMySpaceData;
	private List<MxActivitiesMySpaceMaterial> activitiesMySpaceMaterialList;
	private MxActivitiesMySpaceUsers activitiesMySpaceUser;
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
	public List<MxActivitiesMySpaceMaterial> getActivitiesMySpaceMaterialList() {
		return activitiesMySpaceMaterialList;
	}
	public void setActivitiesMySpaceMaterialList(
			List<MxActivitiesMySpaceMaterial> activitiesMySpaceMaterialList) {
		this.activitiesMySpaceMaterialList = activitiesMySpaceMaterialList;
	}
	public MxActivitiesMySpaceUsers getActivitiesMySpaceUser() {
		return activitiesMySpaceUser;
	}
	public void setActivitiesMySpaceUser(MxActivitiesMySpaceUsers activitiesMySpaceUser) {
		this.activitiesMySpaceUser = activitiesMySpaceUser;
	}
}
