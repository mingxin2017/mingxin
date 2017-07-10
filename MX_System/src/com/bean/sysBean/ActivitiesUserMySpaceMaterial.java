package com.bean.sysBean;

import java.util.List;

import com.bean.MxActivitiesMySpaceData;
import com.bean.MxActivitiesMySpaceMaterial;
import com.bean.MxActivitiesMySpaceUsers;
import com.bean.MxUsersData;

/**
 * 封装了用户和用户某个空间下所有的媒体素材(按用户信息封装)
 */
public class ActivitiesUserMySpaceMaterial {
	private MxUsersData userData;
	private List<MxActivitiesMySpaceMaterial> userMySpaceMaterialList;
	
	
	public MxUsersData getUserData() {
		return userData;
	}
	public void setUserData(MxUsersData userData) {
		this.userData = userData;
	}
	public List<MxActivitiesMySpaceMaterial> getUserMySpaceMaterialList() {
		return userMySpaceMaterialList;
	}
	public void setUserMySpaceMaterialList(List<MxActivitiesMySpaceMaterial> userMySpaceMaterialList) {
		this.userMySpaceMaterialList = userMySpaceMaterialList;
	}
	
}
