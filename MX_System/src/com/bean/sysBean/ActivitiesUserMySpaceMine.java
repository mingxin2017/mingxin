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
	
	private List<MxActivitiesMySpaceMaterial> activitiesMySpaceMaterialMineList;
	private List<MxActivitiesMySpaceComment> activitiesMySpaceCommentMineList;
	
	public List<MxActivitiesMySpaceMaterial> getActivitiesMySpaceMaterialMineList() {
		return activitiesMySpaceMaterialMineList;
	}
	public void setActivitiesMySpaceMaterialMineList(
			List<MxActivitiesMySpaceMaterial> activitiesMySpaceMaterialMineList) {
		this.activitiesMySpaceMaterialMineList = activitiesMySpaceMaterialMineList;
	}
	public List<MxActivitiesMySpaceComment> getActivitiesMySpaceCommentMineList() {
		return activitiesMySpaceCommentMineList;
	}
	public void setActivitiesMySpaceCommentMineList(
			List<MxActivitiesMySpaceComment> activitiesMySpaceCommentMineList) {
		this.activitiesMySpaceCommentMineList = activitiesMySpaceCommentMineList;
	}
	
}
