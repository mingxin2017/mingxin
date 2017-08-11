package com.mx.ssh.service.impl;

import java.util.List;

import com.mx.ssh.bean.MxActivitiesPublicityContent;
import com.mx.ssh.bean.MxActivitiesPublicityData;
import com.mx.ssh.dao.IActivitiesPublicityDAO;

public class ActivitiesPublicityServiceImpl implements com.mx.ssh.service.IActivitiesPublicityService {
	
	private IActivitiesPublicityDAO activitiesPublicityDAO;
	
	/**���ã���������id��ȡ���������
	 * ����1��activitiesType�����
	 * ����ֵ���ɹ����ػ���£�����null
	 * �����ߣ�wulm
	 */
	public List<MxActivitiesPublicityData> getActivitiesPublicityByType(
			int activitiesType) {
		// TODO Auto-generated method stub
		return activitiesPublicityDAO.getActivitiesPublicityByType(activitiesType);
	}

	

	public IActivitiesPublicityDAO getActivitiesPublicityDAO() {
		return activitiesPublicityDAO;
	}

	public void setActivitiesPublicityDAO(IActivitiesPublicityDAO activitiesPublicityDAO) {
		this.activitiesPublicityDAO = activitiesPublicityDAO;
	}



	/**
	 * ����id��ȡ�������������
	 */
	public MxActivitiesPublicityContent getActivitiesPublicityContent(int pdID) {
		
		
		// TODO Auto-generated method stub
		return activitiesPublicityDAO.getActivitiesPublicityContent(pdID);
	}



	
}
