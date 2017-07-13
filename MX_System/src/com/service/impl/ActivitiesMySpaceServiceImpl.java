package com.service.impl;

import java.util.List;

import com.bean.MxActivitiesMySpaceComment;
import com.bean.MxActivitiesMySpaceData;
import com.bean.MxActivitiesMySpaceMaterial;
import com.bean.MxActivitiesMySpaceUsers;
import com.bean.MxUsersData;
import com.bean.sysBean.ActivitiesUserMySpaceMaterial;
import com.bean.sysBean.ActivitiesUserMySpaceMine;
import com.dao.IActivitiesMySpaceDAO;


public class ActivitiesMySpaceServiceImpl implements com.service.IActivitiesMySpaceService{

	private IActivitiesMySpaceDAO activitiesMySpaceDAO;

	public IActivitiesMySpaceDAO getActivitiesMySpaceDAO() {
		return activitiesMySpaceDAO;
	}

	public void setActivitiesMySpaceDAO(IActivitiesMySpaceDAO activitiesMySpaceDAO) {
		this.activitiesMySpaceDAO = activitiesMySpaceDAO;
	}
	
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

	
}
