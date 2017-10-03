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
		return (MxUsersData) getHibernateTemplate().find("from com.mx.ssh.bean.MxUsersData au where au.weixinOpenId ='"+open_id+"'").get(0);	
	}
	//查询新闻类型列表
	@SuppressWarnings("unchecked")
	public List<MxNewsType> getNewsType() {
		return getHibernateTemplate().find("from com.mx.ssh.bean.MxNewsType");
	}
	//查询学校返回
	@SuppressWarnings("unchecked")
	public List<MxSchools> getSchools() {
		return getHibernateTemplate().find("from com.mx.ssh.bean.MxSchools");
	}
	//查询乡村返回
	@SuppressWarnings("unchecked")
	public List<MxRegion> getRegion() {
		return getHibernateTemplate().find("from com.mx.ssh.bean.MxRegion");
	}
	//根据用户id查询新闻返回
	@SuppressWarnings("unchecked")
	public List<MxNewsData> getNewsByUserId(String userId){
		return getHibernateTemplate().find("from com.mx.ssh.bean.MxNewsData d where d.newsWriterId ='"+userId+"'");
	}
	//根据用户id查询新闻条数返回
	@SuppressWarnings("unchecked")
	public int getCountNewsByUserId(String userId,String state){
		if(state.equals("-1")){
			return ((Long)getHibernateTemplate().find("select count(*) from com.mx.ssh.bean.MxNewsData d where d.newsWriterId ='"+userId+"'").listIterator().next()).intValue();
		}else{
			return ((Long)getHibernateTemplate().find("select count(*) from com.mx.ssh.bean.MxNewsData d where d.newsWriterId ='"+userId+"' and d.state = "+state).listIterator().next()).intValue();
		}
	}
	//根据新闻id查询新闻返回
	public MxNewsData getNewsById(String newsId){
		return (MxNewsData) getHibernateTemplate().find("from com.mx.ssh.bean.MxNewsData d where d.newsId ='"+newsId+"'").get(0);
	}
	//根据类型id查询新闻返回
	@SuppressWarnings("unchecked")
	public List<MxNewsData> getNewsByNewsTypeId(String newsTypeId){
		return getHibernateTemplate().find("from com.mx.ssh.bean.MxNewsData d where d.newsTypeId ='"+newsTypeId+"'");
	}
	//根据新闻id查询评论返回
	@SuppressWarnings("unchecked")
	public List<MxNewsComment> getCommentByNewsId(String newsId){
		return  getHibernateTemplate().find("from com.mx.ssh.bean.MxNewsComment d where d.newsId ='"+newsId+"' order by d.createDate desc");
	}
	//增加评论
	public void addComment(MxNewsComment comment){
		getHibernateTemplate().save(comment);
	}
	//删除评论
	public void delComment(MxNewsComment comment){
		getHibernateTemplate().delete(comment);
	}
	//根据id查询评论条数返回
	public int getCountCommentByNewsId(String newsId){
		return ((Long)getHibernateTemplate().find("select count(*) from com.mx.ssh.bean.MxNewsComment d where d.newsId ='"+newsId+"'").listIterator().next()).intValue();
	}
	//新增文章点赞
	public void addPraise(MxNewsPraise praise){
		int count = ((Long)getHibernateTemplate().find("select count(*) from com.mx.ssh.bean.MxNewsPraise d where d.newsId ='"+praise.getNewsId()+"' and d.operator ='"+praise.getOperator()+"'").listIterator().next()).intValue();
		if(count==0){
			getHibernateTemplate().save(praise);
		}
		
	}
	//删除文章点赞
	public void delPraise(MxNewsPraise praise){
		MxNewsPraise model= (MxNewsPraise) getHibernateTemplate().find("from com.mx.ssh.bean.MxNewsPraise d where d.newsId ='"+praise.getNewsId()+"' and d.operator ='"+praise.getOperator()+"'").get(0);
		getHibernateTemplate().delete(model);
	}
	//根据文章id查询点赞数返回
	public int getCountPraiseByNewsId(String newsId){
			return ((Long)getHibernateTemplate().find("select count(*) from com.mx.ssh.bean.MxNewsPraise d where d.newsId ='"+newsId+"'").listIterator().next()).intValue();
	}
	//新增评论点赞
	public void addCommentPraise(MxNewsCommentPraise commentPraise){
		int count = ((Long)getHibernateTemplate().find("select count(*) from com.mx.ssh.bean.MxNewsCommentPraise d where d.commentId ='"+commentPraise.getCommentId()+"' and d.operator ='"+commentPraise.getOperator()+"'").listIterator().next()).intValue();
		if(count==0){
			getHibernateTemplate().save(commentPraise);
		}
		
	}
	
	//删除评论点赞
	public void delCommentPraise(MxNewsCommentPraise commentPraise){
		MxNewsCommentPraise model= (MxNewsCommentPraise) getHibernateTemplate().find("from com.mx.ssh.bean.MxNewsCommentPraise d where d.commentId ='"+commentPraise.getCommentId()+"' and d.operator ='"+commentPraise.getOperator()+"'").get(0);
		getHibernateTemplate().delete(model);
	}
	//根据评论id查询点赞数返回
	public int getCountCommentPraiseByCommentId(String commentId){
		return ((Long)getHibernateTemplate().find("select count(*) from com.mx.ssh.bean.MxNewsCommentPraise d where d.commentId ='"+commentId+"'").listIterator().next()).intValue();
	}
	//根据评论id和用户id查询点赞状态返回
	public int getCommentPraiseById(String commentId,String userId){
		return  ((Long)getHibernateTemplate().find("select count(1) from com.mx.ssh.bean.MxNewsCommentPraise d where d.commentId ='"+commentId+"' and d.operator ='"+userId+"'").listIterator().next()).intValue();
	}
	//根据评论id查询评论返回
	public MxNewsComment getCommentById(String commentId){
		return (MxNewsComment) getHibernateTemplate().find("from com.mx.ssh.bean.MxNewsComment d where d.commentId ='"+commentId+"'").get(0);
	}
	//插入评论的回复
	public void addCommentBack(MxNewsCommentBack commentBack){
		getHibernateTemplate().save(commentBack);
	}
	//根据评论id查询评论回复
	@SuppressWarnings("unchecked")
	public List<MxNewsCommentBack> getCommentBackByCommentId(String commentId){
		return  getHibernateTemplate().find("from com.mx.ssh.bean.MxNewsCommentBack d where d.commentId ='"+commentId+"' order by d.createDate desc");
	}
	//根据用户id查询评论返回
	@SuppressWarnings("unchecked")
	public List<MxNewsComment> getCommentByUserId(String userId){
		return  getHibernateTemplate().find("from com.mx.ssh.bean.MxNewsComment d where d.operator ='"+userId+"' order by d.createDate desc");
	}
}
