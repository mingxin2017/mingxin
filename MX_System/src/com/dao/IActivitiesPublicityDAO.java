package com.dao;

import java.util.List;

import com.bean.MxActivitiesPublicityData;

public interface IActivitiesPublicityDAO {

	/**
	 * ���ݻ����id��ȡ���������
	 */
	List<MxActivitiesPublicityData> getActivitiesPublicityByType(
			int activitiesType);

}
