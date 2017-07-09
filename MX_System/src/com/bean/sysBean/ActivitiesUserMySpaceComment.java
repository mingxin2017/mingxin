package com.bean.sysBean;

import com.bean.MxActivitiesMySpaceComment;
import com.bean.MxUsersData;

/**
 * 封装了用户和用户某个空间下的讨论内容
 */
public class ActivitiesUserMySpaceComment {
	
	private MxUsersData userData;
	private MxActivitiesMySpaceComment activitiesMySpaceComment;
	
	public MxUsersData getUserData() {
		return userData;
	}
	public void setUserData(MxUsersData userData) {
		this.userData = userData;
	}
	public MxActivitiesMySpaceComment getActivitiesMySpaceComment() {
		return activitiesMySpaceComment;
	}
	public void setActivitiesMySpaceComment(MxActivitiesMySpaceComment activitiesMySpaceComment) {
		this.activitiesMySpaceComment = activitiesMySpaceComment;
	}

}
