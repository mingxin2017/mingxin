package com.bean.sysBean;

import java.util.List;

import com.bean.MxActivitiesMySpaceData;
import com.bean.MxActivitiesMySpaceMaterial;
import com.bean.MxActivitiesMySpaceUsers;
import com.bean.MxUsersData;

/**
 * ��װ���û����û�ĳ���ռ������е�ý���ز�(���û���Ϣ��װ)
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
