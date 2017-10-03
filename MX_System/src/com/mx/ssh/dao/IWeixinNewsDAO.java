package com.mx.ssh.dao;

import java.util.List;

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

/**
 * 新闻业务类dao
 * @author zw
 *
 */

public interface IWeixinNewsDAO {

	/**
     * 添加新闻
     * @param
     */ 
	public void addNews(MxNewsData newsData,MxNewsContent newsContent);
	/**
	 * 根据openid获取用户
	 * @param open_id
	 * @return
	 */
	public MxUsersData getUser(String open_id);
	/**
	 * 查询新闻类型列表
	 * @return
	 */
	public List<MxNewsType> getNewsType();
	/**
	 * 获取学校
	 * @return
	 */
	public List<MxSchools> getSchools();
	/**
	 * 获取地区
	 * @return
	 */
	public List<MxRegion> getRegion();
	/**
	 * 根据用户id获取新闻
	 */
	public List<MxNewsData> getNewsByUserId(String userId);
	/**
	 * 根据用户id查询新闻条数返回
	 */
	public int getCountNewsByUserId(String userId,String state);
	/**
	 * 根据新闻id查询新闻返回
	 * @param newsId
	 * @return
	 */
	public MxNewsData getNewsById(String newsId);
	/**
	 * 根据类型id查询新闻返回
	 * @param newsTypeId
	 * @return
	 */
	public List<MxNewsData> getNewsByNewsTypeId(String newsTypeId);
	
	/**
	 * 增加评论
	 * @param comment
	 */
	public void addComment(MxNewsComment comment);
	//删除评论
	public void delComment(MxNewsComment comment);
	/**
	 * 根据新闻id查询评论返回
	 * @param newsId
	 * @return
	 */
	public List<MxNewsComment> getCommentByNewsId(String newsId);
	//根据id查询评论条数返回
	public int getCountCommentByNewsId(String newsId);
	//新增评论的点赞
	public void addCommentPraise(MxNewsCommentPraise commentPraise);
	//删除评论的点赞
	public void delCommentPraise(MxNewsCommentPraise commentPraise);
	//根据评论id查询点赞数返回
	public int getCountCommentPraiseByCommentId(String commentId);
	//根据评论id和用户id查询点赞状态返回
	public int getCommentPraiseById(String commentId,String userId);
	//新增文章点赞
	public void addPraise(MxNewsPraise praise);
	//删除文章点赞
	public void delPraise(MxNewsPraise praise);
	//根据文章id查询点赞数返回
	public int getCountPraiseByNewsId(String newsId);
	//根据评论id查询评论返回
	public MxNewsComment getCommentById(String commentId);
	//插入评论的回复
	public void addCommentBack(MxNewsCommentBack commentBack);
	//根据评论id查询评论回复
	public List<MxNewsCommentBack> getCommentBackByCommentId(String commentId);
	//根据用户id查询评论返回
	public List<MxNewsComment> getCommentByUserId(String userId);
}
