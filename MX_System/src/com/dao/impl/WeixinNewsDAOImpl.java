package com.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.MxNewsContent;
import com.bean.MxNewsData;
import com.bean.MxNewsType;
import com.bean.MxRegion;
import com.bean.MxSchools;
import com.bean.MxUsersData;
import com.dao.IWeixinNewsDAO;
import com.publicMethos.ISqlUtil;

public class WeixinNewsDAOImpl extends HibernateDaoSupport implements IWeixinNewsDAO {

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
	//添加新闻
	public void addNews(MxNewsData newsData,MxNewsContent newsContent) {
		try{
			//添加主表
			Integer news_id = (Integer) getHibernateTemplate().save(newsData);
			//添加从表
			newsContent.setNewsId(news_id);
			getHibernateTemplate().save(newsContent);
		}catch(Exception e){
			e.getMessage();
			e.printStackTrace();
		}
	}
    //根据openid查询用户id
	public MxUsersData getUser(String open_id){
		return (MxUsersData) getHibernateTemplate().find("from com.bean.MxUsersData au where au.weixinOpenId ='"+open_id+"'").get(0);	
	}
	//查询新闻类型列表
	@SuppressWarnings("unchecked")
	public List<MxNewsType> getNewsType() {
		return getHibernateTemplate().find("from com.bean.MxNewsType");
	}
	//查询学校返回
	@SuppressWarnings("unchecked")
	public List<MxSchools> getSchools() {
		return getHibernateTemplate().find("from com.bean.MxSchools");
	}
	//查询乡村返回
	@SuppressWarnings("unchecked")
	public List<MxRegion> getRegion() {
		return getHibernateTemplate().find("from com.bean.MxRegion");
	}
	


}
