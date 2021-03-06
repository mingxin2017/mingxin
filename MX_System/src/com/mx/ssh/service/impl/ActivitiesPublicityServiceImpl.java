package com.mx.ssh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.ssh.bean.MxActivitiesPublicityContent;
import com.mx.ssh.bean.MxActivitiesPublicityData;
import com.mx.ssh.dao.IActivitiesPublicityDAO;

@Service("activitiesPublicityService")
public class ActivitiesPublicityServiceImpl implements com.mx.ssh.service.IActivitiesPublicityService {
	
	@Autowired
	private IActivitiesPublicityDAO activitiesPublicityDAO;
	
	/**作用：根据类型id获取活动宣传文章
	 * 参数1：activitiesType活动类型
	 * 返回值：成功返回活动文章，否则null
	 * 创建者：wulm
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
	 * 根据id获取活动宣传文章内容
	 */
	public MxActivitiesPublicityContent getActivitiesPublicityContent(int pdID) {
		
		
		// TODO Auto-generated method stub
		return activitiesPublicityDAO.getActivitiesPublicityContent(pdID);
	}



	
}
