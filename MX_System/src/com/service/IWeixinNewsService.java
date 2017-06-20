package com.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bean.MxNewsContent;
import com.bean.MxNewsData;
import com.bean.MxNewsType;
import com.bean.MxRegion;
import com.bean.MxSchools;
import com.bean.MxUsersData;

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
}
