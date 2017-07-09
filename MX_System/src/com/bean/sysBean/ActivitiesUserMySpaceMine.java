package com.bean.sysBean;

import java.util.List;

import com.bean.MxActivitiesMySpaceComment;
import com.bean.MxActivitiesMySpaceData;
import com.bean.MxActivitiesMySpaceMaterial;
import com.bean.MxUsersData;

/**
 * ��װ���û����û�ĳ���ռ������е�ý���ز�
 */
public class ActivitiesUserMySpaceMine {
	
	
	private MxUsersData userData;
	private MxActivitiesMySpaceData activitiesMySpaceData;
	private List<MxActivitiesMySpaceMaterial> activitiesMySpaceMaterialList;
	private List<MxActivitiesMySpaceComment> activitiesMySpaceCommentList;
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
	public List<MxActivitiesMySpaceComment> getActivitiesMySpaceCommentList() {
		return activitiesMySpaceCommentList;
	}
	public void setActivitiesMySpaceCommentList(
			List<MxActivitiesMySpaceComment> activitiesMySpaceCommentList) {
		this.activitiesMySpaceCommentList = activitiesMySpaceCommentList;
	}
	
}
