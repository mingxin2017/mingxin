package com.mx.ssh.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mx.ssh.bean.MxActivitiesData;
import com.mx.ssh.bean.MxActivitiesMySpaceData;
import com.mx.ssh.bean.PageBean;

public interface IActivitiesService {

	PageBean<MxActivitiesData> findByPage(int page);

	boolean deleteActivities(List<Integer> idList);

	boolean addActivity(HttpServletRequest request);

	MxActivitiesMySpaceData getSpaceByActivityId(int activitiesId);

	boolean editActivitySpace(HttpServletRequest request);

	
}
