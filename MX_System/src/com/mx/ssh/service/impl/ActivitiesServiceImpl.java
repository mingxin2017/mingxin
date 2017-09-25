package com.mx.ssh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.ssh.bean.MxActivitiesData;
import com.mx.ssh.bean.MxUsersData;
import com.mx.ssh.bean.PageBean;
import com.mx.ssh.dao.IActivitiesDAO;
import com.mx.ssh.service.IActivitiesService;

@Service("activitiesService")
public class ActivitiesServiceImpl implements IActivitiesService {
	
	@Autowired
	private IActivitiesDAO activitiesDAO;

	/*
	 * ��ҳ��ѯ���
	 */
	public PageBean<MxActivitiesData> findByPage(int page) {
		// TODO Auto-generated method stub
		PageBean<MxActivitiesData> pageBean =new PageBean<MxActivitiesData>();
        pageBean.setCurrentPage(page);
        int limit=5;//ÿҳ����
        pageBean.setPageSize(limit);
        int totalCount=activitiesDAO.findTotalCount();
        pageBean.setAllRow(totalCount);
        int totalpage=(int)Math.ceil(totalCount/limit);
        pageBean.setTotalPage(totalpage);
        //ÿҳ��ʾ�����ݼ���
        int begin=(page-1)*limit;
        List<MxActivitiesData> list=activitiesDAO.findActivitiesByPage(begin,limit);
        pageBean.setList(list);
        return pageBean;
	}

	public boolean deleteActivities(List<Integer> idList) {
		// TODO Auto-generated method stub
		return activitiesDAO.deleteActivities(idList);
	}
	
	
}
