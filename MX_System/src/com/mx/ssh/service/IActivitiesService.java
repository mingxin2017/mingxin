package com.mx.ssh.service;

import java.util.List;

import com.mx.ssh.bean.MxActivitiesData;
import com.mx.ssh.bean.PageBean;

public interface IActivitiesService {

	PageBean<MxActivitiesData> findByPage(int page);

	boolean deleteActivities(List<Integer> idList);

	
}
