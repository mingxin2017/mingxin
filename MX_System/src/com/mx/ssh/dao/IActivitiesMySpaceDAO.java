package com.mx.ssh.dao;

import java.util.List;

import com.mx.ssh.bean.MxActivitiesData;
import com.mx.ssh.bean.MxActivitiesMySpaceComment;
import com.mx.ssh.bean.MxActivitiesMySpaceData;
import com.mx.ssh.bean.MxActivitiesMySpaceInviteCode;
import com.mx.ssh.bean.MxActivitiesMySpaceMaterial;
import com.mx.ssh.bean.MxActivitiesMySpaceUsers;
import com.mx.ssh.bean.MxUsersData;
import com.mx.ssh.bean.sysBean.ActivitiesUserMySpaceMaterial;
import com.mx.ssh.bean.sysBean.ActivitiesUserMySpaceMine;

public interface IActivitiesMySpaceDAO {

	boolean saveActivitiesMySpaceComment(MxActivitiesMySpaceComment activitiesMySpaceComment);

	List<MxActivitiesMySpaceUsers> getMySpaceListByUserId(Integer userId);

	List<MxActivitiesMySpaceData> getMySpaceListBySpceIds(List<MxActivitiesMySpaceUsers> userMySpaceList);

	MxActivitiesMySpaceData getMySpaceBySpaceId(int myspaceId);

	MxActivitiesMySpaceUsers getMySpaceUserByUserId_SpaceId(Integer userId,
			int myspaceId);

	List<MxActivitiesMySpaceComment> getUserMySpaceCommontList(int myspaceId);

	List<ActivitiesUserMySpaceMaterial> getUserMySpaceMaterialList(int myspaceId);

	boolean saveActivitiesMySpaceMaterial(MxActivitiesMySpaceMaterial material);

	List<MxActivitiesMySpaceUsers> getMySpaceUsersList(int myspaceId);

	ActivitiesUserMySpaceMine getMySpaceUserMine(Integer userId,
			int myspaceId);

	boolean commentClickPraise(int commentId, int userId);

	MxActivitiesData getActivityByMyspaceId(int parseInt);

	boolean addActivityInviteCode(MxActivitiesMySpaceInviteCode inviteCode);

	/*
	 * 验证活动邀请码
	 */
	int validateInviteCode(String inviteCode, int userId);

	boolean validateMySpaceUser(int myspaceId, int userId);

	boolean deleteMyspaceUser(int myspaceId, int userId);

	boolean saveMyspaceComment_comment(int myspaceId,int commentId, int userId,  String commentTxt);

	boolean deleteMyspaceComment_comment(int commentId);

}
