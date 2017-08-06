package com.service;

import java.util.List;

import com.bean.MxActivitiesData;
import com.bean.MxActivitiesMySpaceComment;
import com.bean.MxActivitiesMySpaceData;
import com.bean.MxActivitiesMySpaceInviteCode;
import com.bean.MxActivitiesMySpaceMaterial;
import com.bean.MxActivitiesMySpaceUsers;
import com.bean.MxUsersData;
import com.bean.sysBean.ActivitiesUserMySpaceMaterial;
import com.bean.sysBean.ActivitiesUserMySpaceMine;

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


}
