package com.mx.ssh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.ssh.bean.MxActivitiesData;
import com.mx.ssh.bean.MxActivitiesMySpaceComment;
import com.mx.ssh.bean.MxActivitiesMySpaceData;
import com.mx.ssh.bean.MxActivitiesMySpaceInviteCode;
import com.mx.ssh.bean.MxActivitiesMySpaceMaterial;
import com.mx.ssh.bean.MxActivitiesMySpaceUsers;
import com.mx.ssh.bean.sysBean.ActivitiesUserMySpaceMaterial;
import com.mx.ssh.bean.sysBean.ActivitiesUserMySpaceMine;
import com.mx.ssh.dao.IActivitiesMySpaceDAO;

@Service("activitiesMySpaceService")
public class ActivitiesMySpaceServiceImpl implements com.mx.ssh.service.IActivitiesMySpaceService{

	@Autowired
	private IActivitiesMySpaceDAO activitiesMySpaceDAO;

	
	public boolean saveActivitiesMySpaceComment(MxActivitiesMySpaceComment activitiesMySpaceComment) {
		// TODO Auto-generated method stub
		return activitiesMySpaceDAO.saveActivitiesMySpaceComment(activitiesMySpaceComment);
	}

	public List<MxActivitiesMySpaceUsers> getMySpaceListByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return activitiesMySpaceDAO.getMySpaceListByUserId(userId);
	}

	public List<MxActivitiesMySpaceData> getMySpaceListBySpceIds(List<MxActivitiesMySpaceUsers> userMySpaceList) {
		// TODO Auto-generated method stub
		return activitiesMySpaceDAO.getMySpaceListBySpceIds(userMySpaceList);
	}

	public MxActivitiesMySpaceData getMySpaceBySpaceId(int myspaceId) {
		// TODO Auto-generated method stub
		return activitiesMySpaceDAO.getMySpaceBySpaceId(myspaceId) ;
	}

	public MxActivitiesMySpaceUsers getMySpaceUserByUserId_SpaceId(
			Integer userId, int myspaceId) {
		// TODO Auto-generated method stub
		return activitiesMySpaceDAO.getMySpaceUserByUserId_SpaceId(
				userId,myspaceId);
	}

	public List<MxActivitiesMySpaceComment> getUserMySpaceCommontList(
			int myspaceId) {
		// TODO Auto-generated method stub
		return activitiesMySpaceDAO.getUserMySpaceCommontList(myspaceId);
	}

	public List<ActivitiesUserMySpaceMaterial> getUserMySpaceMaterialList(
			int myspaceId) {
		// TODO Auto-generated method stub
		return activitiesMySpaceDAO.getUserMySpaceMaterialList(myspaceId);
	}

	public boolean saveActivitiesMySpaceMaterial(
			MxActivitiesMySpaceMaterial material) {
		// TODO Auto-generated method stub
		return activitiesMySpaceDAO.saveActivitiesMySpaceMaterial(material);
	}

	public List<MxActivitiesMySpaceUsers> getMySpaceUsersList(int myspaceId) {
		// TODO Auto-generated method stub
		return activitiesMySpaceDAO.getMySpaceUsersList(myspaceId);
	}

	public ActivitiesUserMySpaceMine getMySpaceUserMine(Integer userId,
			int myspaceId) {
		// TODO Auto-generated method stub
		return  activitiesMySpaceDAO.getMySpaceUserMine(userId,myspaceId);
	}

	public boolean commentClickPraise(int commentId, int userId) {
		// TODO Auto-generated method stub
		return activitiesMySpaceDAO.commentClickPraise(commentId,userId);
	}

	public MxActivitiesData getActivityByMyspaceId(int parseInt) {
		// TODO Auto-generated method stub
		MxActivitiesMySpaceData t=activitiesMySpaceDAO.getMySpaceBySpaceId(parseInt);
		return activitiesMySpaceDAO.getActivityByMyspaceId(t.getActivitiesId());
	}

	public boolean addActivityInviteCode(
			MxActivitiesMySpaceInviteCode inviteCode) {
		// TODO Auto-generated method stub
		return activitiesMySpaceDAO.addActivityInviteCode(inviteCode);
	}

	public int validateInviteCode(String inviteCode,int userId) {
		// TODO Auto-generated method stub
		return activitiesMySpaceDAO.validateInviteCode(inviteCode,userId);
	}

	public boolean validateMySpaceUser(int myspaceId, int userId) {
		// TODO Auto-generated method stub
		return activitiesMySpaceDAO.validateMySpaceUser(myspaceId,userId);
	}

	public boolean deleteMyspaceUser(int myspaceId, int userId) {
		// TODO Auto-generated method stub
		return activitiesMySpaceDAO.deleteMyspaceUser(myspaceId,userId);
	}

	public boolean saveMyspaceComment_comment(int myspaceId,int commentId, int userId,
			String commentTxt) {
		// TODO Auto-generated method stub
		return activitiesMySpaceDAO.saveMyspaceComment_comment(myspaceId,commentId,userId,commentTxt);
	}

	public boolean deleteMyspaceComment_comment(int commentId) {
		// TODO Auto-generated method stub
		return activitiesMySpaceDAO.deleteMyspaceComment_comment(commentId);
	}

	
}
