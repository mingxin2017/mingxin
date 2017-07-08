package com.service.impl;

import java.util.List;

import com.bean.MxActivitiesMySpaceComment;
import com.bean.MxActivitiesMySpaceData;
import com.bean.MxActivitiesMySpaceUsers;
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

	
}
