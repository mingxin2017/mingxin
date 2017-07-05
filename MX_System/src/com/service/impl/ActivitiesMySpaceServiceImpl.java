package com.service.impl;

import com.bean.MxActivitiesMySpaceComment;
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

	
}
