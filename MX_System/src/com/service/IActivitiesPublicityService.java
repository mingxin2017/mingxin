package com.service;

import java.util.List;

import com.bean.MxActivitiesPublicityData;

public interface IActivitiesPublicityService {

	
	public List<MxActivitiesPublicityData> getActivitiesPublicityByType(int activitiesType);
	
}
