package com.mx.ssh.dao;

import java.util.List;

import com.mx.ssh.bean.MxNewsContent;
import com.mx.ssh.bean.MxNewsData;
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
}
