package com.mx.ssh.service;

import java.util.List;

import com.mx.ssh.bean.MxActivitiesData;
import com.mx.ssh.bean.MxActivitiesMySpaceComment;
import com.mx.ssh.bean.MxActivitiesMySpaceData;
import com.mx.ssh.bean.MxActivitiesMySpaceInviteCode;
import com.mx.ssh.bean.MxActivitiesMySpaceMaterial;
import com.mx.ssh.bean.MxActivitiesMySpaceUsers;
import com.mx.ssh.bean.sysBean.ActivitiesUserMySpaceMaterial;
import com.mx.ssh.bean.sysBean.ActivitiesUserMySpaceMine;

public interface IActivitiesMySpaceService {

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

	int validateInviteCode(String inviteCode, int userId);

	boolean validateMySpaceUser(int myspaceId, int userId);

	boolean deleteMyspaceUser(int myspaceId, int userId);

	int saveMyspaceComment_comment(int myspaceId,int commentId, int userId,
			String commentTxt);

	/*
	 * 删除帖子评论
	 */
	boolean deleteMyspaceComment_comment(int commentId);

	boolean deleteMyspaceMaterial(int materialId);

	boolean deleteMyspaceComment(int commentId);//删除帖子及以下的评论


}
