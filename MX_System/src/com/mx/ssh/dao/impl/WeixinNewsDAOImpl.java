package com.mx.ssh.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.mx.publicMethos.ISqlUtil;
import com.mx.ssh.bean.MxNewsComment;
import com.mx.ssh.bean.MxNewsCommentBack;
import com.mx.ssh.bean.MxNewsCommentPraise;
import com.mx.ssh.bean.MxNewsContent;
import com.mx.ssh.bean.MxNewsData;
import com.mx.ssh.bean.MxNewsPraise;
import com.mx.ssh.bean.MxNewsType;
import com.mx.ssh.bean.MxRegion;
import com.mx.ssh.bean.MxSchools;
import com.mx.ssh.bean.MxUsersData;
import com.mx.ssh.dao.IWeixinNewsDAO;

@Repository("weixinNewsDAO")
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
		return (MxUsersData) getHibernateTemplate().find("from com.mx.ssh.bean.MxUsersData au where au.weixinOpenId ='"+open_id+"'").get(0);	
	}
	//��ѯ���������б�
	@SuppressWarnings("unchecked")
	public List<MxNewsType> getNewsType() {
		return getHibernateTemplate().find("from com.mx.ssh.bean.MxNewsType");
	}
	//��ѯѧУ����
	@SuppressWarnings("unchecked")
	public List<MxSchools> getSchools() {
		return getHibernateTemplate().find("from com.mx.ssh.bean.MxSchools");
	}
	//��ѯ��巵��
	@SuppressWarnings("unchecked")
	public List<MxRegion> getRegion() {
		return getHibernateTemplate().find("from com.mx.ssh.bean.MxRegion");
	}
	//�����û�id��ѯ���ŷ���
	@SuppressWarnings("unchecked")
	public List<MxNewsData> getNewsByUserId(String userId){
		return getHibernateTemplate().find("from com.mx.ssh.bean.MxNewsData d where d.newsWriterId ='"+userId+"'");
	}
	//�����û�id��ѯ������������
	@SuppressWarnings("unchecked")
	public int getCountNewsByUserId(String userId,String state){
		if(state.equals("-1")){
			return ((Long)getHibernateTemplate().find("select count(*) from com.mx.ssh.bean.MxNewsData d where d.newsWriterId ='"+userId+"'").listIterator().next()).intValue();
		}else{
			return ((Long)getHibernateTemplate().find("select count(*) from com.mx.ssh.bean.MxNewsData d where d.newsWriterId ='"+userId+"' and d.state = "+state).listIterator().next()).intValue();
		}
	}
	//��������id��ѯ���ŷ���
	public MxNewsData getNewsById(String newsId){
		return (MxNewsData) getHibernateTemplate().find("from com.mx.ssh.bean.MxNewsData d where d.newsId ='"+newsId+"'").get(0);
	}
	//��������id��ѯ���ŷ���
	@SuppressWarnings("unchecked")
	public List<MxNewsData> getNewsByNewsTypeId(String newsTypeId){
		return getHibernateTemplate().find("from com.mx.ssh.bean.MxNewsData d where d.newsTypeId ='"+newsTypeId+"'");
	}
	//��������id��ѯ���۷���
	@SuppressWarnings("unchecked")
	public List<MxNewsComment> getCommentByNewsId(String newsId){
		return  getHibernateTemplate().find("from com.mx.ssh.bean.MxNewsComment d where d.newsId ='"+newsId+"' order by d.createDate desc");
	}
	//��������
	public void addComment(MxNewsComment comment){
		getHibernateTemplate().save(comment);
	}
	//ɾ������
	public void delComment(MxNewsComment comment){
		getHibernateTemplate().delete(comment);
	}
	//����id��ѯ������������
	public int getCountCommentByNewsId(String newsId){
		return ((Long)getHibernateTemplate().find("select count(*) from com.mx.ssh.bean.MxNewsComment d where d.newsId ='"+newsId+"'").listIterator().next()).intValue();
	}
	//�������µ���
	public void addPraise(MxNewsPraise praise){
		int count = ((Long)getHibernateTemplate().find("select count(*) from com.mx.ssh.bean.MxNewsPraise d where d.newsId ='"+praise.getNewsId()+"' and d.operator ='"+praise.getOperator()+"'").listIterator().next()).intValue();
		if(count==0){
			getHibernateTemplate().save(praise);
		}
		
	}
	//ɾ�����µ���
	public void delPraise(MxNewsPraise praise){
		MxNewsPraise model= (MxNewsPraise) getHibernateTemplate().find("from com.mx.ssh.bean.MxNewsPraise d where d.newsId ='"+praise.getNewsId()+"' and d.operator ='"+praise.getOperator()+"'").get(0);
		getHibernateTemplate().delete(model);
	}
	//��������id��ѯ����������
	public int getCountPraiseByNewsId(String newsId){
			return ((Long)getHibernateTemplate().find("select count(*) from com.mx.ssh.bean.MxNewsPraise d where d.newsId ='"+newsId+"'").listIterator().next()).intValue();
	}
	//�������۵���
	public void addCommentPraise(MxNewsCommentPraise commentPraise){
		int count = ((Long)getHibernateTemplate().find("select count(*) from com.mx.ssh.bean.MxNewsCommentPraise d where d.commentId ='"+commentPraise.getCommentId()+"' and d.operator ='"+commentPraise.getOperator()+"'").listIterator().next()).intValue();
		if(count==0){
			getHibernateTemplate().save(commentPraise);
		}
		
	}
	
	//ɾ�����۵���
	public void delCommentPraise(MxNewsCommentPraise commentPraise){
		MxNewsCommentPraise model= (MxNewsCommentPraise) getHibernateTemplate().find("from com.mx.ssh.bean.MxNewsCommentPraise d where d.commentId ='"+commentPraise.getCommentId()+"' and d.operator ='"+commentPraise.getOperator()+"'").get(0);
		getHibernateTemplate().delete(model);
	}
	//��������id��ѯ����������
	public int getCountCommentPraiseByCommentId(String commentId){
		return ((Long)getHibernateTemplate().find("select count(*) from com.mx.ssh.bean.MxNewsCommentPraise d where d.commentId ='"+commentId+"'").listIterator().next()).intValue();
	}
	//��������id���û�id��ѯ����״̬����
	public int getCommentPraiseById(String commentId,String userId){
		return  ((Long)getHibernateTemplate().find("select count(1) from com.mx.ssh.bean.MxNewsCommentPraise d where d.commentId ='"+commentId+"' and d.operator ='"+userId+"'").listIterator().next()).intValue();
	}
	//��������id��ѯ���۷���
	public MxNewsComment getCommentById(String commentId){
		return (MxNewsComment) getHibernateTemplate().find("from com.mx.ssh.bean.MxNewsComment d where d.commentId ='"+commentId+"'").get(0);
	}
	//�������۵Ļظ�
	public void addCommentBack(MxNewsCommentBack commentBack){
		getHibernateTemplate().save(commentBack);
	}
	//��������id��ѯ���ۻظ�
	@SuppressWarnings("unchecked")
	public List<MxNewsCommentBack> getCommentBackByCommentId(String commentId){
		return  getHibernateTemplate().find("from com.mx.ssh.bean.MxNewsCommentBack d where d.commentId ='"+commentId+"' order by d.createDate desc");
	}
	//�����û�id��ѯ���۷���
	@SuppressWarnings("unchecked")
	public List<MxNewsComment> getCommentByUserId(String userId){
		return  getHibernateTemplate().find("from com.mx.ssh.bean.MxNewsComment d where d.operator ='"+userId+"' order by d.createDate desc");
	}
}
