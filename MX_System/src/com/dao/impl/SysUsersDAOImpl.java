package com.dao.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.bean.PageBean;
import com.bean.SysUsers;
import com.dao.ISysUsersDAO;
import com.publicMethos.ISqlUtil;

public class SysUsersDAOImpl extends HibernateDaoSupport implements ISysUsersDAO {

	public static final int pageLinesNum=3;//定义每个页面显示的列表行数
	public static final String DB_table_name="[JC_Web_System_DB].[dbo].[Sys_Users]";//用户数据库及数据表名称
	public static final String Primarykey="user_id";//用户数据表主键名称
	private ISqlUtil sqlUtil;//依赖注入执行自定义sql语句的通用类
	
	public ISqlUtil getSqlUtil() {
		return sqlUtil;
	}
	public void setSqlUtil(ISqlUtil sqlUtil) {
		this.sqlUtil = sqlUtil;
	}

	public void delete(Integer id) {
		getHibernateTemplate().delete(getHibernateTemplate().get(SysUsers.class , id));
	}

	public List<SysUsers> getAll() {
		return getHibernateTemplate().find("from com.bean.SysUsers");
	}
	
	public SysUsers get(Integer id) {
		return getHibernateTemplate().get(SysUsers.class , id);
	}

	public void update(SysUsers user) {
		getHibernateTemplate().saveOrUpdate(user);
	}

	public SysUsers findSysUsersByNameAndPass(String SysUsersname,
			String password) {
		
		List<SysUsers> ul = getHibernateTemplate().find("from com.bean.SysUsers au where au.userLoginName = '"+ SysUsersname+"' and au.userPwd = '"+ password+"'");

		if (ul.size() == 1)
        {
			//System.out.println("查到一条合法用户数据");
			return ul.get(0);
            
        }
		return null;
	}

	public  PageBean<SysUsers> getUsersPageBean(HashMap<String, String> conditionList,int pageNum){
		
		return sqlUtil.queryForPage(DB_table_name,Primarykey, conditionList, SysUsers.class, pageNum);
		
		
	}
	
	
	//按条件页面值查询数据...未测试
	public List<SysUsers> getByPageT_N(int pageNum,int userType, String userName){
		
		//几种典型的分页sql，下面例子是每页50条，198*50=9900，取第199页数据。
		//每页显示m条，取第n页，则rownuber>m*(n-1),rownuber<m*(n-1)+m+1
		int min=pageLinesNum*(pageNum-1);
		int max=pageLinesNum*pageNum+1;
		String sql="select * from (select row_number()over" +
				"(order by user_id)rownumber,* from " +
				"[JC_Web_System_DB].[dbo].[Sys_Users])a " +
				"where rownumber>"+min+" and rownumber<" +max;
		if(userType==-1){
			sql+=" and user_name like '%"+userName+"%'";
		}else{
			sql+=" and user_type="+userType+" and user_name like '%"+userName+"%'";
		}
		
		List<SysUsers> list=(List<SysUsers>) sqlUtil.queryHqlBySession(sql);
		return list;
	}
	
/*	//获取查询页面总量。。未测试
	public int getPageCounts(){
		String sql="";
		sql="select count(*) from " +
				"[JC_Web_System_DB].[dbo].[Sys_Users] ";
		return (int)Math.ceil((double)((Integer)sqlUtil.queryHqlBySession(sql))/pageLinesNum);//通过依赖注入执行hql语句
	}*/
	
	//按条件   获取查询页面数量...未测试
	public int getPageCountsByT_N(int userType, String userName){
		String sql="select count(*) from " +
				"[JC_Web_System_DB].[dbo].[Sys_Users] ";
		if(userType==-1){
			 sql+="where user_name like '%"+userName+"%'";
		}else{
			sql+="where user_type="+userType+" and " +
				"user_name like '%"+userName+"%'";
		}
		//SQLManager sqlManager=(SQLManager)SqlUtil.getBean("sqlManager");
		return (int)Math.ceil((double)((Integer)sqlUtil.queryHqlBySession(sql))/pageLinesNum);//通过依赖注入执行hql语句
	}
	
	

}
