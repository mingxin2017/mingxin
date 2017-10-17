package com.mx.ssh.dao;

import java.util.List;

import com.mx.ssh.bean.MxActivitiesData;
import com.mx.ssh.bean.MxActivitiesMySpaceData;
import com.mx.ssh.bean.PageBean;


public interface IActivitiesDAO {

	int findTotalCount();

	List<MxActivitiesData> findActivitiesByPage(int begin, int limit);

	boolean deleteActivities(List<Integer> idList);

	boolean addActivity(MxActivitiesData act, MxActivitiesMySpaceData space);

	MxActivitiesMySpaceData getSpaceByActivityId(int activitiesId);

	MxActivitiesMySpaceData getSpaceBySpaceId(int myspaceId);

	boolean editActivitySpace(MxActivitiesMySpaceData space);

	PageBean<MxActivitiesData> searchActivity(String txtSearch);
	
	
	
}
