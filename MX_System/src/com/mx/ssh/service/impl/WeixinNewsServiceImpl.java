package com.mx.ssh.service.impl;




import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service("weixinNewsService")
public class WeixinNewsServiceImpl implements com.mx.ssh.service.IWeixinNewsService{

	@Autowired
	private IWeixinNewsDAO weixinNewsDAO;//依赖注入用户dao

	//添加新闻
	public void addNews(MxNewsData newsData,MxNewsContent newsContent) {
		weixinNewsDAO.addNews(newsData,newsContent);
	}
    //根据openid获取用户
	public MxUsersData getUser(String open_id) {
		return weixinNewsDAO.getUser(open_id);
	}
    //查询新闻类型列表
	public List<MxNewsType> getNewsType() {
		return weixinNewsDAO.getNewsType();
	}
    //查询学校
	public List<MxSchools> getSchools() {
		return weixinNewsDAO.getSchools();
	}
    //查询乡村
	public List<MxRegion> getRegion() {
		return weixinNewsDAO.getRegion();
	}
    //根据用户id获取新闻
	public List<MxNewsData> getNewsByUserId(String userId) {
		return weixinNewsDAO.getNewsByUserId(userId);
	}
	//根据用户id查询新闻条数返回
	public int getCountNewsByUserId(String userId,String state) {
		return weixinNewsDAO.getCountNewsByUserId(userId,state);
	}
    //根据新闻id查询新闻返回
	public MxNewsData getNewsById(String newsId) {
		return weixinNewsDAO.getNewsById(newsId);
	}
	//根据类型id查询新闻返回
	public List<MxNewsData> getNewsByNewsTypeId(String newsTypeId) {
		return weixinNewsDAO.getNewsByNewsTypeId(newsTypeId);
	}
	//增加评论
	public void addComment(MxNewsComment comment) {
		weixinNewsDAO.addComment(comment);
	}
	//根据新闻id查询评论返回
	public List<MxNewsComment> getCommentByNewsId(String newsId) {
		return weixinNewsDAO.getCommentByNewsId(newsId);
	}
	//根据id查询评论条数返回
	public int getCountCommentByNewsId(String newsId) {
		return weixinNewsDAO.getCountCommentByNewsId(newsId);
	}
	//删除评论
	public void delComment(MxNewsComment comment) {
		weixinNewsDAO.delComment(comment);
	}
	//新增评论的点赞
	public int addCommentPraise(MxNewsCommentPraise commentPraise) {
		weixinNewsDAO.addCommentPraise(commentPraise);
		return weixinNewsDAO.getCountCommentPraiseByCommentId(commentPraise.getCommentId().toString());
		
	}
	//删除评论的点赞
	public int delCommentPraise(MxNewsCommentPraise commentPraise) {
		weixinNewsDAO.delCommentPraise(commentPraise);
		return weixinNewsDAO.getCountCommentPraiseByCommentId(commentPraise.getCommentId().toString());
	}
	//根据评论id查询点赞数返回
	public int getCountCommentPraiseByCommentId(String commentId) {
		return weixinNewsDAO.getCountCommentPraiseByCommentId(commentId);
	}
	//根据评论id和用户id查询点赞状态返回
	public int getCommentPraiseById(String commentId, String userId) {
		return weixinNewsDAO.getCommentPraiseById(commentId, userId);
	}
	public int addPraise(MxNewsPraise praise) {
		weixinNewsDAO.addPraise(praise);
		return weixinNewsDAO.getCountPraiseByNewsId(praise.getNewsId().toString());
	}
	public int delPraise(MxNewsPraise praise) {
		weixinNewsDAO.delPraise(praise);
		return weixinNewsDAO.getCountPraiseByNewsId(praise.getNewsId().toString());
	}
	public int getCountPraiseByNewsId(String newsId) {
		return weixinNewsDAO.getCountPraiseByNewsId(newsId);
	}
	//根据评论id查询评论返回
	public MxNewsComment getCommentById(String commentId) {
		return weixinNewsDAO.getCommentById(commentId);
	}
	//插入评论的回复
	public void addCommentBack(MxNewsCommentBack commentBack) {
		weixinNewsDAO.addCommentBack(commentBack);
		
	}
	//根据评论id查询评论回复
	public List<MxNewsCommentBack> getCommentBackByCommentId(String commentId) {
		return weixinNewsDAO.getCommentBackByCommentId(commentId);
	}
	//根据用户id查询评论返回
	public List<MxNewsComment> getCommentByUserId(String userId) {
		return weixinNewsDAO.getCommentByUserId(userId);
	}
	

}
