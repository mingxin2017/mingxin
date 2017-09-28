package com.mx.ssh.dao;

import java.util.List;

import com.mx.ssh.bean.MxActivitiesData;


public interface IActivitiesDAO {

	int findTotalCount();

	List<MxActivitiesData> findActivitiesByPage(int begin, int limit);

	boolean deleteActivities(List<Integer> idList);
	
	
	
}