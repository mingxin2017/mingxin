package com.mx.ssh.service;

import java.util.List;

import com.mx.ssh.bean.MxNewsContent;
import com.mx.ssh.bean.MxNewsData;
import com.mx.ssh.bean.MxNewsType;
import com.mx.ssh.bean.MxRegion;
import com.mx.ssh.bean.MxSchools;
import com.mx.ssh.bean.MxUsersData;

/**
 * 新闻业务
 * @author zw
 *
 */

public interface IWeixinNewsService {

	//添加新闻
	void addNews(MxNewsData newsData,MxNewsContent newsContent);
	//根据openid获取用户
	MxUsersData getUser(String open_id);
	/**
	 * 查询新闻类型列表
	 * @return
	 */
	List<MxNewsType> getNewsType();
	/**
	 * 查询学校列表
	 * @return
	 */
	List<MxSchools> getSchools();
	/**
	 * 查询乡村列表
	 * @return
	 */
	List<MxRegion> getRegion();
	/**
	 * 根据用户id获取新闻
	 */
	List<MxNewsData> getNewsByUserId(String userId);
	/**
	 * 根据用户id查询新闻条数返回
	 */
	int getCountNewsByUserId(String userId,String state);
	/**
	 * 根据新闻id查询新闻返回
	 * @param newsId
	 * @return
	 */
	MxNewsData getNewsById(String newsId);
	/**
	 * 根据类型id查询新闻返回
	 * @param newsTypeId
	 * @return
	 */
	List<MxNewsData> getNewsByNewsTypeId(String newsTypeId);
}
