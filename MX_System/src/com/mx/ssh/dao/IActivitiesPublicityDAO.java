package com.mx.ssh.dao;

import java.util.List;

import com.mx.ssh.bean.MxActivitiesPublicityContent;
import com.mx.ssh.bean.MxActivitiesPublicityData;

public interface IActivitiesPublicityDAO {

	/**
	 * 根据活动类型id获取活动宣传文章列表
	 */
	List<MxActivitiesPublicityData> getActivitiesPublicityByType(
			int activitiesType);

	/**
	 * 根据id获取活动宣传文章内容
	 * @param pdID
	 * @return
	 */
	MxActivitiesPublicityContent getActivitiesPublicityContent(int pdID);

}
