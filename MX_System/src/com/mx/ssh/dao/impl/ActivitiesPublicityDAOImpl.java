package com.mx.ssh.dao.impl;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mx.ssh.bean.MxActivitiesPublicityContent;
import com.mx.ssh.bean.MxActivitiesPublicityData;
import com.mx.ssh.bean.MxUsersData;
import com.mx.ssh.dao.IActivitiesPublicityDAO;

public class ActivitiesPublicityDAOImpl extends HibernateDaoSupport implements IActivitiesPublicityDAO{

	/**
	 * ��������id��ȡ���������
	 * 
	 */
	public List<MxActivitiesPublicityData> getActivitiesPublicityByType(
			int activitiesType) {
		List<MxActivitiesPublicityData> publicityList = getHibernateTemplate().find("from com.bean.MxActivitiesPublicityData au where au.actvitiesTypeId = "+ activitiesType);
		
		return publicityList;
	}

	public MxActivitiesPublicityContent getActivitiesPublicityContent(int pdID) {
		// TODO Auto-generated method stubcom.bean.MxActivitiesPublicityContent.publicitityDataId
		//return getHibernateTemplate().get(MxActivitiesPublicityContent.class , pdID);
		return (MxActivitiesPublicityContent) getHibernateTemplate().find("from com.bean.MxActivitiesPublicityContent au where au.publicityDataId = "+ pdID).get(0);
	}


}
