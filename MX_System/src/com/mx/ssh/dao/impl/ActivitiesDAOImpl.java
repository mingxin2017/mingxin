package com.mx.ssh.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.mx.ssh.bean.MxActivitiesData;
import com.mx.ssh.bean.MxActivitiesMySpaceData;
import com.mx.ssh.bean.MxUsersData;
import com.mx.ssh.bean.PageBean;
import com.mx.ssh.dao.IActivitiesDAO;
import com.mx.ssh.util.PageHibernateCallback;



//声明此类为数据持久层的类
@Repository("activitiesDAO")
public class ActivitiesDAOImpl extends HibernateDaoSupport implements IActivitiesDAO {


	//查询MxActivitiesData表中总记录数
    public int findTotalCount() {
        String hql="select count(*) from MxActivitiesData";
        List<Long> list=(List<Long>) this.getHibernateTemplate().find(hql);
        if(list!=null&&list.size()>0){
            return list.get(0).intValue();
        }
        return 0;
    }
	
	/*
	 * 分页查询活动
	 * begin为
	 */
    public List<MxActivitiesData> findActivitiesByPage(int begin, int limit) {
        String hql="from MxActivitiesData order by createDate desc";
        List<MxActivitiesData> list=(List<MxActivitiesData>) this.getHibernateTemplate().execute((HibernateCallback<MxUsersData>) new PageHibernateCallback(hql, new Object[]{}, begin, limit));
        if(list!=null&&list.size()>0){
            
            return list;
        }
        return null;
    }

    /*
     * 批量删除活动
     */
	public boolean deleteActivities(List<Integer> idList) {
		// TODO Auto-generated method stub
		try{
			String hql = "";
	        for(int i=0;i<idList.size();i++) {
	            if(i==0) {
	                hql = "activitiesId="+idList.get(i);
	            } else {
	                hql =hql + " or activitiesId="+idList.get(i);
	            }
	        }   
	        Session session= this.getSession();
	        Query q= session.createQuery("delete from MxActivitiesData where "+hql);
	        q.executeUpdate();
	        return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean addActivity(MxActivitiesData act,MxActivitiesMySpaceData space) {
		// TODO Auto-generated method stub
		try{
			getHibernateTemplate().save(act);
			int id=act.getActivitiesId();
			space.setActivitiesId(id);
			getHibernateTemplate().save(space);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public MxActivitiesMySpaceData getSpaceByActivityId(int activitiesId) {
		// TODO Auto-generated method stub
		String hql="from MxActivitiesMySpaceData where activitiesId="+activitiesId;
		
		MxActivitiesMySpaceData s=(MxActivitiesMySpaceData) getHibernateTemplate().find(hql).get(0);
		
		return s;
	}

	public MxActivitiesMySpaceData getSpaceBySpaceId(int myspaceId) {
		// TODO Auto-generated method stub
		String hql="from MxActivitiesMySpaceData where myspaceId="+myspaceId;
		MxActivitiesMySpaceData s=(MxActivitiesMySpaceData) getHibernateTemplate().find(hql).get(0);
		return s;
	}

	public boolean editActivitySpace(MxActivitiesMySpaceData space) {
		// TODO Auto-generated method stub
		try{
			getHibernateTemplate().update(space);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public PageBean<MxActivitiesData> searchActivity(String txtSearch) {
		// TODO Auto-generated method stub
		String hql="from MxActivitiesData where activitiesName like '%"+txtSearch+
				"%' or activitiesDescribe like '%"+txtSearch+
				"%'";
	    List<MxActivitiesData> activities=this.getHibernateTemplate().find(hql);
		
		PageBean<MxActivitiesData> pageBean =new PageBean<MxActivitiesData>();
        pageBean.setCurrentPage(1);
        int limit=activities.size();//每页数量
        pageBean.setPageSize(limit);
        pageBean.setAllRow(limit);
        int totalpage=1;
        pageBean.setTotalPage(totalpage);
        pageBean.setList(activities);
        return pageBean;
	}

	public MxActivitiesData getActivityByID(int activitiesId) {
		// TODO Auto-generated method stub
		
		return getHibernateTemplate().get(MxActivitiesData.class, activitiesId);
	}

	public boolean editActivity(MxActivitiesData act) {
		// TODO Auto-generated method stub
		try{
			getHibernateTemplate().update(act);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean setState(int i, int activitiesId) {
		// TODO Auto-generated method stub
		
			// TODO Auto-generated method stub
			try{
				getHibernateTemplate().getSessionFactory().openSession().createSQLQuery("update [MXDB].[dbo].[MX_activities_data] set state="+i+" where activities_id="+activitiesId).executeUpdate();
				return true;
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
		
	}
	


}
