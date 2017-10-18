package com.mx.ssh.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.ssh.bean.MxActivitiesData;
import com.mx.ssh.bean.MxActivitiesMySpaceData;
import com.mx.ssh.bean.MxUsersData;
import com.mx.ssh.bean.PageBean;
import com.mx.ssh.dao.IActivitiesDAO;
import com.mx.ssh.service.IActivitiesService;

@Service("activitiesService")
public class ActivitiesServiceImpl implements IActivitiesService {
	
	@Autowired
	private IActivitiesDAO activitiesDAO;

	/*
	 * 分页查询结果
	 */
	public PageBean<MxActivitiesData> findByPage(int page) {
		// TODO Auto-generated method stub
		PageBean<MxActivitiesData> pageBean =new PageBean<MxActivitiesData>();
        pageBean.setCurrentPage(page);
        int limit=2;//每页数量
        pageBean.setPageSize(limit);
        int totalCount=activitiesDAO.findTotalCount();
        pageBean.setAllRow(totalCount);
        int totalpage=(int)Math.ceil(totalCount/limit);
        pageBean.setTotalPage(totalpage);
        //每页显示的数据集合
        int begin=(page-1)*limit;
        List<MxActivitiesData> list=activitiesDAO.findActivitiesByPage(begin,limit);
        pageBean.setList(list);
        return pageBean;
	}

	public boolean deleteActivities(List<Integer> idList) {
		// TODO Auto-generated method stub
		return activitiesDAO.deleteActivities(idList);
	}

	public boolean addActivity(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String activitiesName=request.getParameter("activitiesName");
		String activityDescribe=request.getParameter("activityDescribe");
		int numLower=Integer.parseInt(request.getParameter("numLower").trim());
		int numUper=Integer.parseInt(request.getParameter("numUper").trim());
		String myspaceName=request.getParameter("myspaceName");
		String myspaceDescribe=request.getParameter("myspaceDescribe");
		
		int activityType=Integer.parseInt(request.getParameter("activityType").trim());
		
		String imgURL=request.getParameter("imgURL");
		
		MxUsersData mxUsersData=(MxUsersData) request.getSession().getAttribute("userInfo");
		
		MxActivitiesData act=new MxActivitiesData(mxUsersData, activitiesName,
				activityType, activityDescribe, 0,
				new Date(), new Date(),numUper,
				numLower,imgURL);
		
		MxActivitiesMySpaceData space=new MxActivitiesMySpaceData(-1, myspaceDescribe,
				new Date(), new Date(), numUper,
				numLower,0,imgURL,
				myspaceName);
		
		return activitiesDAO.addActivity(act,space);
	}

	public MxActivitiesMySpaceData getSpaceByActivityId(int activitiesId) {
		// TODO Auto-generated method stub
		
		return activitiesDAO.getSpaceByActivityId(activitiesId);
	}

	public boolean editActivitySpace(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		int myspaceId=Integer.parseInt(request.getParameter("myspaceId").trim());
		int numLower=Integer.parseInt(request.getParameter("numLower").trim());
		int numUper=Integer.parseInt(request.getParameter("numUper").trim());
		String imgURL=request.getParameter("imgURL");
		String describe=request.getParameter("describe");
		
		MxActivitiesMySpaceData space=activitiesDAO.getSpaceBySpaceId(myspaceId);
		
		space.setLowerLimit(numLower);
		space.setUpperLimit(numUper);
		space.setCoverImageUrl(imgURL);
		space.setDescribe(describe);
		space.setUpdateDate(new Date());
		
		/*String activitiesName=request.getParameter("activitiesName");
		String activityDescribe=request.getParameter("activityDescribe");
		String myspaceName=request.getParameter("myspaceName");
		int activityType=Integer.parseInt(request.getParameter("activityType").trim());
		*/

		MxUsersData mxUsersData=(MxUsersData) request.getSession().getAttribute("userInfo");
		return activitiesDAO.editActivitySpace(space);
		
	}

	public PageBean<MxActivitiesData> searchActivity(String txtSearch) {
		// TODO Auto-generated method stub
		return activitiesDAO.searchActivity(txtSearch);
	}
	
	
}
