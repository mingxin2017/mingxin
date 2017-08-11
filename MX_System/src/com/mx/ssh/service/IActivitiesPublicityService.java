package com.mx.ssh.service;

import java.util.List;

import com.mx.ssh.bean.MxActivitiesPublicityContent;
import com.mx.ssh.bean.MxActivitiesPublicityData;

public interface IActivitiesPublicityService {

	/**
	 * 获取活动宣传数据列表
	 * @param activitiesType
	 * @return
	 */
	public List<MxActivitiesPublicityData> getActivitiesPublicityByType(int activitiesType);

	/**
	 * 根据id获取活动宣传文章内容
	 * @param pdID
	 * @return
	 */
	public MxActivitiesPublicityContent getActivitiesPublicityContent(int pdID);

	
	
}
