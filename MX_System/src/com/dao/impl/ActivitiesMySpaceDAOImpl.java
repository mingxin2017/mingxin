package com.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.MxActivitiesMySpaceComment;
import com.bean.MxActivitiesMySpaceData;
import com.bean.MxActivitiesMySpaceUsers;
import com.bean.MxActivitiesPublicityData;
import com.dao.IActivitiesMySpaceDAO;

public class ActivitiesMySpaceDAOImpl extends HibernateDaoSupport implements IActivitiesMySpaceDAO{

	/**
	 * 保存空间讨论内容
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
			MxActivitiesMySpaceData spaceData=(MxActivitiesMySpaceData) getHibernateTemplate().
					find("from com.bean.MxActivitiesMySpaceData au where au.myspaceId = "+ userMySpaceList.get(i).getMyspaceId()).get(0);
			userSpaceList.add(spaceData);
		}
		return userSpaceList;
	}


}
