package com.dao;

import java.util.List;

import com.bean.MxActivitiesPublicityData;

public interface IActivitiesPublicityDAO {

	/**
	 * 根据活动类型id获取活动宣传文章
	 */
	List<MxActivitiesPublicityData> getActivitiesPublicityByType(
			int activitiesType);

}
