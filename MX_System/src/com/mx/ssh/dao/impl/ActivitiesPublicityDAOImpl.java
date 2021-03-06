package com.mx.ssh.dao.impl;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.mx.ssh.bean.MxActivitiesPublicityContent;
import com.mx.ssh.bean.MxActivitiesPublicityData;
import com.mx.ssh.dao.IActivitiesPublicityDAO;

@Repository("activitiesPublicityDAO")
public class ActivitiesPublicityDAOImpl extends HibernateDaoSupport implements IActivitiesPublicityDAO{

	/**
	 * 根据类型id获取活动宣传文章
	 * 
	 */
	public List<MxActivitiesPublicityData> getActivitiesPublicityByType(
			int activitiesType) {
		List<MxActivitiesPublicityData> publicityList = getHibernateTemplate().find("from com.mx.ssh.bean.MxActivitiesPublicityData au where au.actvitiesTypeId = "+ activitiesType);
		
		return publicityList;
	}

	public MxActivitiesPublicityContent getActivitiesPublicityContent(int pdID) {
		// TODO Auto-generated method stubcom.mx.ssh.bean.MxActivitiesPublicityContent.publicitityDataId
		//return getHibernateTemplate().get(MxActivitiesPublicityContent.class , pdID);
		return (MxActivitiesPublicityContent) getHibernateTemplate().find("from com.mx.ssh.bean.MxActivitiesPublicityContent au where au.publicityDataId = "+ pdID).get(0);
	}


}
