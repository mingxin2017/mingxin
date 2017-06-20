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

	public static final int pageLinesNum=3;//����ÿ��ҳ����ʾ���б�����
	public static final String DB_table_name="[JC_Web_System_DB].[dbo].[Sys_Users]";//�û����ݿ⼰���ݱ�����
	public static final String Primarykey="user_id";//�û����ݱ���������
	private ISqlUtil sqlUtil;//����ע��ִ���Զ���sql����ͨ����
	
	public ISqlUtil getSqlUtil() {
		return sqlUtil;
	}
	public void setSqlUtil(ISqlUtil sqlUtil) {
		this.sqlUtil = sqlUtil;
	}
	//�������
	public void addNews(MxNewsData newsData,MxNewsContent newsContent) {
		try{
			//�������
			Integer news_id = (Integer) getHibernateTemplate().save(newsData);
			//��Ӵӱ�
			newsContent.setNewsId(news_id);
			getHibernateTemplate().save(newsContent);
		}catch(Exception e){
			e.getMessage();
			e.printStackTrace();
		}
	}
    //����openid��ѯ�û�id
	public MxUsersData getUser(String open_id){
		return (MxUsersData) getHibernateTemplate().find("from com.bean.MxUsersData au where au.weixinOpenId ='"+open_id+"'").get(0);	
	}
	//��ѯ���������б�
	@SuppressWarnings("unchecked")
	public List<MxNewsType> getNewsType() {
		return getHibernateTemplate().find("from com.bean.MxNewsType");
	}
	//��ѯѧУ����
	@SuppressWarnings("unchecked")
	public List<MxSchools> getSchools() {
		return getHibernateTemplate().find("from com.bean.MxSchools");
	}
	//��ѯ��巵��
	@SuppressWarnings("unchecked")
	public List<MxRegion> getRegion() {
		return getHibernateTemplate().find("from com.bean.MxRegion");
	}
	


}
