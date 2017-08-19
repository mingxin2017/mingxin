package com.mx.ssh.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import com.mx.ssh.bean.MxUsersData;
import com.mx.ssh.dao.ISysUsersDAO;


//��������Ϊ���ݳ־ò����
@Repository("sysUsersDAO")
public class SysUsersDAOImpl extends HibernateDaoSupport implements ISysUsersDAO {

	public static final int pageLinesNum=3;//����ÿ��ҳ����ʾ���б�����
	public static final String DB_table_name="[JC_Web_System_DB].[dbo].[Sys_Users]";//�û����ݿ⼰���ݱ�����
	public static final String Primarykey="user_id";//�û����ݱ���������
	
	@Autowired 
    public void setSessionFactoryOverride(SessionFactory sessionFactory)
    { 
      super.setSessionFactory(sessionFactory); 
    } 
	
	
	public void delete(Integer id) {
		getHibernateTemplate().delete(getHibernateTemplate().get(MxUsersData.class , id));
	}

	public List<MxUsersData> getAll() {
		return getHibernateTemplate().find("from com.mx.ssh.bean.SysUsers");
	}
	
	public MxUsersData get(Integer id) {
		return getHibernateTemplate().get(MxUsersData.class , id);
	}

	public void update(MxUsersData user) {
		getHibernateTemplate().saveOrUpdate(user);
	}

	public MxUsersData findSysUsersByNameAndPass(String SysUsersname,
			String password) {
		
		List<MxUsersData> ul = getHibernateTemplate().find("from com.mx.ssh.bean.SysUsers au where au.userLoginName = '"+ SysUsersname+"' and au.userPwd = '"+ password+"'");

		if (ul.size() == 1)
        {
			//System.out.println("�鵽һ���Ϸ��û�����");
			return ul.get(0);
            
        }
		return null;
	}

	
	
	//������û�
	public void addUser(MxUsersData userData) {
		
		getHibernateTemplate().save(userData);
		
	}
	public MxUsersData getUserByOpenId(String openId) {
		// TODO Auto-generated method stub
		List<MxUsersData> mud=getHibernateTemplate().find("from com.mx.ssh.bean.MxUsersData au where au.weixinOpenId ='"+openId+"'");
		if(mud.size()!=0){
			return (MxUsersData) mud.get(0);
		}else{
			return null;
		}
		
	}
	public boolean setUserState(MxUsersData ur) {
		// TODO Auto-generated method stub
		
		try{
			getHibernateTemplate().getSessionFactory().openSession().createSQLQuery("update [MXDB].[dbo].[MX_users_data] set user_state="+ur.getUserState()+" where weixin_openID='"+ur.getWeixinOpenId()+"'");
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

}
