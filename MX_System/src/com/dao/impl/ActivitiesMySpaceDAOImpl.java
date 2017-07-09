package com.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.MxActivitiesMySpaceComment;
import com.bean.MxActivitiesMySpaceData;
import com.bean.MxActivitiesMySpaceUsers;
import com.bean.MxActivitiesPublicityData;
import com.bean.MxUsersData;
import com.bean.sysBean.ActivitiesUserMySpaceComment;
import com.dao.IActivitiesMySpaceDAO;

public class ActivitiesMySpaceDAOImpl extends HibernateDaoSupport implements IActivitiesMySpaceDAO{

	/**
	 * ����ռ���������
	 * 
	 */
	public boolean saveActivitiesMySpaceComment(MxActivitiesMySpaceComment activitiesMySpaceComment) {
		// TODO Auto-generated method stub
		try {
			getHibernateTemplate().save(activitiesMySpaceComment);
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<MxActivitiesMySpaceUsers> getMySpaceListByUserId(Integer userId) {
		// TODO Auto-generated method stub
		List<MxActivitiesMySpaceUsers> userMySpaceList = getHibernateTemplate().find("from com.bean.MxActivitiesMySpaceUsers au where au.userId = "+ userId);
		return userMySpaceList;
	}

	public List<MxActivitiesMySpaceData> getMySpaceListBySpceIds(List<MxActivitiesMySpaceUsers> userMySpaceList) {
		// TODO Auto-generated method stub
		List<MxActivitiesMySpaceData> userSpaceList=new ArrayList();
		for(int i=0;i<userMySpaceList.size();i++){
			MxActivitiesMySpaceData spaceData=(MxActivitiesMySpaceData) getHibernateTemplate().find("from com.bean.MxActivitiesMySpaceData au where au.myspaceId = "+ userMySpaceList.get(i).getMyspaceId()).get(0);
			userSpaceList.add(spaceData);
		}
		return userSpaceList;
	}

	public MxActivitiesMySpaceData getMySpaceBySpaceId(int myspaceId) {
		// TODO Auto-generated method stub
		return (MxActivitiesMySpaceData) getHibernateTemplate().find("from com.bean.MxActivitiesMySpaceData au where au.myspaceId = "+ myspaceId).get(0);
	}

	public MxActivitiesMySpaceUsers getMySpaceUserByUserId_SpaceId(
			Integer userId, int myspaceId) {
		// TODO Auto-generated method stub
		return (MxActivitiesMySpaceUsers) getHibernateTemplate().find("from com.bean.MxActivitiesMySpaceUsers au where au.myspaceId = "+ myspaceId+"and au.userId="+userId).get(0);
	}

	public List<ActivitiesUserMySpaceComment> getUserMySpaceCommontList(
			int myspaceId) {
		// TODO Auto-generated method stub
		List<ActivitiesUserMySpaceComment> reutnList=new ArrayList();
		List<MxActivitiesMySpaceComment> myspaceCommentList=getHibernateTemplate().find("from com.bean.MxActivitiesMySpaceComment au where au.myspaceId = "+ myspaceId);
		for(int i=0;i<myspaceCommentList.size();i++){
			ActivitiesUserMySpaceComment buff=new ActivitiesUserMySpaceComment();
			MxActivitiesMySpaceComment commentBuff=myspaceCommentList.get(i);
			MxUsersData userBuff=(MxUsersData) getHibernateTemplate().find("from com.bean.MxUsersData au where au.userId = "+ commentBuff.getSubmitUserId()).get(0);
			buff.setActivitiesMySpaceComment(commentBuff);
			buff.setUserData(userBuff);
			reutnList.add(buff);
		}
		return reutnList;
	}


}
