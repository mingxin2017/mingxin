package com.mx.ssh.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import com.mx.ssh.bean.MxUsersData;
import com.mx.ssh.bean.PageBean;
import com.mx.ssh.dao.ISysUsersDAO;
import com.mx.ssh.util.PageHibernateCallback;



//声明此类为数据持久层的类
@Repository("sysUsersDAO")
public class SysUsersDAOImpl extends HibernateDaoSupport implements ISysUsersDAO {

	public static final int pageLinesNum=3;//定义每个页面显示的列表行数
	public static final String DB_table_name="[JC_Web_System_DB].[dbo].[Sys_Users]";//用户数据库及数据表名称
	public static final String Primarykey="user_id";//用户数据表主键名称
	
	@Autowired 
    public void setSessionFactoryOverride(SessionFactory sessionFactory)
    { 
      super.setSessionFactory(sessionFactory); 
    } 
	
	
	public void delete(Integer id) {
		getHibernateTemplate().delete(getHibernateTemplate().get(MxUsersData.class , id));
	}

	public List<MxUsersData> getAll() {
		return getHibernateTemplate().find("from com.mx.ssh.bean.MxUsersData");
	}
	
	public MxUsersData get(Integer id) {
		return getHibernateTemplate().get(MxUsersData.class , id);
	}

	public void update(MxUsersData user) {
		getHibernateTemplate().saveOrUpdate(user);
	}

	public MxUsersData findSysUsersByNameAndPass(String SysUsersname,
			String password) {
		
		List<MxUsersData> ul = getHibernateTemplate().find("from com.mx.ssh.bean.MxUsersData au where au.userLoginName = '"+ SysUsersname+"' and au.userPwd = '"+ password+"'");

		if (ul.size() == 1)
        {
			//System.out.println("查到一条合法用户数据");
			return ul.get(0);
            
        }
		return null;
	}

	
	
	//添加新用户
	public void addUser(MxUsersData userData) {
		
		getHibernateTemplate().save(userData);
		
	}
	public MxUsersData getUserByOpenId(String openId) {
		// TODO Auto-generated method stub
		List<MxUsersData> mud=getHibernateTemplate().find("from com.mx.ssh.bean.MxUsersData au where au.weixinOpenId ='"+openId+"'");
		if(mud.size()!=0){
			return mud.get(0);
		}else{
			return null;
		}
		
	}
	public boolean setUserState(MxUsersData ur) {
		// TODO Auto-generated method stub
		
		try{
			getHibernateTemplate().getSessionFactory().openSession().createSQLQuery("update [MXDB].[dbo].[MX_users_data] set user_state="+ur.getUserState()+" where weixin_openID='"+ur.getWeixinOpenId()+"'").executeUpdate();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	public MxUsersData validateWeixinUser(String openId) {
		// TODO Auto-generated method stub
		
		List<MxUsersData> ud= getHibernateTemplate().find("from com.mx.ssh.bean.MxUsersData au where au.weixinOpenId ='"+openId+"' and au.userState=0");
		if(ud.size()==0){
			return null;
		}else{
			return ud.get(0);
		}
	}
	public int isExistUser(String open_id) {
		// TODO Auto-generated method stub
		return 0;
	}


	/*
	 * 系统用户登录(non-Javadoc)
	 * @see com.mx.ssh.dao.ISysUsersDAO#userLogin(java.lang.String, java.lang.String)
	 */
	public MxUsersData userLogin(String userName, String userPwd) {
		// TODO Auto-generated method stub
		//this.getHibernateTemplate().clear();
		List<MxUsersData> ud= getHibernateTemplate().find("from com.mx.ssh.bean.MxUsersData au where au.userName ='"+userName+"' and au.userTypeId=1100 and au.password='"+userPwd+"'");
		
		if(ud.size()>0){
			MxUsersData user=ud.get(0);
			user.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
			getHibernateTemplate().update(user);
			return user;
		}else{
			return null;
		}
		
	}
	
	
	//查询MxUsersData表中总记录数
    public int findTotalCount() {
        String hql="select count(*) from MxUsersData";
        List<Long> list=(List<Long>) this.getHibernateTemplate().find(hql);
        if(list!=null&&list.size()>0){
            return list.get(0).intValue();
        }
        return 0;
    }
	
	/*
	 * 分页查询用户
	 * begin为
	 */
    public List<MxUsersData> findUsersByPage(int begin, int limit) {
        String hql="from MxUsersData order by subscribeTime desc";
        List<MxUsersData> list=(List<MxUsersData>) this.getHibernateTemplate().execute((HibernateCallback<MxUsersData>) new PageHibernateCallback(hql, new Object[]{}, begin, limit));
        if(list!=null&&list.size()>0){
            
            return list;
        }
        return null;
    }


    /*
     * 添加系统管理员(non-Javadoc)
     * @see com.mx.ssh.dao.ISysUsersDAO#addAdmin(com.mx.ssh.bean.MxUsersData)
     */
	public boolean addAdmin(MxUsersData user) {
		// TODO Auto-generated method stub
		try{
			getHibernateTemplate().save(user);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}


	/*
	 * 批量删除用户(non-Javadoc)
	 * @see com.mx.ssh.dao.ISysUsersDAO#deleteUsers(java.util.List)
	 */
	public boolean deleteUsers(List<Integer> idList) {
		// TODO Auto-generated method stub
		try{
			String hql = "";
	        for(int i=0;i<idList.size();i++) {
	            if(i==0) {
	                hql = "userId="+idList.get(i);
	            } else {
	                hql =hql + " or userId="+idList.get(i);
	            }
	        }   
	        Session session= this.getSession();
	        Query q= session.createQuery("delete from MxUsersData where "+hql);
	        q.executeUpdate();
	        return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}


	public PageBean<MxUsersData> searchUser(String txtSearch) {
		// TODO Auto-generated method stub
		
		String hql="from MxUsersData where userName like '%"+txtSearch+
				"%' or userRealName like '%"+txtSearch+
				"%' or userEmail like '%"+txtSearch+
				"%' or userPhoneNum like '%"+txtSearch+
				"%'";
	    List<MxUsersData> users=this.getHibernateTemplate().find(hql);
		//List<MxUsersData> users=
		
		PageBean<MxUsersData> pageBean =new PageBean<MxUsersData>();
        pageBean.setCurrentPage(1);
        int limit=users.size();//每页数量
        pageBean.setPageSize(limit);
        //int totalCount=sysUsersDAO.findTotalCount();
        pageBean.setAllRow(limit);
        int totalpage=1;
        pageBean.setTotalPage(totalpage);
        //每页显示的数据集合
        //int begin=(page-1)*limit;
        //List<MxUsersData> list=sysUsersDAO.findUsersByPage(begin,limit);
        pageBean.setList(users);
        return pageBean;
		//return null;
	}


	public boolean setState(int i, int userId) {
		// TODO Auto-generated method stub
		try{
			getHibernateTemplate().getSessionFactory().openSession().createSQLQuery("update [MXDB].[dbo].[MX_users_data] set user_state="+i+" where user_id="+userId).executeUpdate();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}


	/*
	 * 删除用户(non-Javadoc)
	 * @see com.mx.ssh.dao.ISysUsersDAO#deleteUser(int)
	 */
	public boolean deleteUser(int userId) {
		// TODO Auto-generated method stub
		
		try{
			String hql = "Delete FROM MxUsersData Where userId="+userId;     
	        Query q = getHibernateTemplate().getSessionFactory().openSession().createQuery(hql);     
	        q.executeUpdate();   

			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}


	public boolean restoreUserPassword(int userId) {
		// TODO Auto-generated method stub

		try{
			String hql = "update MxUsersData set password='123456' where userId="+userId;     
	        Query q = getHibernateTemplate().getSessionFactory().openSession().createQuery(hql);     
	        q.executeUpdate();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
