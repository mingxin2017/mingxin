package com.mx.ssh.bean.sysBean;

import java.util.List;

import com.mx.ssh.bean.MxActivitiesMySpaceComment;
import com.mx.ssh.bean.MxActivitiesMySpaceMaterial;

/**
 * 封装了用户和用户某个空间下所有的媒体素材
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
